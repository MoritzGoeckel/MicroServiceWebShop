package de.hskarlsruhe.vslab.category_service;

import org.junit.Test;

import java.util.Arrays;

public class CategoryServiceApplicationTests {

    private CategoryServiceClient client = new CategoryServiceClient();

    @Test
    public void addCategoryTest() throws ApiException {
        Category category = client.postCategory("TheName3");
        System.out.println(category);
    }

    @Test
    public void getCategoryArray() throws ApiException {
        Category[] categories = client.getCategories("");
        System.out.println(Arrays.toString(categories));
    }

    @Test
    public void getSpecificCategoryArray() throws ApiException {
        Category[] categories = client.getCategories("2");
        System.out.println(Arrays.toString(categories));
    }

    @Test
    public void getSecondCategoryById() throws ApiException {
        Category category = client.getCategoryById(2);
        System.out.println(category);
    }

    @Test
    public void deleteCategoryTest() throws ApiException {
        client.deleteCategoryById(3);
        Category[] categories = client.getCategories();
        System.out.println(Arrays.toString(categories));
    }
}
