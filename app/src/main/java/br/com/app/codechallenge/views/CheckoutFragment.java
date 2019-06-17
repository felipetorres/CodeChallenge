package br.com.app.codechallenge.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import br.com.app.codechallenge.R;
import br.com.app.codechallenge.viewModels.MainViewModel;
import br.com.app.codechallenge.views.adapters.CheckoutAdapter;

public class CheckoutFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_checkout, container, false);

        MainViewModel vm = ViewModelProviders.of(getActivity()).get(MainViewModel.class);

        RecyclerView checkoutList = layout.findViewById(R.id.checkout_list);
        checkoutList.setLayoutManager(new LinearLayoutManager(getContext()));
        CheckoutAdapter adapter = new CheckoutAdapter(vm.flatCart());
        checkoutList.setAdapter(adapter);

        final TextView cartTotal = layout.findViewById(R.id.cart_total);
        cartTotal.setText(vm.getTotalWithDiscount());

        return layout;
    }
}
