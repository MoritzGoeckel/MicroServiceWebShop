package hska.microServiceWebShop.Clients;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import hska.microServiceWebShop.models.ProductBackend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Controller
public class ProductServiceClient {

  private Map<Long, ProductBackend> cache = new HashMap<>();

    private String baseUrl;

    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate restTemplate;

    public ProductServiceClient(){ this.baseUrl = "http://" + "productservice" + "/";}

    public ProductServiceClient(String baseUrl){ this.baseUrl = baseUrl;}


    public ProductBackend postProduct(String name, Double price, Long category, String details) throws ApiException{


        HttpHeaders headers = new HttpHeaders();

        headers.set("name", name);
        headers.set("price", price.toString());
        headers.set("category", category.toString());
        headers.set("details", details);

        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<ProductBackend> response = restTemplate.postForEntity(baseUrl + "products", new HttpEntity<>(headers), ProductBackend.class);

        handle(response);

        return response.getBody();
    }

    public ProductBackend[] getProducts() throws ApiException {
        return getProducts(null, null, null, null);
    }

    @HystrixCommand(fallbackMethod = "getProductsCache",
            ignoreExceptions=ApiException.class)
    public ProductBackend[] getProducts(String text, Double min, Double max, Long category) throws ApiException {


        HttpHeaders headers = new HttpHeaders();
        if(text!=null)
            headers.set("text", text);
        if(min!=null)
            headers.set("min", min.toString());
        if(max!=null)
            headers.set("max", max.toString());
        if(category!=null)
            headers.set("category", category.toString());


        ResponseEntity<ProductBackend[]> response = restTemplate.exchange(baseUrl + "products", HttpMethod.GET, new HttpEntity<String>(headers), ProductBackend[].class);

        handle(response);

        ProductBackend[] products = response.getBody();
        for(ProductBackend product : products) {
            cache.put(product.getId(), product);
        }

        return products;
    }

    public ProductBackend[] getProductsCache(String text, Double min, Double max, Long category) throws ApiException {

        Stream<ProductBackend> allFoundProductsSream = cache.values().stream();

        // Suche nach text - check
        if(text != null)
        {
            allFoundProductsSream = allFoundProductsSream
                    .filter(c -> (c.getDetails().contains(text) || c.getName().contains(text)));
        }

        // Suche nach min -
        if( min != null)
        {
            allFoundProductsSream = allFoundProductsSream
                    .filter(c -> c.getPrice() > min);
        }

        // Suche nach max
        if(max != null)
        {
            allFoundProductsSream = allFoundProductsSream
                    .filter(c -> c.getPrice() < max);
        }

        // Suche nach category
        if(category != null)
        {
            allFoundProductsSream = allFoundProductsSream
                    .filter(c -> c.getCategory().equals(category));
        }

        ProductBackend[] foundProducts = allFoundProductsSream.toArray(ProductBackend[]::new);

        return foundProducts;
    }

    @HystrixCommand(fallbackMethod = "getProductByIdCache",
            ignoreExceptions=ApiException.class)
    public ProductBackend getProductById(int id) throws ApiException {
        ResponseEntity<ProductBackend> response = restTemplate.getForEntity(baseUrl + "products/" + id, ProductBackend.class);
        handle(response);

        ProductBackend product = response.getBody();
        if(product != null)
            cache.put(product.getId(), product);

        return product;
    }

    public ProductBackend getProductByIdCache(int id) throws ApiException {
        ProductBackend product = cache.getOrDefault((long) id, null);
        if(product == null)
            throw new ApiException(HttpStatus.NOT_FOUND.value(), "Category not found");

        return product;
    }

    public void deleteProductById(int id) throws ApiException {
        ResponseEntity<ProductBackend> response = restTemplate.exchange(baseUrl + "products/" + id, HttpMethod.DELETE, new HttpEntity<String>(new HttpHeaders()), ProductBackend.class);
        handle(response);

    }

    private void handle(ResponseEntity<?> response) throws ApiException {
        if(response.getStatusCode() != HttpStatus.OK)
            throw new ApiException(response.getStatusCode().value(), response.getStatusCode().getReasonPhrase());
    }
    
}
