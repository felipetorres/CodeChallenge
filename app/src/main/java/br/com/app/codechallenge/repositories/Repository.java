package br.com.app.codechallenge.repositories;

import java.util.List;

import br.com.app.codechallenge.models.Product;
import br.com.app.codechallenge.models.StateLiveData;

public interface Repository {

    StateLiveData<List<Product>> getProducts();
}
