package hska.iwi.eShopMaster.model.businessLogic.manager;

import java.util.List;

import hska.microServiceWebShop.models.Category;


public interface CategoryManager {

	public List<Category> getCategories();
	
	public Category getCategory(int id); 
	
	public void addCategory(String name);
	
	public void delCategoryById(int id);

	
}
