package de.hskarlsruhe.vslab.category_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class CategoryEndpoints {

    @Autowired
    private CategoryRepo repo;

    @RequestMapping(value = "/categories", method = RequestMethod.POST)
    public ResponseEntity<Category> postCategory(@RequestBody String name) {
        boolean nameAlreadyExists = StreamSupport
                .stream(repo.findAll().spliterator(), true)
                .anyMatch(c -> c.getName().equals(name));

        if(nameAlreadyExists)
            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);

        Category category = new Category(name);
        category = repo.save(category);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Category>> getCategories(@RequestHeader(defaultValue = "") String query) {
        if(query == null || query.isEmpty())
            return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);

        Iterable<Category> foundCategories = StreamSupport
                .stream(repo.findAll().spliterator(), true)
                .filter(c -> c.getName().contains(query)).collect(Collectors.toList());

        return new ResponseEntity<>(foundCategories, HttpStatus.OK);
    }

    @RequestMapping(value = "/categories/{id}", method = RequestMethod.GET)
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Optional<Category> optionalCategory = repo.findById(id);

        return optionalCategory
                .map(category -> new ResponseEntity<>(category, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/categories/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Iterable<Category>> deleteCategory(@PathVariable Long id) {
        if(repo.existsById(id)){
            repo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
