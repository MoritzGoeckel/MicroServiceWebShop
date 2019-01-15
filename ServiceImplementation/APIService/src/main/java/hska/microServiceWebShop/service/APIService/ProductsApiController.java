package hska.microServiceWebShop.service.APIService;

import hska.microServiceWebShop.ApiException;
import hska.microServiceWebShop.models.Error;
import hska.microServiceWebShop.models.Product;
import hska.microServiceWebShop.models.ProductQuery;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
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
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-04T00:32:34.965Z")

@Controller
public class ProductsApiController implements ProductsApi {

    private static final Logger log = LoggerFactory.getLogger(ProductsApiController.class);
    hska.microServiceWebShop.api.ProductsApi productsAPIClient = new hska.microServiceWebShop.api.ProductsApi();

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public ProductsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity addProduct(@ApiParam(value = "The inserted product" ,required=true )  @Valid @RequestBody Product product) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                Product p = productsAPIClient.addProduct(product);
                return new ResponseEntity<Product>(p, HttpStatus.OK);
            } catch (ApiException e) {
                e.printStackTrace();
                Error error = new Error();
                error.setDescription(e.getMessage());
                return new ResponseEntity<Error>(error, HttpStatus.valueOf(e.getCode()));
            }
        }

        Error error = new Error();
        error.description("wrong acept datatye");
        return new ResponseEntity<Error>(error,HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity deleteProduct(@ApiParam(value = "The id of the to be deleted product",required=true) @PathVariable("id") Long id) {
        String accept = request.getHeader("Accept");
        try {
            Product p = productsAPIClient.getProduct(id);
            productsAPIClient.deleteProduct(id);
            return new ResponseEntity<Product>(p, HttpStatus.OK);
        } catch (ApiException e) {
            e.printStackTrace();
            Error error = new Error();
            error.setDescription(e.getMessage());
            return new ResponseEntity<Error>(error,HttpStatus.valueOf(e.getCode()));
        }
    }

    public ResponseEntity getProduct(@ApiParam(value = "The id of the to be retrieved product",required=true) @PathVariable("id") Long id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                Product p = productsAPIClient.getProduct(id);
                return new ResponseEntity<Product>(p, HttpStatus.OK);
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

    public ResponseEntity queryProducts(@RequestHeader(value="Text",defaultValue = "") String text,
                                        @RequestHeader(value="Category",defaultValue = "") Long category,
                                        @RequestHeader(value="PriceMin",defaultValue = "") Double priceMin,
                                        @RequestHeader(value="PriceMax",defaultValue = "") Double priceMax) {
        ProductQuery query = new ProductQuery();
        query.setText(text);
        query.setCategory(category);
        query.setPriceMin(priceMin);
        query.setPriceMax(priceMax);
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                List<Product> ps = productsAPIClient.queryProducts(query);
                return new ResponseEntity<List<Product>>(ps, HttpStatus.OK);
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
