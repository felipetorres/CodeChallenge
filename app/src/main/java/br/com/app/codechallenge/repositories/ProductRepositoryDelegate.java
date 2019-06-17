package br.com.app.codechallenge.repositories;

import br.com.app.codechallenge.api.models.Products;

public interface ProductRepositoryDelegate {

    void onSuccess(Products products);

    void onFailure(int statusCode);

    void onFailure(Throwable t);
}
