package br.com.app.codechallenge.api.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Products {

    @SerializedName("products") private List<ProductApiModel> products;

    public List<ProductApiModel> get() { return products; }

    public Products(List<ProductApiModel> products) { this.products = products; }
}
