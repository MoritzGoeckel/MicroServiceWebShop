package de.hska.frontend;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;

import com.opensymphony.xwork2.interceptor.annotations.Before;

import hska.iwi.eShopMaster.model.businessLogic.manager.CategoryManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.ProductManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.UserManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.CategoryManagerImpl;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.ProductManagerImpl;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.UserManagerImpl;
import hska.iwi.eShopMaster.models.Category;
import hska.iwi.eShopMaster.models.Product;
import hska.iwi.eShopMaster.models.Role;
import hska.iwi.eShopMaster.models.User;

public class ManagerTests {

	
	
	private ProductManager productManager;
	private CategoryManager categoryManager;
	private UserManager userManager;

	@Before
	public void Setup() {
		ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();
		resource.setPassword("pw");
		resource.setUsername("bob");
		resource.setAccessTokenUri("http://localhost:8092/oauth/token");
		resource.setClientId("frontendId");
		resource.setClientSecret("frontendSecret");
		resource.setGrantType("password");
		resource.setScope(Arrays.asList("read", "write"));

		OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resource);
		
		productManager = new ProductManagerImpl(restTemplate);
		categoryManager = new CategoryManagerImpl(restTemplate);
		userManager = new UserManagerImpl(restTemplate);
	}
	
	@Test
	public void UserRoleTest() {

		System.out.println("Check User Exists");
		Assert.assertTrue(userManager.doesUserAlreadyExist("bob"));

		System.out.println("Check User Role");
		Role role = userManager.getRoleByLevel(1);
		Assert.assertEquals("user", role.getTyp());
		Assert.assertTrue(role.getLevel() == 1);

		System.out.println("Check User By Name");
		User user = userManager.getUserByUsername("bob");
		Assert.assertEquals("bob", user.getUsername());
		Assert.assertEquals("Bob", user.getFirstName());
		Assert.assertEquals("Bobowitsch", user.getLastName());
		Assert.assertEquals("pw", user.getPassword());
		Assert.assertEquals("user", user.getRole().getTyp());
		Assert.assertEquals("1", user.getRole().getLevel().toString());

		System.out.println("Add User");
		userManager.registerUser("Tester", "Tester", "Tester", "Tester", role.getId());
		User user2 = userManager.getUserByUsername("Tester");
		Assert.assertEquals("Tester", user2.getUsername());
		Assert.assertEquals("Tester", user2.getFirstName());
		Assert.assertEquals("Tester", user2.getLastName());
		Assert.assertEquals("Tester", user2.getPassword());
		Assert.assertEquals(role.getTyp(), user2.getRole().getTyp());
		Assert.assertEquals(role.getLevel(), user2.getRole().getLevel());

		System.out.println("Readd User");
		userManager.registerUser("Tester", "Tester", "Tester", "Tester", role.getId());

		System.out.println("Delete User");
		Assert.assertTrue(userManager.deleteUserById(user2.getId()));
		User user3 = userManager.getUserByUsername("Tester");
		Assert.assertTrue(user3 == null);

	}

	@Test
	public void TestCategory() {

		List<String> names = new ArrayList<String>();
		names.add("cat");
		names.add("cat2");

		System.out.println("Add Categories");
		for (String name : names) {
			categoryManager.addCategory(name);
		}

		System.out.println("ReAdd Category");
		categoryManager.addCategory(names.get(0));

		System.out.println("Get Categories");
		List<Category> categories = categoryManager.getCategories();
		Assert.assertEquals(names.size(), categories.size());
		for (int i = 0; i < categories.size(); i++) {
			Assert.assertEquals(names.get(i), categories.get(i).getName());
		}

		System.out.println("Get Category By Id");
		Category cat = categoryManager.getCategory(categories.get(0).getId());
		Assert.assertEquals(categories.get(0).getId(), cat.getId());

		System.out.println("Delete Category");
		for (Category cate : categories) {
			categoryManager.delCategoryById(cate.getId());
		}

		Assert.assertTrue(categoryManager.getCategories().isEmpty());

	}

	@Test
	public void ProductTest() {
		List<String> names = new ArrayList<String>();
		names.add("cat");
		names.add("cat2");

		System.out.println("Add Categories");
		for (String name : names) {
			categoryManager.addCategory(name);
		}
		System.out.println("Get Categories");
		List<Category> categories = categoryManager.getCategories();

		System.out.println("Add Products");
		productManager.addProduct("Name", 1.1, categories.get(0).getId(), "Fetails");
		productManager.addProduct("Name2", 1.3, categories.get(0).getId(), "Fetails");
		productManager.addProduct("Nam3", 1.1, categories.get(1).getId(), "Fetails");

		System.out.println("ReAdd Product");
		productManager.addProduct("Name", 1.1, categories.get(0).getId(), "Fetails");

		System.out.println("Get Products");
		List<Product> products = productManager.getProducts();
		Assert.assertEquals(3, products.size());

		System.out.println("Get Product By Id");
		Product cat = productManager.getProductById(products.get(0).getId());
		Assert.assertEquals(products.get(0).getId(), cat.getId());

		System.out.println("Products By CatId");
		Assert.assertEquals(1, 
				productManager.getProductsForSearchValues(null, null, null, categories.get(1).getId()).size());

		//productManager.deleteProductsByCategoryId(categories.get(1).getId());
		
		
		System.out.println("Delete Products");
		for (Product cate : products) {
			productManager.deleteProductById(cate.getId());
		}

		Assert.assertTrue(productManager.getProducts().isEmpty());

		
		System.out.println("Delete Category");
		for (Category cate : categories) {
			categoryManager.delCategoryById(cate.getId());
		}
	}

}
