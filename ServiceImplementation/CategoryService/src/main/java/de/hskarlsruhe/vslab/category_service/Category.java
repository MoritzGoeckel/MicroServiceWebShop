package de.hskarlsruhe.vslab.category_service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@javax.persistence.Entity
public class Category {

    @javax.persistence.Id
    @javax.persistence.GeneratedValue
    @javax.persistence.Column(name="id")
    @JsonIgnoreProperties(ignoreUnknown = true)
    private long id;

    @javax.persistence.Column(name="name")
    private String name;

    public Category(){}

    public Category(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
