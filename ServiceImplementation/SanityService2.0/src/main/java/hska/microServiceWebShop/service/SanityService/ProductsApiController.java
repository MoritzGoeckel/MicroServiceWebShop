package hska.microServiceWebShop.service.SanityService;

import hska.microServiceWebShop.Clients.ApiException;
import hska.microServiceWebShop.Clients.CategoryServiceClient;
import hska.microServiceWebShop.Clients.ProductServiceClient;
import hska.microServiceWebShop.models.*;
import hska.microServiceWebShop.models.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class ProductsApiController {

	@Autowired
	private ProductServiceClient productsAPIClient;

	@Autowired
	private CategoryServiceClient categoriesAPIClient;

	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public ResponseEntity<?> addProduct(@RequestHeader(name = "name", required = true) String name,
            @RequestHeader(name = "price", required = true) Double price,
            @RequestHeader(name = "category", required = true) Long category,
            @RequestHeader(name = "details", required = true) String details) {
		try {
			Category c = categoriesAPIClient.getCategoryById(category);
			ProductBackend p = productsAPIClient.postProduct(name, price,
					category, details);
			Product productNew = new Product();
			productNew.setId(p.getId());
			productNew.setName(p.getName());
			productNew.setPrice(p.getPrice());
			productNew.setDetails(p.getDetails());
			productNew.setCategory(c);
			return new ResponseEntity<Product>(productNew, HttpStatus.OK);
		} catch (ApiException e) {
			e.printStackTrace();
			Error error = new Error();
			error.setDescription(e.getMessage());
			return new ResponseEntity<Error>(error, HttpStatus.valueOf(e.getCode()));
		}
	}

	@RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
		try {
			productsAPIClient.deleteProductById(id.intValue());
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (ApiException e) {
			e.printStackTrace();
			Error error = new Error();
			error.setDescription(e.getMessage());
			return new ResponseEntity<Error>(error, HttpStatus.valueOf(e.getCode()));
		}
	}

	@RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getProduct(@PathVariable("id") Long id) {
		try {
			ProductBackend p = productsAPIClient.getProductById(id.intValue());
			Category c = categoriesAPIClient.getCategoryById(p.getCategory().intValue());
			Product productNew = new Product();
			productNew.setId(p.getId());
			productNew.setName(p.getName());
			productNew.setPrice(p.getPrice());
			productNew.setDetails(p.getDetails());
			productNew.setCategory(c);
			return new ResponseEntity<Product>(productNew, HttpStatus.OK);
		} catch (ApiException e) {
			e.printStackTrace();
			Error error = new Error();
			error.setDescription(e.getMessage());
			return new ResponseEntity<Error>(error, HttpStatus.valueOf(e.getCode()));
		}
	}

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public ResponseEntity<?> queryProducts(@RequestHeader(value = "Text", defaultValue = "") String text,
			@RequestHeader(value = "Category", defaultValue = "") Long category,
			@RequestHeader(value = "PriceMin", defaultValue = "") Double priceMin,
			@RequestHeader(value = "PriceMax", defaultValue = "") Double priceMax) {
		ProductQuery query = new ProductQuery();
		query.setText(text);
		query.setCategory(category);
		query.setPriceMin(priceMin);
		query.setPriceMax(priceMax);
		try {
			List<ProductBackend> ps = Arrays.asList(productsAPIClient.getProducts(query.getText(), query.getPriceMin(),
					query.getPriceMax(), query.getCategory()));
			List<Product> psNew = new ArrayList<>(ps.size());
			for (ProductBackend p : ps) {
				Category c = categoriesAPIClient.getCategoryById(p.getCategory().intValue());
				Product productNew = new Product();
				productNew.setId(p.getId());
				productNew.setName(p.getName());
				productNew.setPrice(p.getPrice());
				productNew.setDetails(p.getDetails());
				productNew.setCategory(c);
				psNew.add(productNew);
			}
			return new ResponseEntity<List<Product>>(psNew, HttpStatus.OK);
		} catch (ApiException e) {
			e.printStackTrace();
			Error error = new Error();
			error.setDescription(e.getMessage());
			return new ResponseEntity<Error>(error, HttpStatus.valueOf(e.getCode()));
		}
	}

}
