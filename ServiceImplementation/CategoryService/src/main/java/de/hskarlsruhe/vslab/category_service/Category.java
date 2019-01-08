package de.hskarlsruhe.vslab.category_service;

@javax.persistence.Entity
public class Category {

    @javax.persistence.Id
    @javax.persistence.GeneratedValue
    @javax.persistence.Column(name="id")
    private long id;

    @javax.persistence.Column(name="name")
    private String name;

    public Category(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
