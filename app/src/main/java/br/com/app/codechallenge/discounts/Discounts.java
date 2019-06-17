package br.com.app.codechallenge.discounts;

import java.util.Arrays;
import java.util.List;

import br.com.app.codechallenge.models.Purchase;

public class Discounts {

    private final List<Discount> discounts;

    public Discounts() {
        discounts = Arrays.asList(new TwoForOne(), new Bulk());
    }

    public void applyTo(List<Purchase> allPurchases) {
        discounts.forEach(discount -> discount.apply(allPurchases));
    }
}
