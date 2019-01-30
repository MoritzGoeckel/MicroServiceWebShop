package hska.iwi.eShopMaster.model.businessLogic.manager;

import java.util.List;

import hska.iwi.eShopMaster.models.ProductBackend;

public interface ProductManager {

	public List<ProductBackend> getProducts();

	public ProductBackend getProductById(int id);

	public long addProduct(String name, double price, int categoryId, String details); 

	public List<ProductBackend> getProductsForSearchValues(String searchValue, Double searchMinPrice, Double searchMaxPrice, Long categoryId);
	
	public boolean deleteProductsByCategoryId(int categoryId);
	
    public void deleteProductById(int id); 
    
	
}
