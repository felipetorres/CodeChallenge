package br.com.app.codechallenge.views.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import br.com.app.codechallenge.R;
import br.com.app.codechallenge.models.Purchase;
import br.com.app.codechallenge.views.adapters.holders.CheckoutHolder;

public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutHolder> {

    private final List<Purchase> cart;

    public CheckoutAdapter(List<Purchase> cart) {
        this.cart = cart;
    }

    @NonNull
    @Override
    public CheckoutHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_checkout, parent, false);
        return new CheckoutHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckoutHolder holder, int position) {
        holder.init(cart.get(position));
    }

    @Override
    public int getItemCount() { return cart.size(); }
}
