package de.hskarlsruhe.vslab.product_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ResponseEntity<Product> postProduct(@RequestBody Product product) {

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
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Product>> getProducts(@RequestBody Product product) {

        String name = product.getName();
        String details = product.getDetails();
        Long category = product.getCategory();
        Double price = product.getPrice();

        // wenn alle felder von Product null sind und name ein leerer String ist
        // werden alle Producte zurück gegeben
        if((name == null || name.isEmpty()) && details == null && category == null && price == null)
            return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);

        // Suche nach Details
        else if((name == null || name.isEmpty()) && (details != null && !details.isEmpty()) && category == null && price == null)
        {
            Iterable<Product> foundProducts = StreamSupport
                    .stream(repo.findAll().spliterator(), true)
                    .filter(c -> c.getDetails().contains(product.getDetails())).collect(Collectors.toList());
            return new ResponseEntity<>(foundProducts, HttpStatus.OK);
        }

        // Suche nach Categorie
        else if((name == null || name.isEmpty()) && details == null && category != null && price == null)
        {
            Iterable<Product> foundProducts = StreamSupport
                    .stream(repo.findAll().spliterator(), true)
                    .filter(c -> c.getCategory().equals(product.getCategory())).collect(Collectors.toList());
            return new ResponseEntity<>(foundProducts, HttpStatus.OK);
        }

        // Suche nach Preis
        else if((name == null || name.isEmpty()) && details == null && category != null && price == null)
        {
            Iterable<Product> foundProducts = StreamSupport
                    .stream(repo.findAll().spliterator(), true)
                    .filter(c -> c.getPrice().equals(product.getPrice())).collect(Collectors.toList());
            return new ResponseEntity<>(foundProducts, HttpStatus.OK);
        }

        // Suche nach Namen mit bestimmten Buchstaben
        else if((name == null || name.isEmpty()) && details == null && category != null && price == null)
        {
            Iterable<Product> foundProducts = StreamSupport
                    .stream(repo.findAll().spliterator(), true)
                    .filter(c -> c.getName().contains(product.getName())).collect(Collectors.toList());
            return new ResponseEntity<>(foundProducts, HttpStatus.OK);
        }

        else
        {
            Iterable<Product> foundProducts = new ArrayList<>();
            Product p = new Product("",0.0,0L,"");
            ((ArrayList<Product>) foundProducts).add(p);
            return new ResponseEntity<>(foundProducts, HttpStatus.OK);
        }

    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> optionalProduct = repo.findById(id);

        return optionalProduct
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
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
