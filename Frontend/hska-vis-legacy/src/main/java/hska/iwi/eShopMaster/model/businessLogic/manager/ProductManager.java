package hska.iwi.eShopMaster.model.businessLogic.manager;

import java.util.List;

import hska.iwi.eShopMaster.models.Product;

public interface ProductManager {

	public List<Product> getProducts();

	public Product getProductById(long id);

	public long addProduct(String name, double price, long categoryId, String details); 

	public List<Product> getProductsForSearchValues(String searchValue, Double searchMinPrice, Double searchMaxPrice, Long categoryId);
	
	public boolean deleteProductsByCategoryId(long categoryId);
	
    public void deleteProductById(long id); 
    
	
}
