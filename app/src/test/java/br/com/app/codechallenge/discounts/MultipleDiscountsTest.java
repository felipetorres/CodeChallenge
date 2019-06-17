package br.com.app.codechallenge.discounts;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import br.com.app.codechallenge.infra.Util;
import br.com.app.codechallenge.models.Product;
import br.com.app.codechallenge.models.Purchase;

public class MultipleDiscountsTest {

    @Test
    public void shouldApplyMultipleDiscounts() {
        List<Purchase> purchases = Arrays.asList(
                new Purchase(new Product("VOUCHER", "", 5.0)),
                new Purchase(new Product("TSHIRT", "", 20.0)),
                new Purchase(new Product("VOUCHER", "", 5.0)),
                new Purchase(new Product("VOUCHER", "", 5.0)),
                new Purchase(new Product("MUG", "", 7.5)),
                new Purchase(new Product("TSHIRT", "", 20.0)),
                new Purchase(new Product("TSHIRT", "", 20.0)));

        Bulk bulk = new Bulk();
        Assert.assertTrue(bulk.apply(purchases));

        TwoForOne twoForOne = new TwoForOne();
        Assert.assertTrue(twoForOne.apply(purchases));

        Assert.assertEquals(74.5, Util.sum(purchases, Purchase::getPrice), 0.001);
    }
}
