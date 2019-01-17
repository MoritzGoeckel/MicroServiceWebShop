/*
 * Sanity Service
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: 1.0.0
 * Contact: bhb127@outlook.de
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package hska.microServiceWebShop.api;

import hska.microServiceWebShop.ApiException;
import hska.microServiceWebShop.models.*;
import hska.microServiceWebShop.models.Error;
import org.junit.Test;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for ProductsApi
 */
@Ignore
public class ProductsApiTest {

    private final ProductsApi api = new ProductsApi();
    private final CategoriesApi apiCategory = new CategoriesApi();

    
    @Test
    public void addAndDeleteProductTest() throws ApiException {
        System.out.println("AddAndDeleteProductTest");
        System.out.println("------------------------");
        System.out.println();
        try {
        	
        	Category name = new Category();
            name.setName("cat5");

            // Add cat1
            System.out.println("Add cat5");
            Category response = apiCategory.addCategory(name);
            System.out.println(response.toString());
            assert response.getName().equalsIgnoreCase("cat5");
            assert response.getId() != null;

        	
            ProductBackend pro = new ProductBackend();
            pro.setName("pro1");
            pro.setDetails("no details");
            pro.setCategory(response.getId());
            pro.setPrice(50.50);

            // Add pro1
            System.out.println("Add pro1");
            Product proRes = api.addProduct(pro);
            System.out.println(proRes.toString());
            assert proRes.getName().equalsIgnoreCase("pro1");
            assert proRes.getId() != null;

            // Delete pro1
            System.out.println("Delete pro1");
            Long id = proRes.getId();
            api.deleteProduct(id);
            
         // Delete cat1
            System.out.println("Delete cat5");
            id = response.getId();
            apiCategory.deleteCategory(id);
            
        }catch (ApiException e){
            System.err.println(e.getCode());
            System.err.println(e.getMessage());
            System.err.println(e.getResponseBody());
            System.err.println(e.getStackTrace());
            assert false;
        }

    }

    @Test
    public void addAndQueryProductTest() throws ApiException {
        System.out.println("AddAndQueryProductTest");
        System.out.println("------------------------");
        System.out.println();
        try {
        	Category name = new Category();
            name.setName("cat6");

            // Add cat6
            System.out.println("Add cat6");
            Category responseC = apiCategory.addCategory(name);
            System.out.println(responseC.toString());
            assert responseC.getName().equalsIgnoreCase("cat6");
            assert responseC.getId() != null;
        	
            ProductBackend pro = new ProductBackend();
            pro.setName("pro2");
            pro.setDetails("no details");
            pro.setCategory(responseC.getId());
            pro.setPrice(50.50);

            // Add pro2
            System.out.println("Add pro2");
            Product response = api.addProduct(pro);
            System.out.println(response.toString());
            assert response.getName().equalsIgnoreCase("pro2");
            assert response.getId() != null;

            // get pro2 by id
            System.out.println("get cat2 by id");
            Long id = response.getId();
            Product response2 = api.getProduct(id);
            System.out.println(response.toString());
            assert response2.getName().equalsIgnoreCase("pro2");

            pro = new ProductBackend();
            pro.setName("pro3");
            pro.setDetails("no details");
            pro.setCategory(responseC.getId());
            pro.setPrice(50.50);

            // add pro3
            System.out.println("add pro3");
            response = api.addProduct(pro);
            System.out.println(response.toString());
            assert response.getName().equalsIgnoreCase("pro3");
            assert response.getId() != null;

            // query for pro3
            System.out.println("query for pro3");
            ProductQuery query = new ProductQuery();
            query.setText("pro3");
            List<Product> responses = api.queryProducts(query);
            for (Product c: responses) {
                System.out.println(c.toString());
                assert response.getName().contains("pro3");
            }

            // query for all
            System.out.println("query for all");
            query = new ProductQuery();
            responses = api.queryProducts(query);
            for (Product c: responses) {
                System.out.println(c.toString());

                // Delete catX
                System.out.println("Delete " + c.getName());
                id = c.getId();
                api.deleteProduct(id);
            }

            // query for all
            System.out.println("query for all");
            query = new ProductQuery();
            responses = api.queryProducts(query);
            assert responses.size() == 0;
            
         // Delete cat1
            System.out.println("Delete cat6");
            id = responseC.getId();
            apiCategory.deleteCategory(id);


        }catch (ApiException e){
            System.err.println(e.getCode());
            System.err.println(e.getMessage());
            System.err.println(e.getResponseBody());
            System.err.println(e.getStackTrace());
            assert false;
        }

    }

    @Test
    public void addTwoTimesProductTest() throws ApiException {
        System.out.println("AddTwoTimesProductTest");
        System.out.println("------------------------");
        System.out.println();
        try {
        	
        	Category name = new Category();
            name.setName("cat7");

            // Add cat6
            System.out.println("Add cat7");
            Category responseC = apiCategory.addCategory(name);
            System.out.println(responseC.toString());
            assert responseC.getName().equalsIgnoreCase("cat7");
            assert responseC.getId() != null;
        	
            ProductBackend pro = new ProductBackend();
            pro.setName("pro4");
            pro.setDetails("no details");
            pro.setCategory(responseC.getId());
            pro.setPrice(50.50);

            // Add pro4
            System.out.println("Add pro4");
            Product response = api.addProduct(pro);
            System.out.println(response.toString());
            assert response.getName().equalsIgnoreCase("pro4");
            assert response.getId() != null;

            try {
                // Add pro4
                System.out.println("Add pro4");
                response = api.addProduct(pro);
                assert true;
            }catch (ApiException e){
                System.out.println(e.getCode());
                System.out.println(e.getMessage());
                System.out.println(e.getResponseBody());
                System.out.println(e.getStackTrace());
            }

            // Delete pro4
            System.out.println("Delete pro4");
            Long id = response.getId();
            api.deleteProduct(id);
            
            // Delete cat1
            System.out.println("Delete cat7");
            id = responseC.getId();
            apiCategory.deleteCategory(id);

            
        }catch (ApiException e){
            System.err.println(e.getCode());
            System.err.println(e.getMessage());
            System.err.println(e.getResponseBody());
            System.err.println(e.getStackTrace());
            assert false;
        }

    }
    
}
