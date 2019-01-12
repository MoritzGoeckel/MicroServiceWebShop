package hska.microServiceWebShop.service.SanityService;

import hska.microServiceWebShop.ApiException;
import hska.microServiceWebShop.models.Error;
import hska.microServiceWebShop.models.Product;
import hska.microServiceWebShop.models.ProductQuery;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ProductsApiController{
    hska.microServiceWebShop.api.ProductsApi productsAPIClient = new hska.microServiceWebShop.api.ProductsApi();

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public ProductsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity addProduct(@RequestBody Product product) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                Product p = productsAPIClient.addProduct(product);
                return ResponseEntity.ok().body(p);
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

    public ResponseEntity deleteProduct(@PathVariable("id") Long id) {
        String accept = request.getHeader("Accept");
        try {
            Product p = productsAPIClient.getProduct(id);
            productsAPIClient.deleteProduct(id);
            return ResponseEntity.ok().body(p);
        } catch (ApiException e) {
            e.printStackTrace();
            Error error = new Error();
            error.setDescription(e.getMessage());
            return ResponseEntity.status(e.getCode()).body(error);
        }
    }

    public ResponseEntity getProduct(@PathVariable("id") Long id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                Product p = productsAPIClient.getProduct(id);
                return ResponseEntity.ok().body(p);
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
                return ResponseEntity.ok().body(ps);
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

}
