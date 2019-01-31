package hska.iwi.eShopMaster.controller;

import hska.iwi.eShopMaster.model.businessLogic.manager.CategoryManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.CategoryManagerImpl;
import hska.iwi.eShopMaster.models.Category;
import hska.iwi.eShopMaster.models.User;

import java.util.List;
import java.util.Map;

import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteCategoryAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1254575994729199914L;

	private int catId;
	private List<Category> categories;

	public String execute() throws Exception {

		String res = "input";

		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User) session.get("webshop_user");

		if (user != null && (user.getRole().getTyp().equals("admin"))) {

			// Helper inserts new Category in DB:
			CategoryManager categoryManager = new CategoryManagerImpl((OAuth2RestTemplate)session.get("restTemplate"));

			try {
				categoryManager.delCategoryById(catId);
				res = "success";
			} catch (HttpClientErrorException e) {
				res = "error";
				addActionError(getText("There are Products existing with this category...deleteing not allowed!"));
			}

			categories = categoryManager.getCategories();

		}

		return res;

	}

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
}
