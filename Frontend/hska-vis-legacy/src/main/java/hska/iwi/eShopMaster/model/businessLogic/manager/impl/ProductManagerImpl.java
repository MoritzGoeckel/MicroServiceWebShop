package hska.iwi.eShopMaster.model.businessLogic.manager.impl;

import java.util.ArrayList;
import java.util.List;

import hska.iwi.eShopMaster.model.businessLogic.manager.ProductManager;
import hska.microServiceWebShop.ApiClient;
import hska.microServiceWebShop.ApiException;
import hska.microServiceWebShop.api.CategoriesApi;
import hska.microServiceWebShop.api.ProductsApi;
import hska.microServiceWebShop.models.Product;
import hska.microServiceWebShop.models.ProductBackend;
import hska.microServiceWebShop.models.ProductQuery;

public class ProductManagerImpl implements ProductManager {

	ProductsApi apiInstance;
	
	public ProductManagerImpl() {
		ApiClient apiClient = new ApiClient();
		apiClient.setBasePath("http://localhost:8091/api/");
		apiInstance = new ProductsApi(apiClient);
	}

	public List<Product> getProducts() {
		List<Product> all = null;
		try {
			all = apiInstance.queryProducts(null);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			all = new ArrayList<Product>();
		}
		return all;
	}
	
	public List<Product> getProductsForSearchValues(String searchValue, Double searchMinPrice, Double searchMaxPrice) {
		List<Product> all = null;
		ProductQuery query = new ProductQuery();
		query.setText(searchValue);
		query.setPriceMax(searchMaxPrice);
		query.setPriceMin(searchMinPrice);
		try {
			all = apiInstance.queryProducts(query);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			all = new ArrayList<Product>();
		}
		return all;
	}

	public Product getProductById(int id) {
		try {
			return apiInstance.getProduct((long)id);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public long addProduct(String name, double price, int categoryId, String details) {
		ProductBackend product = new ProductBackend();
		product.setCategory((long)categoryId);
		product.setName(name);
		product.setPrice(price);
		product.setDetails(details);
		
		try {
			return apiInstance.addProduct(product).getId();
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
		
	}
	

	public void deleteProductById(int id) {
		try {
			apiInstance.deleteProduct((long)id);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean deleteProductsByCategoryId(int categoryId) {
		return false;
	}


}
