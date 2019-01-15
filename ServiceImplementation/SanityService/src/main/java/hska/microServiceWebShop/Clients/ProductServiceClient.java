package hska.microServiceWebShop.Clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import hska.microServiceWebShop.models.Product;

@Controller
public class ProductServiceClient {

    private String baseUrl;

    @Autowired
    private RestTemplate restTemplate;

    public ProductServiceClient(){ this.baseUrl = "http://" + "productservice" + "/";}

    public ProductServiceClient(String baseUrl){ this.baseUrl = baseUrl;}


    public Product postProduct(String name, Double price, Long category, String details) throws ApiException{


        HttpHeaders headers = new HttpHeaders();

        headers.set("name", name);
        headers.set("price", price.toString());
        headers.set("category", category.toString());
        headers.set("details", details);

        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<Product> response = restTemplate.postForEntity(baseUrl + "products", new HttpEntity<>(headers), Product.class);

        handle(response);

        return response.getBody();
    }

    public Product[] getProducts() throws ApiException {
        return getProducts(null, null, null, null);
    }

    public Product[] getProducts(String name, Double price, Long category, String details) throws ApiException {


        HttpHeaders headers = new HttpHeaders();
        if(name!=null)
            headers.set("name", name);
        if(price!=null)
            headers.set("price", price.toString());
        if(category!=null)
            headers.set("category", category.toString());
        if(details!=null)
            headers.set("details", details);

        //RequestEntity<Product> requestEntity = new RequestEntity<Product>();

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