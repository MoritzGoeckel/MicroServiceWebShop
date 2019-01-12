package hska.microServiceWebShop.service.SanityService;

import hska.microServiceWebShop.ApiException;
import hska.microServiceWebShop.models.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import hska.microServiceWebShop.models.Error;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-03T22:24:38.514Z")

@Controller
public class CategoriesApiController  implements CategoriesApi{
    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    //@Autowired
    //private CategoryServiceClient categoriesAPIClient;
    hska.microServiceWebShop.api.CategoriesApi categoriesAPIClient = new hska.microServiceWebShop.api.CategoriesApi();

    hska.microServiceWebShop.api.ProductsApi productsAPIClient = new hska.microServiceWebShop.api.ProductsApi();

    @org.springframework.beans.factory.annotation.Autowired
    public CategoriesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity addCategory(@ApiParam(value = "The name of the category" ,required=true )  @Valid @RequestBody Category name) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
                try {
                    Category c = categoriesAPIClient.addCategory(name);
                    return ResponseEntity.ok().body(c);
                } catch (ApiException e) {
                    e.printStackTrace();
                    Error error = new Error();
                    error.setDescription(e.getMessage());
                    return ResponseEntity.status(e.getCode()).body(error);
                }
        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity deleteCategory(@ApiParam(value = "The id of the to be deleted category",required=true) @PathVariable("id") Long id) {
        String accept = request.getHeader("Accept");
        try {
            Category c = categoriesAPIClient.getCategory(id);
            ProductQuery pq = new ProductQuery();
            pq.category(id);
            List<Product> ps = productsAPIClient.queryProducts(pq);
            if(ps.size() == 0){
                categoriesAPIClient.deleteCategory(id);
                return ResponseEntity.ok().body(c);
            }
            Error error = new Error();
            error.description("still products in category");
            return ResponseEntity.badRequest().body(error);
        } catch (ApiException e) {
            e.printStackTrace();
            Error error = new Error();
            error.setDescription(e.getMessage());
            return ResponseEntity.status(e.getCode()).body(error);
        }
    }

    public ResponseEntity getCategory(@ApiParam(value = "The id of the to be retrieved category",required=true) @PathVariable("id") Long id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                Category c = categoriesAPIClient.getCategory(id);
                return ResponseEntity.ok().body(c);
            } catch (ApiException e) {
                e.printStackTrace();
                Error error = new Error();
                error.setDescription(e.getMessage());
                return ResponseEntity.status(e.getCode()).body(error);
            }
        }

        Error error = new Error();
        error.description("wrong acept datatye");
        return ResponseEntity.badRequest().body(error);
    }

    public ResponseEntity queryCategories(@RequestHeader(value="Text",defaultValue = "") String text) {
        CategoryQuery query = new CategoryQuery();
        query.setText(text);
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                List<Category> cs = categoriesAPIClient.queryCategories(query);
                return new ResponseEntity<List<Category>>(cs,HttpStatus.OK);
            } catch (ApiException e) {
                e.printStackTrace();
                Error error = new Error();
                error.setDescription(e.getMessage());
                return ResponseEntity.status(e.getCode()).body(error);
            }
        }

        Error error = new Error();
        error.description("wrong acept datatye");
        return new ResponseEntity<Error>(error,HttpStatus.BAD_REQUEST);
    }

}
