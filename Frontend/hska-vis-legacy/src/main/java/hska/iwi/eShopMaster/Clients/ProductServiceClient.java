package hska.iwi.eShopMaster.Clients;

import hska.iwi.eShopMaster.models.ProductBackend;
import org.springframework.http.*;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;

public class ProductServiceClient {

    private String baseUrl;
    private OAuth2RestTemplate restTemplate;

    public ProductServiceClient(OAuth2RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.baseUrl = "http://" + "localhost:8091" + "/api/";
    }

    public ProductBackend postProduct(String name, Double price, Long category, String details) throws ApiException{

        HttpHeaders headers = new HttpHeaders();

        headers.set("name", name);
        headers.set("price", price.toString());
        headers.set("category", category.toString());
        headers.set("details", details);

        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<ProductBackend> response = restTemplate.postForEntity(baseUrl + "products", new HttpEntity<String>(headers), ProductBackend.class);

        handle(response);

        return response.getBody();
    }

    public ProductBackend[] getProducts() throws ApiException {
        return getProducts(null, null, null, null);
    }

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

        return response.getBody();
    }

    public ProductBackend getProductById(int id) throws ApiException {
        ResponseEntity<ProductBackend> response = restTemplate.getForEntity(baseUrl + "products/" + id, ProductBackend.class);
        handle(response);

        return response.getBody();
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
