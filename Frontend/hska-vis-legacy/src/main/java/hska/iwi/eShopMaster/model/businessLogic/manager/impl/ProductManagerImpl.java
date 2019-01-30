package hska.iwi.eShopMaster.model.businessLogic.manager.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import hska.iwi.eShopMaster.Clients.ApiException;
import hska.iwi.eShopMaster.Clients.ProductServiceClient;
import hska.iwi.eShopMaster.model.businessLogic.manager.OAuth2RestManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.ProductManager;
import hska.iwi.eShopMaster.models.Product;

public class ProductManagerImpl implements ProductManager {

	private ProductServiceClient apiInstance;
	
	public ProductManagerImpl() {
		apiInstance = new ProductServiceClient(OAuth2RestManager.getInstance());
	}

	public List<Product> getProducts() {
		List<Product> all = null;
		try {
			all = Arrays.asList(apiInstance.getProducts());
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			all = new ArrayList<Product>();
		}
		return all;
	}
	
	public List<Product> getProductsForSearchValues(String searchValue, Double searchMinPrice, Double searchMaxPrice, Long categroyId) {
		List<Product> all = null;
		try {
			all = Arrays.asList(apiInstance.getProducts(searchValue, searchMinPrice, searchMaxPrice, categroyId));
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			all = new ArrayList<Product>();
		}
		return all;
	}

	public Product getProductById(long id) {
		try {
			return apiInstance.getProductById(id);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public long addProduct(String name, double price, long categoryId, String details) {
		try {
			return apiInstance.postProduct(name, price, categoryId, details).getId();
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
		
	}
	

	public void deleteProductById(long id) {
		try {
			apiInstance.deleteProductById(id);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean deleteProductsByCategoryId(long categoryId) {
		// TODO Implement??
		return false;
	}

}
