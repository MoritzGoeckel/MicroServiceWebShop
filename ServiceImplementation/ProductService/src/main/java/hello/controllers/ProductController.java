package hello.controllers;

import hello.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class ProductController {

    private List<Product> products = new ArrayList<>();
    private AtomicLong nexId = new AtomicLong();

    @GetMapping("/hello")
    public String getHelloMeaasge(){
        return "Hello Spring Boot world!";
    }

    @PostMapping("/products")
    public Product createNewProduct(@RequestBody Product product){

        // Set the ID for Product
        product.setId(nexId.incrementAndGet());

        products.add(product);
        return product;
    }

    @PostMapping("/products/{id}")
    public Product editOneProduct(
            @RequestBody Product newProduct,
            @PathVariable("id") long productId ){

        int internalId = (int)productId;
        internalId--;

        if(internalId >= 0 && products.size() >= productId){
            products.remove(internalId);
            newProduct.setId(productId);
            products.add(newProduct);
            return products.get(internalId);
        }
        else
            throw  new IllegalArgumentException();
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return products;
    }


    @GetMapping("/products/{id}")
    public Product getOneProduct(@PathVariable("id") long productId){
        int internalId = (int)productId;
        internalId--;

        if(internalId >= 0 && products.size() >= productId)
            return products.get((int)internalId);
        else
            throw  new IllegalArgumentException();
    }

    @DeleteMapping("/products/{id}")
    public void deleteOneProduct(@PathVariable("id") long productId){
        int internalId = (int)productId;
        internalId--;

        if(internalId >= 0 && products.size() >= productId)
            products.remove(internalId);
        else
            throw  new IllegalArgumentException();
    }

    // Create Exception Handler
    @ResponseStatus(value= HttpStatus.BAD_REQUEST,
                    reason = "Request ID not found.")
    @ExceptionHandler(IllegalArgumentException.class)
    public void badIdExceptionHandler(){
        //Nothing to do
    }
}
