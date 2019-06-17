package br.com.app.codechallenge;

import android.content.Context;
import android.widget.Toast;

import java.util.List;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import br.com.app.codechallenge.models.Product;
import br.com.app.codechallenge.models.Resource;
import br.com.app.codechallenge.viewModels.MainViewModel;
import br.com.app.codechallenge.views.adapters.ProductsAdapter;

public class ProductsResourceObserver implements Observer<Resource<List<Product>>> {

    private final RecyclerView productList;
    private final MainViewModel vm;

    public ProductsResourceObserver(RecyclerView productList, MainViewModel vm) {
        this.productList = productList;
        this.vm = vm;
    }

    @Override
    public void onChanged(Resource<List<Product>> resource) {
        switch (resource.getStatus()) {
            case LOADING: loading(); break;
            case SUCCESS: success(resource.getData()); break;
            case FAILURE: failure(resource.getErrorMessage()); break;
        }
    }

    private void loading() {
        Context ctx = productList.getContext();
        Toast.makeText(ctx, ctx.getText(R.string.loading), Toast.LENGTH_SHORT).show();
    }

    private void success(List<Product> products) {
        ProductsAdapter adapter = new ProductsAdapter(products, vm);
        productList.setAdapter(adapter);
    }

    private void failure(String errorMessage) {
        Toast.makeText(productList.getContext(), errorMessage, Toast.LENGTH_LONG).show();
    }
}
