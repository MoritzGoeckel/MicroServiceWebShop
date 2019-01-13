package hska.microServiceWebShop.Clients;

import hska.microServiceWebShop.ApiException;
import hska.microServiceWebShop.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

@Controller
public class ProductServiceClient {

    private String baseUrl;

    @Autowired
    private RestTemplate restTemplate;

    public ProductServiceClient(){ this.baseUrl = "http://" + "product-service" + "/";}

    public ProductServiceClient(String baseUrl){ this.baseUrl = baseUrl;}

    public Product postProduct(String jsonElement) throws ApiException {
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
