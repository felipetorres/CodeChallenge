package br.com.app.codechallenge.views.adapters.holders;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import br.com.app.codechallenge.R;
import br.com.app.codechallenge.models.Product;
import br.com.app.codechallenge.models.Purchase;
import br.com.app.codechallenge.viewModels.MainViewModel;

public class PurchaseHolder extends RecyclerView.ViewHolder {

    private final Button buttonRemove;
    private final TextView value;
    private final Button buttonAdd;
    private final TextView productName;
    private final TextView productPrice;

    public PurchaseHolder(@NonNull View itemView) {
        super(itemView);
        productName = itemView.findViewById(R.id.product_name);
        productPrice = itemView.findViewById(R.id.product_price);
        buttonRemove = itemView.findViewById(R.id.counter_button_remove);
        value = itemView.findViewById(R.id.counter_value);
        buttonAdd = itemView.findViewById(R.id.counter_button_add);
    }

    public void init(LinkedHashMap<String, List<Purchase>> map, final String key, final MainViewModel vm, LifecycleOwner owner) {
        final List<Purchase> purchases = map.get(key);
        final Purchase purchase = purchases.get(0);
        Product product = purchase.getProduct();

        productName.setText(product.getName());
        productPrice.setText(String.format(Locale.getDefault(), "%.2f€", product.getPrice()));

        setCount(purchases.size());

        vm.countPurchasesWith(key).observe(owner, m -> { if(m.containsKey(key)) setCount(m.get(key)); });
        buttonRemove.setOnClickListener(v -> vm.minusOneFrom(purchase));
        buttonAdd.setOnClickListener(v -> vm.plusOneFrom(purchase));
    }

    private void setCount(int value) { this.value.setText(String.valueOf(value)); }
}
