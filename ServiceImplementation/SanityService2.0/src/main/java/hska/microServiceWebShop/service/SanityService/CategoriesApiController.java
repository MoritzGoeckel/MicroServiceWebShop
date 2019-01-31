package hska.microServiceWebShop.service.SanityService;

import hska.microServiceWebShop.Clients.ApiException;
import hska.microServiceWebShop.Clients.CategoryServiceClient;
import hska.microServiceWebShop.Clients.ProductServiceClient;
import hska.microServiceWebShop.models.Category;
import hska.microServiceWebShop.models.CategoryQuery;
import hska.microServiceWebShop.models.Error;
import hska.microServiceWebShop.models.ProductBackend;
import hska.microServiceWebShop.models.ProductQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;


@RestController
public class CategoriesApiController {

	@Autowired
	private CategoryServiceClient categoriesAPIClient;

	@Autowired
	private ProductServiceClient productsAPIClient;

	@RequestMapping(value = "/categories", method = RequestMethod.POST)
	public ResponseEntity<?> addCategory(@RequestBody String name) {
		try {
			Category c = categoriesAPIClient.postCategory(name);
			return new ResponseEntity<Category>(c, HttpStatus.OK);
		} catch (ApiException e) {
			System.err.println(e.getCode());
			e.printStackTrace();
			Error error = new Error();
			error.setDescription(e.getMessage());
			return new ResponseEntity<Error>(error, HttpStatus.valueOf(e.getCode()));
		}
	}

	@RequestMapping(value = "/categories/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id) {
		try {
			Category c = categoriesAPIClient.getCategoryById(id.intValue());
			ProductQuery pq = new ProductQuery();
			pq.category(id);
			List<ProductBackend> ps = Arrays.asList(productsAPIClient.getProducts(null, null, null, pq.getCategory()));
			if (ps.size() == 0) {
				categoriesAPIClient.deleteCategoryById(id.intValue());
				return new ResponseEntity<Category>(c, HttpStatus.OK);
			}
			Error error = new Error();
			error.description("still products in category");
			return new ResponseEntity<Error>(error, HttpStatus.BAD_REQUEST);
		} catch (ApiException e) {
			e.printStackTrace();
			Error error = new Error();
			error.setDescription(e.getMessage());
			return new ResponseEntity<Error>(error, HttpStatus.valueOf(e.getCode()));
		}
	}

	@RequestMapping(value = "/categories/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getCategory(@PathVariable("id") Long id) {
		try {
			Category c = categoriesAPIClient.getCategoryById(id.intValue());
			return new ResponseEntity<Category>(c, HttpStatus.OK);
		} catch (ApiException e) {
			e.printStackTrace();
			Error error = new Error();
			error.setDescription(e.getMessage());
			return new ResponseEntity<Error>(error, HttpStatus.valueOf(e.getCode()));
		}
	}

	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public ResponseEntity<?> queryCategories(@RequestHeader(value = "Text", defaultValue = "") String text) {
		CategoryQuery query = new CategoryQuery();
		query.setText(text);
		try {
			List<Category> cs = Arrays.asList(categoriesAPIClient.getCategories(query.getText()));
			return new ResponseEntity<List<Category>>(cs, HttpStatus.OK);
		} catch (ApiException e) {
			e.printStackTrace();
			Error error = new Error();
			error.setDescription(e.getMessage());
			return new ResponseEntity<Error>(error, HttpStatus.valueOf(e.getCode()));
		}
	}

}
