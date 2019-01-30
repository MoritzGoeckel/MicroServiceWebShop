package hska.iwi.eShopMaster.Clients;

import hska.iwi.eShopMaster.models.Product;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;

public class ProductServiceClient {

	private String baseUrl;
	private OAuth2RestTemplate restTemplate;

	public ProductServiceClient(OAuth2RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
		this.baseUrl = "http://" + "localhost:8091" + "/api/";
	}

	public Product postProduct(String name, Double price, Long category, String details) throws ApiException {

		HttpHeaders headers = new HttpHeaders();

		headers.set("name", name);
		headers.set("price", price.toString());
		headers.set("category", category.toString());
		headers.set("details", details);

		headers.setContentType(MediaType.APPLICATION_JSON);

		try {
			ResponseEntity<Product> response = restTemplate.postForEntity(baseUrl + "products",
					new HttpEntity<String>(headers), Product.class);
			handle(response);
			return response.getBody();
		} catch (HttpMessageNotReadableException e) {
			// TODO ???
			throw new ApiException(208, "Product already reported");
		}
	}

	public Product[] getProducts() throws ApiException {
		return getProducts(null, null, null, null);
	}

	public Product[] getProducts(String text, Double min, Double max, Long category) throws ApiException {
		HttpHeaders headers = new HttpHeaders();
		if (text != null)
			headers.set("text", text);
		if (min != null)
			headers.set("min", min.toString());
		if (max != null)
			headers.set("max", max.toString());
		if (category != null)
			headers.set("category", category.toString());

		ResponseEntity<Product[]> response = restTemplate.exchange(baseUrl + "products", HttpMethod.GET,
				new HttpEntity<String>(headers), Product[].class);

		handle(response);

		return response.getBody();
	}

	public Product getProductById(long id) throws ApiException {
		ResponseEntity<Product> response = restTemplate.getForEntity(baseUrl + "products/" + id, Product.class);
		handle(response);

		return response.getBody();
	}

	public void deleteProductById(long id) throws ApiException {
		ResponseEntity<Void> response = restTemplate.exchange(baseUrl + "products/" + id, HttpMethod.DELETE,
				new HttpEntity<String>(new HttpHeaders()), Void.class);
		handle(response);

	}

	private void handle(ResponseEntity<?> response) throws ApiException {
		if (response.getStatusCode() != HttpStatus.OK)
			throw new ApiException(response.getStatusCode().value(), response.getStatusCode().getReasonPhrase());
	}
}
