package hello.model;

public class Product {
    private long id;
    private String name;
    private String categorie;

    public Product(){}

    public Product(long id, String name, String categorie)
    {
        this.id = id;
        this.name = name;
        this.categorie = categorie;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
}
