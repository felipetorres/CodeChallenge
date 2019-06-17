package br.com.app.codechallenge.views.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import br.com.app.codechallenge.R;
import br.com.app.codechallenge.models.Product;
import br.com.app.codechallenge.viewModels.MainViewModel;
import br.com.app.codechallenge.views.adapters.holders.ProductHolder;

public class ProductsAdapter extends RecyclerView.Adapter<ProductHolder> {

    private final List<Product> products;
    private final MainViewModel vm;

    public ProductsAdapter(List<Product> products, MainViewModel vm) {
        this.products = products;
        this.vm = vm;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        holder.init(products.get(position), vm);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
