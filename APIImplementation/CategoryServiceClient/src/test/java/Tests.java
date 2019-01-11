import CategoryServiceClient.ApiException;
import CategoryServiceClient.Category;
import CategoryServiceClient.CategoryServiceClientApplication;
import CategoryServiceClient.CategoryServiceClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={CategoryServiceClientApplication.class})
public class Tests {

    //!!!! YOU HAVE TO LET IT BE INSTACIATED BY SPRING. DO NOT USE "NEW"
    @Autowired
    private CategoryServiceClient client;

    @Test
    public void generalTest() throws ApiException {
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