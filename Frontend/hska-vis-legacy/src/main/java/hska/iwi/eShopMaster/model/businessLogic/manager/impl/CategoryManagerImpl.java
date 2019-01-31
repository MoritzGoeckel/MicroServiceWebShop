package hska.iwi.eShopMaster.model.businessLogic.manager.impl;

import hska.iwi.eShopMaster.Clients.ApiException;
import hska.iwi.eShopMaster.Clients.CategoryServiceClient;
import hska.iwi.eShopMaster.model.businessLogic.manager.CategoryManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.OAuth2RestManager;
import hska.iwi.eShopMaster.models.Category;
import hska.iwi.eShopMaster.models.CategoryQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.oauth2.client.OAuth2RestTemplate;

public class CategoryManagerImpl implements CategoryManager{

	private CategoryServiceClient apiInstance;
	
	public CategoryManagerImpl(OAuth2RestTemplate restTemplate) {
		apiInstance = new CategoryServiceClient(restTemplate);
	}
	
	public List<Category> getCategories() {
		List<Category> all = null;
		CategoryQuery query = new CategoryQuery();
		try {
			all = Arrays.asList(apiInstance.getCategories(query.getText()));
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			all = new ArrayList<Category>();
		}
		return all;
	}

	public Category getCategory(long id) {
		Category cat = null;
		try {
			cat = apiInstance.getCategoryById(id);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cat;
	}

	public void addCategory(String name) {
		try {
			apiInstance.postCategory(name);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void delCategoryById(long id) {
		try {
			apiInstance.deleteCategoryById(id);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
