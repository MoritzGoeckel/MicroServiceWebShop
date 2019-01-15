package de.hskarlsruhe.vslab;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@RestController
public class ProductEndpoints {

    @Autowired
    private ProductRepo repo;

    @RequestMapping(value = "/products",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<Product> postProduct(@RequestHeader(name = "name", required = true) String name,
                                               @RequestHeader(name = "price", required = true) Double price,
                                               @RequestHeader(name = "category", required = true) Long category,
                                               @RequestHeader(name = "details", required = true) String details) {


        Product product = new Product(name, price, category, details);

        boolean nameAlreadyExists = StreamSupport
                .stream(repo.findAll().spliterator(), true)
                .anyMatch(c -> c.getName().equals(product.getName()));

        if(nameAlreadyExists)
            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);

        Product newProduct = product;
        newProduct = repo.save(product);
        return new ResponseEntity<>(newProduct, HttpStatus.OK);
    }

    // liefert einen bestimmten Product zurück, mit bestimmten Namen
    // wenn product leer ist, werden alle Produkte zurück geliefert
    @RequestMapping(value = "/products", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getProducts(@RequestHeader(name = "text", required = false) String text,
                                                         @RequestHeader(name = "min", required = false) Double min,
                                                         @RequestHeader(name = "max", required = false) Double max,
                                                         @RequestHeader(name = "category", required = false) String category) {


        Stream<Product> allFoundProductsSream = StreamSupport
                .stream(repo.findAll().spliterator(), true);

        // Suche nach text - check
        if(text != null)
        {
            allFoundProductsSream = allFoundProductsSream
                    .filter(c -> (c.getDetails().contains(text) || c.getName().contains(text)));
        }

        // Suche nach min -
        if( min != null)
        {
            allFoundProductsSream = allFoundProductsSream
                    .filter(c -> c.getPrice() > min);
        }

        // Suche nach max
        if(max != null)
        {
            allFoundProductsSream = allFoundProductsSream
                    .filter(c -> c.getPrice() < max);
        }

        // Suche nach category
        if(category != null)
        {
            allFoundProductsSream = allFoundProductsSream
                    .filter(c -> c.getCategory().equals(category));
        }

        List<Product> foundProducts = allFoundProductsSream.collect(Collectors.toList());

        return new ResponseEntity<List<Product>>(foundProducts, HttpStatus.OK);

    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> optionalProduct = repo.findById(id);

        return optionalProduct
                .map(product -> new ResponseEntity<Product>(product, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<Product>(HttpStatus.NOT_FOUND));

    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Iterable<Product>> deleteProduct(@PathVariable Long id) {
        if(repo.existsById(id)){
            repo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
