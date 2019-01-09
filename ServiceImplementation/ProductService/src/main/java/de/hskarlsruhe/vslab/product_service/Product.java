package de.hskarlsruhe.vslab.product_service;

import java.util.Objects;

@javax.persistence.Entity
public class Product {

    /** for body, auswählen raw + JSON (application/json)
     * {
     *   "category": 0,
     *   "details": "string",
     *   "id": 0,
     *   "name": "string",
     *   "price": 0
     * }
     */
    @javax.persistence.Id
    @javax.persistence.GeneratedValue
    @javax.persistence.Column(name="id")
    private long id;

    @javax.persistence.Column(name="name")
    private String name;

    @javax.persistence.Column(name="price")
    private Double price;

    @javax.persistence.Column(name="category")
    private Long category;

    @javax.persistence.Column(name="details")
    private String details;

    public Product(){}

    public Product(String name, Double price, Long category, String details) {

        this.name = name;
        this.price = price;
        this.category = category;
        this.details = details;
    }

    public Product id(Long id) {
        this.id = id;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product product = (Product) o;
        return Objects.equals(this.id, product.id) &&
                Objects.equals(this.name, product.name) &&
                Objects.equals(this.price, product.price) &&
                Objects.equals(this.category, product.category) &&
                Objects.equals(this.details, product.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, category, details);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Product {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    price: ").append(toIndentedString(price)).append("\n");
        sb.append("    category: ").append(toIndentedString(category)).append("\n");
        sb.append("    details: ").append(toIndentedString(details)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
