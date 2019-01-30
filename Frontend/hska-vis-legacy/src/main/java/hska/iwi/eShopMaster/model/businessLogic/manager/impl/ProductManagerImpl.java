package hska.iwi.eShopMaster.model.businessLogic.manager.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import hska.iwi.eShopMaster.Clients.ApiException;
import hska.iwi.eShopMaster.Clients.ProductServiceClient;
import hska.iwi.eShopMaster.model.businessLogic.manager.OAuth2RestManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.ProductManager;
import hska.iwi.eShopMaster.models.ProductBackend;

public class ProductManagerImpl implements ProductManager {

	private ProductServiceClient apiInstance;
	
	public ProductManagerImpl() {
		apiInstance = new ProductServiceClient(OAuth2RestManager.getInstance());
	}

	public List<ProductBackend> getProducts() {
		List<ProductBackend> all = null;
		try {
			all = Arrays.asList(apiInstance.getProducts());
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			all = new ArrayList<ProductBackend>();
		}
		return all;
	}
	
	public List<ProductBackend> getProductsForSearchValues(String searchValue, Double searchMinPrice, Double searchMaxPrice, Long categroyId) {
		List<ProductBackend> all = null;
		try {
			all = Arrays.asList(apiInstance.getProducts(searchValue, searchMinPrice, searchMaxPrice, categroyId));
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			all = new ArrayList<ProductBackend>();
		}
		return all;
	}

	public ProductBackend getProductById(int id) {
		try {
			return apiInstance.getProductById(id);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public long addProduct(String name, double price, int categoryId, String details) {
		try {
			return apiInstance.postProduct(name, price, (long) categoryId, details).getId();
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
		
	}
	

	public void deleteProductById(int id) {
		try {
			apiInstance.deleteProductById(id);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean deleteProductsByCategoryId(int categoryId) {
		return false;
	}

}
