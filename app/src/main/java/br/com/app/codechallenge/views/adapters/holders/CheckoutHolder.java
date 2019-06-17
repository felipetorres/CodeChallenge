package br.com.app.codechallenge.views.adapters.holders;

import android.view.View;
import android.widget.TextView;

import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import br.com.app.codechallenge.R;
import br.com.app.codechallenge.models.Purchase;

public class CheckoutHolder extends RecyclerView.ViewHolder {

    private final TextView productName;
    private final TextView productPrice;

    public CheckoutHolder(@NonNull View itemView) {
        super(itemView);
        productName = itemView.findViewById(R.id.checkout_product_name);
        productPrice = itemView.findViewById(R.id.checkout_product_price);
    }

    public void init(Purchase purchase) {
        productName.setText(purchase.getProduct().getName());
        productPrice.setText(String.format(Locale.getDefault(), "%.2f€", purchase.getPrice()));
    }
}
