package hska.microServiceWebShop.service.SanityService;

import hska.microServiceWebShop.Clients.ApiException;
import hska.microServiceWebShop.Clients.CategoryServiceClient;
import hska.microServiceWebShop.Clients.ProductServiceClient;
import hska.microServiceWebShop.models.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import hska.microServiceWebShop.models.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-03T22:24:38.514Z")

@Controller
public class CategoriesApiController  implements CategoriesApi{
    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private CategoryServiceClient categoriesAPIClient;

    @Autowired
    private ProductServiceClient productsAPIClient;

    @org.springframework.beans.factory.annotation.Autowired
    public CategoriesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity addCategory(@RequestBody Category category) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
                try {
                    Category c = categoriesAPIClient.postCategory(category.getName());
                    return new ResponseEntity<Category>(c,HttpStatus.OK);
                } catch (ApiException e) {
                    System.err.println(e.getCode());
                    e.printStackTrace();
                    Error error = new Error();
                    error.setDescription(e.getMessage());
                    return new ResponseEntity<Error>(error,HttpStatus.valueOf(e.getCode()));
                }
        }

        Error error = new Error();
        error.description("wrong acept datatye");
        return new ResponseEntity<Error>(error,HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity deleteCategory(@PathVariable("id") Long id) {
        String accept = request.getHeader("Accept");
        try {
            Category c = categoriesAPIClient.getCategoryById(id.intValue());
            ProductQuery pq = new ProductQuery();
            pq.category(id);
            List<Product> ps = Arrays.asList(productsAPIClient.getProducts(null,null,null,pq.getCategory()));
            if(ps.size() == 0){
                categoriesAPIClient.deleteCategoryById(id.intValue());
                return new ResponseEntity<Category>(c,HttpStatus.OK);
            }
            Error error = new Error();
            error.description("still products in category");
            return new ResponseEntity<Error>(error,HttpStatus.BAD_REQUEST);
        } catch (ApiException e) {
            e.printStackTrace();
            Error error = new Error();
            error.setDescription(e.getMessage());
            return new ResponseEntity<Error>(error,HttpStatus.valueOf(e.getCode()));
        }
    }

    public ResponseEntity getCategory(@PathVariable("id") Long id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                Category c = categoriesAPIClient.getCategoryById(id.intValue());
                return new ResponseEntity<Category>(c,HttpStatus.OK);
            } catch (ApiException e) {
                e.printStackTrace();
                Error error = new Error();
                error.setDescription(e.getMessage());
                return new ResponseEntity<Error>(error,HttpStatus.valueOf(e.getCode()));
            }
        }

        Error error = new Error();
        error.description("wrong acept datatye");
        return new ResponseEntity<Error>(error,HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity queryCategories(@RequestHeader(value="Text",defaultValue = "") String text) {
        CategoryQuery query = new CategoryQuery();
        query.setText(text);
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                List<Category> cs = Arrays.asList(categoriesAPIClient.getCategories(query.getText()));
                return new ResponseEntity<List<Category>>(cs,HttpStatus.OK);
            } catch (ApiException e) {
                e.printStackTrace();
                Error error = new Error();
                error.setDescription(e.getMessage());
                return new ResponseEntity<Error>(error,HttpStatus.valueOf(e.getCode()));
            }
        }

        Error error = new Error();
        error.description("wrong acept datatye");
        return new ResponseEntity<Error>(error,HttpStatus.BAD_REQUEST);
    }

}
