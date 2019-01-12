import ProductServiceClient.ApiException;
import ProductServiceClient.Product;
import ProductServiceClient.ProductServiceClientApplication;
import ProductServiceClient.ProductServiceClient;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={ProductServiceClientApplication.class})
public class Tests {

    //!!!! YOU HAVE TO LET IT BE INSTACIATED BY SPRING. DO NOT USE "NEW"
    @Autowired
    private ProductServiceClient client;

    @Test
    public void generalTest() throws ApiException {

        System.out.println("Starting test");


        Product p1 = new Product("testClient1",1.0,1L,"test run1");
        Gson gson = new Gson();
        String json = gson.toJson(p1);
        JsonElement jsonElement = gson.toJsonTree(p1, Product.class);
        String element = jsonElement.toString();
        Product product = client.postProduct(element);
        System.out.println("Added: " + product);



        Product p2 = new Product("testClient2",2.0,2L,"test run2");
        //json = gson.toJson(p2);

        Product[] products = client.getProducts();
        System.out.println("Got all: " + Arrays.toString(products));

        //product = client.postProduct("test2");
        //System.out.println("Added: " + product);

        products = client.getProducts("1");
        System.out.println("Got specific: " + Arrays.toString(products));

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