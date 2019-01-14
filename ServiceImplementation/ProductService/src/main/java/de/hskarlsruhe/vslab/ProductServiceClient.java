package de.hskarlsruhe.vslab;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class ProductServiceClient {

    private String baseUrl = "http://localhost:8082/";
    private RestTemplate restTemplate = new RestTemplate();

    public ProductServiceClient(){}

    public ProductServiceClient(String baseUrl){
        this.baseUrl = baseUrl;
    }

    public Product postProduct(String jsonElement) throws ApiException {

        //Gson gson = new Gson();
        //Product product = gson.fromJson(productAsString, Product.class);

        // set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(jsonElement, headers);

        ResponseEntity<Product> response = restTemplate.postForEntity(baseUrl + "products", entity, Product.class);

        handle(response);

        return response.getBody();

    }

    public Product[] getProducts() throws ApiException {
        return getProducts("");
    }

    public Product[] getProducts(String query) throws ApiException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("query", query);

        ResponseEntity<Product[]> response = restTemplate.exchange(baseUrl + "products", HttpMethod.GET, new HttpEntity(headers), Product[].class);

        handle(response);

        return response.getBody();
    }

    public Product getProductById(int id) throws ApiException {
        ResponseEntity<Product> response = restTemplate.getForEntity(baseUrl + "products/" + id, Product.class);
        handle(response);

        return response.getBody();

    }

    public void deleteProductById(int id) throws ApiException {
        ResponseEntity<Product> response = restTemplate.exchange(baseUrl + "products/" + id, HttpMethod.DELETE, new HttpEntity(new HttpHeaders()), Product.class);
        handle(response);

    }

    private void handle(ResponseEntity response) throws ApiException {
        if(response.getStatusCode() != HttpStatus.OK)
            throw new ApiException(response.getStatusCode().value(), response.getStatusCode().getReasonPhrase());
    }
}
