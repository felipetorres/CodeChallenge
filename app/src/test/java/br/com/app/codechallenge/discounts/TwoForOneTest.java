package br.com.app.codechallenge.discounts;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import br.com.app.codechallenge.infra.Util;
import br.com.app.codechallenge.models.Product;
import br.com.app.codechallenge.models.Purchase;

public class TwoForOneTest {

    @Test
    public void shouldNotApplyDiscount() {
        List<Purchase> purchases = Arrays.asList(
                new Purchase(new Product("VOUCHER", "", 5.0)),
                new Purchase(new Product("TSHIRT", "", 20.0)),
                new Purchase(new Product("MUG", "", 7.5)));

        TwoForOne twoForOne = new TwoForOne();
        Assert.assertFalse(twoForOne.apply(purchases));
        Assert.assertEquals(32.5, Util.sum(purchases, Purchase::getPrice), 0.001);
    }

    @Test
    public void shouldApplyDiscount() {
        List<Purchase> purchases = Arrays.asList(
                new Purchase(new Product("VOUCHER", "", 5.0)),
                new Purchase(new Product("TSHIRT", "", 20.0)),
                new Purchase(new Product("VOUCHER", "", 5.0)));

        TwoForOne twoForOne = new TwoForOne();
        Assert.assertTrue(twoForOne.apply(purchases));
        Assert.assertEquals(25.0, Util.sum(purchases, Purchase::getPrice), 0.001);
    }

    @Test
    public void shouldApplyDiscountOnlyForVoucherProducts() {
        List<Purchase> purchases = Arrays.asList(
                new Purchase(new Product("VOUCHER", "", 5.0)),
                new Purchase(new Product("TSHIRT", "", 20.0)),
                new Purchase(new Product("VOUCHER", "", 5.0)),
                new Purchase(new Product("VOUCHER", "", 5.0)),
                new Purchase(new Product("MUG", "", 7.5)),
                new Purchase(new Product("TSHIRT", "", 20.0)),
                new Purchase(new Product("TSHIRT", "", 20.0)));

        TwoForOne twoForOne = new TwoForOne();
        Assert.assertTrue(twoForOne.apply(purchases));
        Assert.assertEquals(77.5, Util.sum(purchases, Purchase::getPrice), 0.001);
    }
}
