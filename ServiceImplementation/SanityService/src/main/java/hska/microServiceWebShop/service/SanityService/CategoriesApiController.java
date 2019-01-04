package hska.microServiceWebShop.service.SanityService;

import hska.microServiceWebShop.ApiException;
import hska.microServiceWebShop.models.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import hska.microServiceWebShop.models.Error;
import io.swagger.annotations.*;
import io.swagger.models.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-03T22:24:38.514Z")

@Controller
public class CategoriesApiController implements CategoriesApi {

    private static final Logger log = LoggerFactory.getLogger(CategoriesApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

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
                    if(e.getResponseBody() == null)
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
                    return ResponseEntity.status(e.getCode()).body(e.getResponseBody());
                }
        }
        Error error = new Error();
        error.description("wrong acept datatye");
        return ResponseEntity.badRequest().body(error);
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
            if(e.getResponseBody() == null)
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            return ResponseEntity.status(e.getCode()).body(e.getResponseBody());
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
                if(e.getResponseBody() == null)
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
                return ResponseEntity.status(e.getCode()).body(e.getResponseBody());
            }
        }

        Error error = new Error();
        error.description("wrong acept datatye");
        return ResponseEntity.badRequest().body(error);    }

    public ResponseEntity queryCategories(@ApiParam(value = "Parameters of the categoryquery"  )  @Valid @RequestBody CategoryQuery query) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                List<Category> c = categoriesAPIClient.queryCategories(query);
                return ResponseEntity.ok().body(c);
            } catch (ApiException e) {
                e.printStackTrace();
                if(e.getResponseBody() == null)
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
                return ResponseEntity.status(e.getCode()).body(e.getResponseBody());
            }
        }

        Error error = new Error();
        error.description("wrong acept datatye");
        return ResponseEntity.badRequest().body(error);
    }

}
