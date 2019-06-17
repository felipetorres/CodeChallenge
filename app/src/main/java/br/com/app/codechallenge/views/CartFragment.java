package br.com.app.codechallenge.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import br.com.app.codechallenge.R;
import br.com.app.codechallenge.viewModels.MainViewModel;
import br.com.app.codechallenge.views.adapters.CartAdapter;

public class CartFragment extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_cart, container, false);

        RecyclerView cartList = layout.findViewById(R.id.cart_list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        cartList.setLayoutManager(manager);

        MainViewModel vm = ViewModelProviders.of(getActivity()).get(MainViewModel.class);

        CartAdapter adapter = new CartAdapter(vm.getCart(), vm, getViewLifecycleOwner());
        cartList.setAdapter(adapter);

        final TextView total = layout.findViewById(R.id.cart_total);

        vm.getOriginalTotal().observe(getViewLifecycleOwner(), total::setText);

        Button cartCheckoutButton = layout.findViewById(R.id.cart_checkout);
        cartCheckoutButton.setOnClickListener(this);

        return layout;
    }

    @Override
    public void onClick(View view) {
        FragmentTransaction tx = getFragmentManager().beginTransaction();
        tx.replace(android.R.id.content, new CheckoutFragment());
        tx.addToBackStack(null);
        tx.commit();
    }
}
