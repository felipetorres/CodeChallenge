package br.com.app.codechallenge.views.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedHashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import br.com.app.codechallenge.R;
import br.com.app.codechallenge.models.Purchase;
import br.com.app.codechallenge.viewModels.MainViewModel;
import br.com.app.codechallenge.views.adapters.holders.PurchaseHolder;

public class CartAdapter extends RecyclerView.Adapter<PurchaseHolder> {

    private final String[] keys;
    private final MainViewModel vm;
    private final LifecycleOwner owner;
    private final LinkedHashMap<String, List<Purchase>> purchases;

    public CartAdapter(LinkedHashMap<String, List<Purchase>> purchases, MainViewModel vm, LifecycleOwner owner) {
        this.purchases = purchases;
        this.keys = purchases.keySet().toArray(new String[]{});
        this.vm = vm;
        this.owner = owner;
    }

    @NonNull
    @Override
    public PurchaseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new PurchaseHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull PurchaseHolder holder, int position) {
        holder.init(purchases, keys[position], vm, owner);
    }

    @Override
    public int getItemCount() { return keys.length; }
}
