package de.hskarlsruhe.vslab;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;
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
    public ResponseEntity<Iterable<Product>> getProducts(@RequestHeader(name = "name", required = false) String name,
                                                         @RequestHeader(name = "price", required = false) Double price,
                                                         @RequestHeader(name = "category", required = false) Long category,
                                                         @RequestHeader(name = "details", required = false) String details) {


        // wenn alle felder von Product null sind und name ein leerer String ist
        // werden alle Producte zurück gegeben
        if((name == null || name.isEmpty()) && details == null && category == null && price == null)
            return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);

        // Suche nach Details
        else if((name == null || name.isEmpty()) && (details != null && !details.isEmpty()) && category == null && price == null)
        {
            Iterable<Product> foundProducts = StreamSupport
                    .stream(repo.findAll().spliterator(), true)
                    .filter(c -> c.getDetails().contains(details)).collect(Collectors.toList());
            return new ResponseEntity<>(foundProducts, HttpStatus.OK);
        }

        // Suche nach Categorie
        else if((name == null || name.isEmpty()) && details == null && category != null && price == null)
        {
            Iterable<Product> foundProducts = StreamSupport
                    .stream(repo.findAll().spliterator(), true)
                    .filter(c -> c.getCategory().equals(category)).collect(Collectors.toList());
            return new ResponseEntity<>(foundProducts, HttpStatus.OK);
        }

        // Suche nach Preis
        else if((name == null || name.isEmpty()) && details == null && category == null && price != null)
        {
            Iterable<Product> foundProducts = StreamSupport
                    .stream(repo.findAll().spliterator(), true)
                    .filter(c -> c.getPrice().equals(price)).collect(Collectors.toList());
            return new ResponseEntity<>(foundProducts, HttpStatus.OK);
        }

        // Suche nach Namen mit bestimmten Buchstaben
        else if(!(name == null) && details == null && category == null && price == null)
        {
            Iterable<Product> foundProducts = StreamSupport
                    .stream(repo.findAll().spliterator(), true)
                    .filter(c -> c.getName().contains(name)).collect(Collectors.toList());
            return new ResponseEntity<>(foundProducts, HttpStatus.OK);
        }

        else if(name == null  && details == null && category == null && price == null)
        {
            return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
        }

        // wenn das Produkt nicht gefunden wird
        else{
            Iterable<Product> foundProducts = new ArrayList<>();
            Product product = new Product("", 0.0, 0L, "");
            ((ArrayList<Product>) foundProducts).add(product);
            return new ResponseEntity<>(foundProducts, HttpStatus.NOT_FOUND);
        }

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
