package br.com.app.codechallenge.discounts;

import java.util.List;

import br.com.app.codechallenge.models.Purchase;

class TwoForOne implements Discount {

    @Override
    public boolean apply(List<Purchase> purchases) {
        if(purchases.size() < 2) { return false; }

        boolean applied = false;
        int voucherCount = 0;
        for (Purchase purchase : purchases) {
            if(purchase.getProduct().getCode().equals("VOUCHER")) {
                voucherCount++;
                if(voucherCount == 2) {
                    purchase.setPrice(0.0);
                    voucherCount = 0;
                    applied = true;
                }
            }
        }
        return applied;
    }
}
