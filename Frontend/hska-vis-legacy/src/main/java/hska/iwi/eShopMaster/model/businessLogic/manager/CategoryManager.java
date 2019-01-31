package hska.iwi.eShopMaster.model.businessLogic.manager;

import java.util.List;

import hska.iwi.eShopMaster.models.Category;

public interface CategoryManager {

	public List<Category> getCategories();
	
	public Category getCategory(long id); 
	
	public void addCategory(String name);
	
	public void delCategoryById(long id);

	
}
