package br.com.app.codechallenge.discounts;

import java.util.List;

import br.com.app.codechallenge.models.Purchase;

interface Discount {

    boolean apply(List<Purchase> purchases);
}
