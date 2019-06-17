package br.com.app.codechallenge.viewModels;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import java.util.List;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import br.com.app.codechallenge.models.Product;
import br.com.app.codechallenge.models.Purchase;
import br.com.app.codechallenge.models.StateLiveData;

public class MainViewModelTest {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();
    private MainViewModel vm;

    @Before
    public void setUp() {
        vm = new FakeFactory().create(MainViewModel.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotBeCreatedWithWrongClass() {
        new MainViewModel.Factory().create(WrongViewModel.class);
    }

    @Test
    public void shouldGetProducts() {
        StateLiveData<List<Product>> data = vm.getProducts();
        List<Product> products = data.getValue().getData();

        Assert.assertEquals(3, products.size());
    }

    @Test
    public void shouldAddProductsToCart() {
        vm.addToCart(new Product("VOUCHER", "Voucher", 5.0));
        vm.addToCart(new Product("VOUCHER", "Voucher", 5.0));

        List<Purchase> purchases = vm.flatCart();
        Assert.assertEquals(2, purchases.size());

        Purchase first = purchases.get(0);
        Assert.assertEquals(5.0, first.getPrice(), 0.001);

        Product firstProduct = first.getProduct();
        Assert.assertEquals("VOUCHER", firstProduct.getCode());
        Assert.assertEquals("Voucher", firstProduct.getName());
        Assert.assertEquals(5.0, firstProduct.getPrice(), 0.001);

        Purchase second = purchases.get(1);
        Assert.assertEquals(5.0, first.getPrice(), 0.001);

        Product secondProduct = second.getProduct();
        Assert.assertEquals("VOUCHER", secondProduct.getCode());
        Assert.assertEquals("Voucher", secondProduct.getName());
        Assert.assertEquals(5.0, secondProduct.getPrice(), 0.001);
    }

    @Test
    public void shouldFormatTotal() {
        vm.addToCart(new Product("VOUCHER", "Voucher", 5.0));
        vm.addToCart(new Product("MUG", "Mug", 7.5));

        LiveData<String> total = vm.getOriginalTotal();

        Assert.assertEquals("12.50€", total.getValue());
    }

    @Test
    public void shouldAddPurchasesOneByOne() {
        Purchase purchase = new Purchase(new Product("VOUCHER", "Voucher", 5.0));
        vm.plusOneFrom(purchase);
        vm.plusOneFrom(purchase);

        List<Purchase> purchases = vm.flatCart();
        Assert.assertEquals(2, purchases.size());

        LiveData<String> total = vm.getOriginalTotal();
        Assert.assertEquals("10.00€", total.getValue());

        Assert.assertEquals("5.00€", vm.getTotalWithDiscount());
    }

    @Test
    public void shouldRemovePurchasesOneByOne() {
        Purchase purchase = new Purchase(new Product("VOUCHER", "Voucher", 5.0));
        vm.minusOneFrom(purchase);
        vm.minusOneFrom(purchase);

        List<Purchase> purchases = vm.flatCart();
        Assert.assertEquals(0, purchases.size());

        LiveData<String> total = vm.getOriginalTotal();
        Assert.assertEquals("0.00€", total.getValue());
        Assert.assertEquals("0.00€", vm.getTotalWithDiscount());
    }

    private class WrongViewModel extends ViewModel {}
}
