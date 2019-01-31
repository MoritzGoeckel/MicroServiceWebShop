package hska.iwi.eShopMaster.Clients;

import hska.iwi.eShopMaster.models.Category;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

public class CategoryServiceClient {

	private String baseUrl;
	private OAuth2RestTemplate restTemplate;

	public CategoryServiceClient(OAuth2RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
		this.baseUrl = "http://" + "localhost:8091" + "/api/" + "categories";
	}

	public CategoryServiceClient(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public Category postCategory(String name) throws ApiException {
		// Manager
		ResponseEntity<Category> response = null;
		try {
			response = restTemplate.postForEntity(baseUrl, name, Category.class);
			handle(response);
			return response.getBody();
		} catch (HttpMessageNotReadableException e) {
			// TODO ???
			throw new ApiException(208, "Category already reported");
		}
	}

	public Category[] getCategories(String query) throws ApiException {
		HttpHeaders headers = new HttpHeaders();
		if (query != null && !query.isEmpty()) {
			headers.set("query", query);
		}

		ResponseEntity<Category[]> response = restTemplate.exchange(baseUrl, HttpMethod.GET,
				new HttpEntity<String>(headers), Category[].class);

		handle(response);

		return response.getBody();
	}

	public Category getCategoryById(long id) throws ApiException {
		ResponseEntity<Category> response = restTemplate.getForEntity(baseUrl + "/" + id, Category.class);
		handle(response);

		return response.getBody();
	}

	public void deleteCategoryById(long id) throws ApiException {
		ResponseEntity<Category> response = restTemplate.exchange(baseUrl + "/" + id, HttpMethod.DELETE,
				new HttpEntity<String>(new HttpHeaders()), Category.class);
		handle(response);
	}

	private void handle(ResponseEntity<?> response) throws ApiException {
		if (response.getStatusCode() != HttpStatus.OK)
			throw new ApiException(response.getStatusCode().value(), response.getStatusCode().getReasonPhrase());
	}
}
