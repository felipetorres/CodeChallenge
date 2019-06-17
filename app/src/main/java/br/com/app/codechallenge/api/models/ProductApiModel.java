package br.com.app.codechallenge.api.models;

import com.google.gson.annotations.SerializedName;

public class ProductApiModel {

    @SerializedName("code") private String code;
    @SerializedName("name") private String name;
    @SerializedName("price") private Double price;

    public ProductApiModel(String code, String name, Double price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }

    public Double getPrice() { return price; }

    public String getName() { return name; }

    public String getCode() { return code; }
}
