package br.com.app.codechallenge.viewModels;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import br.com.app.codechallenge.api.Api;
import br.com.app.codechallenge.discounts.Discounts;
import br.com.app.codechallenge.infra.Util;
import br.com.app.codechallenge.models.Product;
import br.com.app.codechallenge.models.Purchase;
import br.com.app.codechallenge.models.StateLiveData;
import br.com.app.codechallenge.repositories.ProductRepository;
import br.com.app.codechallenge.repositories.Repository;

public class MainViewModel extends ViewModel {

    private final Repository repository;
    private final Discounts discounts;

    private final LinkedHashMap<String, List<Purchase>> purchases = new LinkedHashMap<>();
    private final MutableLiveData<Map<String, Integer>> count = new MutableLiveData<>();
    private final MutableLiveData<String> total = new MutableLiveData<>();

    public MainViewModel(Repository repository, Discounts discounts) {
        this.repository = repository;
        this.discounts = discounts;
    }

    public StateLiveData<List<Product>> getProducts() {
        return repository.getProducts();
    }

    public void addToCart(@NonNull Product product) {
        if (purchases.containsKey(product.getCode())) {
            purchases.put(product.getCode(), Util.add(product, purchases));
        } else {
            purchases.put(product.getCode(), Collections.singletonList(new Purchase(product)));
        }
    }

    public LinkedHashMap<String, List<Purchase>> getCart() {
        return purchases;
    }

    public List<Purchase> flatCart() {
        return Util.flat(purchases.values());
    }

    public void plusOneFrom(Purchase purchase) {
        addToCart(purchase.getProduct());
        countPurchasesWith(purchase.getProduct().getCode());
        getOriginalTotal();
    }

    public void minusOneFrom(Purchase purchase) {
        Product product = purchase.getProduct();

        if (purchases.containsKey(product.getCode())) {
            List<Purchase> purchasesMinusOne = Util.remove(product, purchases);
            if (purchasesMinusOne.isEmpty()) {
                purchases.remove(product.getCode());
            } else {
                purchases.put(product.getCode(), purchasesMinusOne);
            }
        }
        countPurchasesWith(purchase.getProduct().getCode());
        getOriginalTotal();
    }

    public LiveData<Map<String, Integer>> countPurchasesWith(String productCode) {
        List<Purchase> list = this.purchases.get(productCode);
        count.postValue(Collections.singletonMap(productCode, list == null ? 0 : list.size()));
        return count;
    }

    public LiveData<String> getOriginalTotal() {
        total.setValue(formattedTotal(flatCart()));
        return total;
    }

    public String getTotalWithDiscount() {
        List<Purchase> allPurchases = flatCart();
        discounts.applyTo(allPurchases);
        return formattedTotal(allPurchases);
    }

    private String formattedTotal(List<Purchase> allPurchases) {
        Double sum = Util.sum(allPurchases, Purchase::getPrice);
        return String.format(Locale.getDefault(), "%.2f€", sum);
    }

    public static class Factory implements ViewModelProvider.Factory {

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (!modelClass.equals(MainViewModel.class)) {
                throw new IllegalArgumentException("Wrong model class");
            }

            ProductRepository repo = new ProductRepository(new Api().get());
            Discounts discounts = new Discounts();

            return (T) new MainViewModel(repo, discounts);
        }
    }
}
