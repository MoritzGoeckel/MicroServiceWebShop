package de.hska.frontend;

import org.junit.Assert;
import org.junit.Test;

import hska.iwi.eShopMaster.model.businessLogic.manager.CategoryManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.ProductManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.UserManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.CategoryManagerImpl;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.ProductManagerImpl;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.UserManagerImpl;
import hska.iwi.eShopMaster.models.Role;
import hska.iwi.eShopMaster.models.User;


public class ManagerTests {

	private ProductManager productManager = new ProductManagerImpl();
	private CategoryManager categoryManager = new CategoryManagerImpl();
	private UserManager userManager = new UserManagerImpl();
	
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
	
//	@Test
//	public void Test2() {
//		
//		List<Product> products = productManager.getProducts();		
//		Assertions.assertTrue(products.isEmpty());
//		
//		
//		long productId = productManager.addProduct("Test", 3.5, 2, "Details");
//		
//		String [] args = {""};
//		ProductServiceApplication.main(args);
//
//		System.out.println("Starting test");
//
//
//		Product p1 = new Product("testClient1",1.0,1L,"test run1");
//		Gson gson = new Gson();
//		//String json = gson.toJson(p1);
//		JsonElement jsonElement = gson.toJsonTree(p1, Product.class);
//		String element = jsonElement.toString();
//		Product product = client.postProduct(element);
//		System.out.println("Added: " + product);
//
//
//
//		//Product p2 = new Product("testClient2",2.0,2L,"test run2");
//		//json = gson.toJson(p2);
//
//		Product[] products = client.getProducts();
//		System.out.println("Got all: " + Arrays.toString(products));
//
//
//		products = client.getProducts("1");
//		System.out.println("Got specific: " + Arrays.toString(products));
//
//		Product product1 = client.getProductById(1);
//		System.out.println("Got by ID: " + product1);
//
//		products = client.getProducts();
//		System.out.println("Got all: " + Arrays.toString(products));
//
//		System.out.println("Deleting id 1");
//		client.deleteProductById(1);
//
//		products = client.getProducts();
//		System.out.println("Got all: " + Arrays.toString(products));
//	}


}

