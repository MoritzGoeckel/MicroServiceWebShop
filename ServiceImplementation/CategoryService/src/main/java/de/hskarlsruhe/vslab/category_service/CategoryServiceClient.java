package de.hskarlsruhe.vslab.category_service;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class CategoryServiceClient {

    private String baseUrl = "http://localhost:8083/";
    private RestTemplate restTemplate = new RestTemplate();

    public CategoryServiceClient(){}

    public CategoryServiceClient(String baseUrl){
        this.baseUrl = baseUrl;
    }

    public Category postCategory(String name) throws ApiException {
        ResponseEntity<Category> response = restTemplate.postForEntity(baseUrl + "categories", name, Category.class);
        handle(response);
        return response.getBody();

        //return restTemplate.postForObject(baseUrl + "categories/", name, Category.class);
    }

    public Category[] getCategories() throws ApiException {
        return getCategories("");
    }

    public Category[] getCategories(String query) throws ApiException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("query", query);

        ResponseEntity<Category[]> response = restTemplate.exchange(baseUrl + "categories", HttpMethod.GET, new HttpEntity<String>(headers), Category[].class);

        handle(response);

        return response.getBody();
    }

    public Category getCategoryById(int id) throws ApiException {
        ResponseEntity<Category> response = restTemplate.getForEntity(baseUrl + "categories/" + id, Category.class);
        handle(response);

        return response.getBody();

        //return restTemplate.getForObject(baseUrl + "categories/" + id, Category.class);
    }

    public void deleteCategoryById(int id) throws ApiException {
        ResponseEntity<Category> response = restTemplate.exchange(baseUrl + "categories/" + id, HttpMethod.DELETE, new HttpEntity<String>(new HttpHeaders()), Category.class);
        handle(response);

        //restTemplate.delete(baseUrl + "categories/" + id);
    }

    private void handle(ResponseEntity<?> response) throws ApiException {
        if(response.getStatusCode() != HttpStatus.OK)
            throw new ApiException(response.getStatusCode().value(), response.getStatusCode().getReasonPhrase());
    }
}
