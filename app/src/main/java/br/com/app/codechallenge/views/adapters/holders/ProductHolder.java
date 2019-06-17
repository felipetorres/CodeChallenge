package br.com.app.codechallenge.views.adapters.holders;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

import androidx.recyclerview.widget.RecyclerView;
import br.com.app.codechallenge.R;
import br.com.app.codechallenge.models.Product;
import br.com.app.codechallenge.viewModels.MainViewModel;

public class ProductHolder extends RecyclerView.ViewHolder {

    private final TextView name;
    private final TextView price;
    private final Button addButton;

    public ProductHolder(View layout) {
        super(layout);
        name = layout.findViewById(R.id.product_name);
        price = layout.findViewById(R.id.product_price);
        addButton = layout.findViewById(R.id.product_add_button);
    }

    public void init(final Product product, final MainViewModel vm) {
        name.setText(product.getName());
        price.setText(String.format(Locale.getDefault(), "%.2f€", product.getPrice()));

        addButton.setOnClickListener(v -> vm.addToCart(product));
    }
}
