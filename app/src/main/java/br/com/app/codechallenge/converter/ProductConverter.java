package br.com.app.codechallenge.converter;

import java.util.List;
import java.util.stream.Collectors;

import br.com.app.codechallenge.api.models.Products;
import br.com.app.codechallenge.models.Product;

public class ProductConverter {

    public List<Product> convert(Products products) {
        return products.get().stream()
                .map(p -> new Product(p.getCode(), p.getName(), p.getPrice()))
                .collect(Collectors.toList());
    }
}
