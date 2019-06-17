package br.com.app.codechallenge.viewModels;

import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import br.com.app.codechallenge.discounts.Discounts;
import br.com.app.codechallenge.models.Product;
import br.com.app.codechallenge.models.StateLiveData;
import br.com.app.codechallenge.repositories.Repository;

public class FakeFactory implements ViewModelProvider.Factory {

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainViewModel(new FakeProductRepository(), new Discounts());
    }

    private class FakeProductRepository implements Repository {

        private StateLiveData<List<Product>> data = new StateLiveData<>();

        @Override
        public StateLiveData<List<Product>> getProducts() {
            List<Product> productList =
                    Arrays.asList(new Product("VOUCHER", "Voucher", 10.0),
                                  new Product("TSHIRT", "T-Shirt", 25.0),
                                  new Product("MUG", "Cabify Coffee Mug", 7.5));

            data.setSuccess(productList);

            return data;
        }
    }
}