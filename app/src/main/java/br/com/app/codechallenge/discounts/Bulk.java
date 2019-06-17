package br.com.app.codechallenge.discounts;

import java.util.ArrayList;
import java.util.List;

import br.com.app.codechallenge.models.Purchase;

class Bulk implements Discount {

    @Override
    public boolean apply(List<Purchase> purchases) {
        if(purchases.size() <= 2) { return false; }

        List<Purchase> tshirts = filter(purchases);
        if(tshirts.size() <= 2) { return false; }

        tshirts.forEach(t -> t.setPrice(19.0));

        return true;
    }

    private List<Purchase> filter(List<Purchase> purchases) {
        List<Purchase> tshirts = new ArrayList<>();

        purchases.stream()
                .filter(p -> "TSHIRT".equals(p.getProduct().getCode()))
                .forEach(tshirts::add);

        return tshirts;
    }
}
