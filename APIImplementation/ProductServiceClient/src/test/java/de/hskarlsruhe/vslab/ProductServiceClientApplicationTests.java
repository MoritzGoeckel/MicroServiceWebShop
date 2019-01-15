package de.hskarlsruhe.vslab;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceClientApplicationTests {

	//!!!! YOU HAVE TO LET IT BE INSTACIATED BY SPRING. DO NOT USE "NEW"
	@Autowired
	private ProductServiceClient client;

	@Test
	public void generalTest() throws ApiException {

		System.out.println("Starting test");

		Product product = client.postProduct("testClient1", 1.0, 1L, "test run1");
		System.out.println("Added product1: " + product);



		Product product2 = client.postProduct("tc2",2.0,2L,"tr2");
		System.out.println("Added product2: " + product2);

		Product product3 = client.postProduct("tc233",2.0,2L,"tr2");
		System.out.println("Added product3: " + product3);

		Product[] products = client.getProducts();
		System.out.println("Got all: " + Arrays.toString(products));


		products = client.getProducts("testClient1", null, null, null);
		System.out.println("Got Products by Name: " + Arrays.toString(products));

		products = client.getProducts("tc23", null, null, null);
		System.out.println("Got Products by Name: " + Arrays.toString(products));

		products = client.getProducts(null, 1.0, null, null);
		System.out.println("Got Products by Price: " + Arrays.toString(products));

		products = client.getProducts(null, null, 1.0, null);
		System.out.println("Got Products by Category: " + Arrays.toString(products));

		products = client.getProducts(null, null, null, "tr2");
		System.out.println("Got Products by Details: " + Arrays.toString(products));


		Product product1 = client.getProductById(1);
		System.out.println("Got by ID: " + product1);

		products = client.getProducts();
		System.out.println("Got all: " + Arrays.toString(products));

		System.out.println("Deleting id 1");
		client.deleteProductById(1);

		products = client.getProducts();
		System.out.println("Got all: " + Arrays.toString(products));
	}

}

