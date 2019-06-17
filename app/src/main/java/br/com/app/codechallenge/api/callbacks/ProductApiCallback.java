package br.com.app.codechallenge.api.callbacks;

import androidx.annotation.NonNull;
import br.com.app.codechallenge.api.models.Products;
import br.com.app.codechallenge.repositories.ProductRepositoryDelegate;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductApiCallback implements Callback<Products> {

    private final ProductRepositoryDelegate delegate;

    public ProductApiCallback(ProductRepositoryDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public void onResponse(@NonNull Call<Products> call, @NonNull Response<Products> response) {
        if(response.isSuccessful()) {
            delegate.onSuccess(response.body());
        } else {
            delegate.onFailure(response.code());
        }
    }

    @Override
    public void onFailure(@NonNull Call<Products> call, @NonNull Throwable t) {
        delegate.onFailure(t);
    }
}
