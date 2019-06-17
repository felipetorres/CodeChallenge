package br.com.app.codechallenge.models;

public class Purchase {

    private final Product product;
    private Double price;

    public Purchase(Product product) {
        this.product = product;
        this.price = product.getPrice();
    }

    public void setPrice(Double price) { this.price = price; }

    public Double getPrice() { return price; }

    public Product getProduct() {
        return new Product(this.product.getCode(),
                           this.product.getName(),
                           this.product.getPrice());
    }
}
