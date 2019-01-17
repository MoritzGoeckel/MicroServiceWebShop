package hska.microServiceWebShop.service.SanityService;

import hska.microServiceWebShop.Clients.ApiException;
import hska.microServiceWebShop.Clients.CategoryServiceClient;
import hska.microServiceWebShop.Clients.ProductServiceClient;
import hska.microServiceWebShop.models.*;
import hska.microServiceWebShop.models.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class ProductsApiController implements ProductsApi{

    @Autowired
    private ProductServiceClient productsAPIClient;

    @Autowired
    private CategoryServiceClient categoriesAPIClient;
    
    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public ProductsApiController(HttpServletRequest request) {
        this.request = request;
    }

    public ResponseEntity addProduct(@RequestBody ProductBackend product) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
            	Category c = categoriesAPIClient.getCategoryById(product.getCategory().intValue());
                ProductBackend p = productsAPIClient.postProduct(product.getName(),product.getPrice(),product.getCategory(),product.getDetails());
                Product productNew = new Product();
                productNew.setId(p.getId());
                productNew.setName(p.getName());
                productNew.setPrice(p.getPrice());
                productNew.setDetails(p.getDetails());
                productNew.setCategory(c);
                return new ResponseEntity<Product>(productNew, HttpStatus.OK);
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

    public ResponseEntity deleteProduct(@PathVariable("id") Long id) {
        String accept = request.getHeader("Accept");
        try {
            productsAPIClient.deleteProductById(id.intValue());
            return new ResponseEntity(HttpStatus.OK);
        } catch (ApiException e) {
            e.printStackTrace();
            Error error = new Error();
            error.setDescription(e.getMessage());
            return new ResponseEntity<Error>(error,HttpStatus.valueOf(e.getCode()));
        }
    }

    public ResponseEntity getProduct(@PathVariable("id") Long id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                ProductBackend p = productsAPIClient.getProductById(id.intValue());
                Category c = categoriesAPIClient.getCategoryById(p.getCategory().intValue());
                Product productNew = new Product();
                productNew.setId(p.getId());
                productNew.setName(p.getName());
                productNew.setPrice(p.getPrice());
                productNew.setDetails(p.getDetails());
                productNew.setCategory(c);
                return new ResponseEntity<Product>(productNew, HttpStatus.OK);
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
                List<ProductBackend> ps = Arrays.asList(productsAPIClient.getProducts(query.getText(),query.getPriceMin(),query.getPriceMax(),query.getCategory()));
                List<Product> psNew = new ArrayList<>(ps.size());
                for(ProductBackend p : ps){
                    Category c = categoriesAPIClient.getCategoryById(p.getCategory().intValue());
                    Product productNew = new Product();
                    productNew.setId(p.getId());
                    productNew.setName(p.getName());
                    productNew.setPrice(p.getPrice());
                    productNew.setDetails(p.getDetails());
                    productNew.setCategory(c);
                    psNew.add(productNew);
                }
                return new ResponseEntity<List<Product>>(psNew, HttpStatus.OK);
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
