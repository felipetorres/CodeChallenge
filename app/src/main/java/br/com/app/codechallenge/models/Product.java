package br.com.app.codechallenge.models;

public class Product {

    private String code;
    private String name;
    private Double price;

    public Product(String code, String name, Double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price should be positive");
        }
        this.code = code;
        this.name = name;
        this.price = price;
    }

    public String getCode() { return code; }

    public String getName() { return name; }

    public Double getPrice() { return price; }
}
