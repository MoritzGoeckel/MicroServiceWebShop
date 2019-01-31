package hska.iwi.eShopMaster.controller;

import hska.iwi.eShopMaster.model.businessLogic.manager.CategoryManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.ProductManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.CategoryManagerImpl;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.ProductManagerImpl;
import hska.iwi.eShopMaster.models.Category;
import hska.iwi.eShopMaster.models.Product;
import hska.iwi.eShopMaster.models.User;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.security.oauth2.client.OAuth2RestTemplate;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SearchAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6565401833074694229L;
	
	
	private String searchDescription = null;
	private String searchMinPrice;
	private String searchMaxPrice;
	
	private Double sMinPrice = null;
	private Double sMaxPrice = null;
	
	private User user;
	private List<Product> products;
	private List<Category> categories;
	

	public String execute() throws Exception {
		System.err.println("1");
		String result = "input";
		
		// Get user:
		Map<String, Object> session = ActionContext.getContext().getSession();
		user = (User) session.get("webshop_user");
		ActionContext.getContext().setLocale(Locale.US);  
		System.err.println("2");
		if(user != null){
			System.err.println("3");
			// Search products and show results:
			ProductManager productManager = new ProductManagerImpl((OAuth2RestTemplate)session.get("restTemplate"));
//			this.products = productManager.getProductsForSearchValues(this.searchDescription, this.searchMinPrice, this.searchMaxPrice);
			if (!searchMinPrice.isEmpty()){
				sMinPrice =  Double.parseDouble(this.searchMinPrice);
			}
			if (!searchMaxPrice.isEmpty()){
				sMaxPrice =  Double.parseDouble(this.searchMaxPrice);
			}
			System.err.println("4");
			this.products = productManager.getProductsForSearchValues(this.searchDescription, sMinPrice, sMaxPrice, null);
			System.err.println("Products: " + this.products.toString());
			// Show all categories:
			CategoryManager categoryManager = new CategoryManagerImpl((OAuth2RestTemplate)session.get("restTemplate"));
			this.categories = categoryManager.getCategories();
			System.err.println("Categories: " + this.categories.toString());
			result = "success";
		}
		
		return result;
	}
			
		
		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}
		
		public List<Product> getProducts() {
			return products;
		}

		public void setProducts(List<Product> products) {
			this.products = products;
		}
		
		public List<Category> getCategories() {
			return categories;
		}

		public void setCategories(List<Category> categories) {
			this.categories = categories;
		}
		
		


	public String getSearchValue() {
		return searchDescription;
	}


	public void setSearchValue(String searchValue) {
		this.searchDescription = searchValue;
	}


	public String getSearchMinPrice() {
		return searchMinPrice;
	}


	public void setSearchMinPrice(String searchMinPrice) {
		this.searchMinPrice = searchMinPrice;
	}


	public String getSearchMaxPrice() {
		return searchMaxPrice;
	}


	public void setSearchMaxPrice(String searchMaxPrice) {
		this.searchMaxPrice = searchMaxPrice;
	}


//	public Double getSearchMinPrice() {
//		return searchMinPrice;
//	}
//
//
//	public void setSearchMinPrice(Double searchMinPrice) {
//		this.searchMinPrice = searchMinPrice;
//	}
//
//
//	public Double getSearchMaxPrice() {
//		return searchMaxPrice;
//	}
//
//
//	public void setSearchMaxPrice(Double searchMaxPrice) {
//		this.searchMaxPrice = searchMaxPrice;
//	}
}
