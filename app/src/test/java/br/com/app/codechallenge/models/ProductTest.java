package br.com.app.codechallenge.models;

import org.junit.Test;

public class ProductTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAcceptNegativePrice() {
        new Product("VOUCHER", "Voucher", -1.0);
    }
}