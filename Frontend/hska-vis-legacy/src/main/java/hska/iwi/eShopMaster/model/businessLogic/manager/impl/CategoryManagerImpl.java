package hska.iwi.eShopMaster.model.businessLogic.manager.impl;

import java.util.ArrayList;
import java.util.List;

import hska.iwi.eShopMaster.model.businessLogic.manager.CategoryManager;
import hska.microServiceWebShop.ApiClient;
import hska.microServiceWebShop.ApiException;
import hska.microServiceWebShop.api.CategoriesApi;
import hska.microServiceWebShop.api.UserRoleApi;
import hska.microServiceWebShop.models.Category;

public class CategoryManagerImpl implements CategoryManager{

	CategoriesApi apiInstance;
	
	public CategoryManagerImpl() {
		ApiClient apiClient = new ApiClient();
		apiClient.setBasePath("http://localhost:8091/api/");
		apiInstance = new CategoriesApi(apiClient);
	}
	
	public List<Category> getCategories() {
		List<Category> all = null;
		try {
			all = apiInstance.queryCategories(null);
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
			cat = apiInstance.getCategory((long)id);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cat;
	}

	public void addCategory(String name) {
		Category category = new Category();
		category.setName(name);
		try {
			apiInstance.addCategory(category);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void delCategoryById(int id) {
		try {
			apiInstance.deleteCategory((long)id);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
