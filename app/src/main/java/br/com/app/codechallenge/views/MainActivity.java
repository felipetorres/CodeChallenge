package br.com.app.codechallenge.views;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import br.com.app.codechallenge.ProductsResourceObserver;
import br.com.app.codechallenge.R;
import br.com.app.codechallenge.viewModels.MainViewModel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainViewModel.Factory factory = new MainViewModel.Factory();
        MainViewModel vm = ViewModelProviders.of(this, factory).get(MainViewModel.class);

        RecyclerView productList = findViewById(R.id.product_list);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        productList.setLayoutManager(manager);

        vm.getProducts().observe(this, new ProductsResourceObserver(productList, vm));

        FloatingActionButton cartButton = findViewById(R.id.product_cart);
        cartButton.setOnClickListener(this);
    }

    public void onClick(View v) {
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(android.R.id.content, new CartFragment());
        tx.addToBackStack(null);
        tx.commit();
    }
}
