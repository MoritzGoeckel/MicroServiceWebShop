package CategoryServiceClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CategoryServiceClient {

    private String baseUrl;
    private Map<Long, Category> cache = new HashMap<>();

    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate restTemplate;

    public CategoryServiceClient(){
        this.baseUrl = "http://" + "category-service" + "/";
    }

    public CategoryServiceClient(String baseUrl){
        this.baseUrl = baseUrl;
    }

    public Category postCategory(String name) throws ApiException {
        ResponseEntity<Category> response = restTemplate.postForEntity(baseUrl + "categories", name, Category.class);
        handle(response);
        return response.getBody();
    }

    public Category[] getCategories() throws ApiException {
        return getCategories("");
    }

    @HystrixCommand(fallbackMethod = "getCategoriesCache",
            ignoreExceptions=ApiException.class)
    public Category[] getCategories(String query) throws ApiException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("query", query);

        ResponseEntity<Category[]> response = restTemplate.exchange(baseUrl + "categories", HttpMethod.GET, new HttpEntity(headers), Category[].class);

        handle(response);

        Category[] categories = response.getBody();
        for(Category category : categories) {
            cache.put(category.getId(), category);
        }

        return categories;
    }

    @HystrixCommand(fallbackMethod = "getCategoriesByIdCache",
            ignoreExceptions=ApiException.class)
    public Category getCategoryById(int id) throws ApiException {
        ResponseEntity<Category> response = restTemplate.getForEntity(baseUrl + "categories/" + id, Category.class);
        handle(response);

        Category category = response.getBody();
        if(category != null)
            cache.put(category.getId(), category);

        return category;
    }

    public void deleteCategoryById(int id) throws ApiException {
        ResponseEntity<Category> response = restTemplate.exchange(baseUrl + "categories/" + id, HttpMethod.DELETE, new HttpEntity(new HttpHeaders()), Category.class);
        handle(response);
    }

    private void handle(ResponseEntity response) throws ApiException {
        if(response.getStatusCode() != HttpStatus.OK)
            throw new ApiException(response.getStatusCode().value(), response.getStatusCode().getReasonPhrase());
    }

    public Category[] getCategoriesCache(String query) {
        return cache.values()
                .stream()
                .filter(c -> c.getName().contains(query))
                .toArray(Category[]::new);
    }

    public Category getCategoriesByIdCache(int id) throws ApiException {
        Category category = cache.getOrDefault((long) id, null);
        if(category == null)
            throw new ApiException(HttpStatus.NOT_FOUND.value(), "Category not found");

        return category;
    }
}

