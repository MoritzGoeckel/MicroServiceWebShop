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

public class CategoryManagerImpl implements CategoryManager{

	private CategoryServiceClient apiInstance;
	
	public CategoryManagerImpl() {
		apiInstance = new CategoryServiceClient(OAuth2RestManager.getInstance());
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

	public Category getCategory(int id) {
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

	public void delCategoryById(int id) {
		try {
			apiInstance.deleteCategoryById(id);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
