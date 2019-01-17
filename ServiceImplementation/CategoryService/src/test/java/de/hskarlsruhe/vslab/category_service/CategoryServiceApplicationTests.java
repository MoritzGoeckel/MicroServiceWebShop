package de.hskarlsruhe.vslab.category_service;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
@Ignore
public class CategoryServiceApplicationTests {

    private CategoryServiceClient client = new CategoryServiceClient();

    @Test
    public void generalTest() throws ApiException {
        CategoryServiceApplication.main("");

        System.out.println("Starting test");

        Category category = client.postCategory("TheName");
        System.out.println("Added: " + category);

        Category[] categories = client.getCategories();
        System.out.println("Got all: " + Arrays.toString(categories));

        category = client.postCategory("TheName2");
        System.out.println("Added: " + category);

        categories = client.getCategories("2");
        System.out.println("Got specific: " + Arrays.toString(categories));

        category = client.getCategoryById(2);
        System.out.println("Got by ID: " + category);

        categories = client.getCategories();
        System.out.println("Got all: " + Arrays.toString(categories));

        System.out.println("Deleting id 1");
        client.deleteCategoryById(1);

        categories = client.getCategories();
        System.out.println("Got all: " + Arrays.toString(categories));
    }

}
