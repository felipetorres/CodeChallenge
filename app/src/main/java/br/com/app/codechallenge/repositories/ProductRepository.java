package br.com.app.codechallenge.repositories;

import java.util.List;

import br.com.app.codechallenge.api.Api;
import br.com.app.codechallenge.api.models.Products;
import br.com.app.codechallenge.api.callbacks.ProductApiCallback;
import br.com.app.codechallenge.converter.ProductConverter;
import br.com.app.codechallenge.models.Product;
import br.com.app.codechallenge.models.StateLiveData;

public class ProductRepository implements Repository, ProductRepositoryDelegate {

    private StateLiveData<List<Product>> products = new StateLiveData<>();
    private ProductConverter converter = new ProductConverter();
    private final Api.Service service;

    public ProductRepository(Api.Service service) {
        this.service = service;
    }

    public StateLiveData<List<Product>> getProducts() {
        products.setLoading();
        service.getProducts().enqueue(new ProductApiCallback(this));
        return products;
    }

    @Override
    public void onSuccess(Products productApiModels) {
        products.setSuccess(converter.convert(productApiModels));
    }

    @Override
    public void onFailure(int statusCode) {
        products.setFailure(statusCode);
    }

    @Override
    public void onFailure(Throwable error) {
        products.setFailure();
    }
}
