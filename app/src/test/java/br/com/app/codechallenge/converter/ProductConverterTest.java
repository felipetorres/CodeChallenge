package br.com.app.codechallenge.converter;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.com.app.codechallenge.api.models.ProductApiModel;
import br.com.app.codechallenge.api.models.Products;
import br.com.app.codechallenge.models.Product;

public class ProductConverterTest {

    @Test
    public void shouldConvertApiModelToModel() {
        List<ProductApiModel> apiModels =
                Arrays.asList(new ProductApiModel("VOUCHER", "Voucher", 10.0),
                              new ProductApiModel("TSHIRT", "T-Shirt", 25.0));

        List<Product> products = new ProductConverter().convert(new Products(apiModels));

        Assert.assertEquals(2, products.size());

        Product first = products.get(0);
        Assert.assertEquals("VOUCHER", first.getCode());
        Assert.assertEquals("Voucher", first.getName());
        Assert.assertEquals(10.0, first.getPrice(), 0.0001);

        Product second = products.get(1);
        Assert.assertEquals("TSHIRT", second.getCode());
        Assert.assertEquals("T-Shirt", second.getName());
        Assert.assertEquals(25.0, second.getPrice(), 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotConvertIfNegativePrice() {
        List<ProductApiModel> apiModels =
                Collections.singletonList(new ProductApiModel("VOUCHER", "Voucher", -1.0));

        new ProductConverter().convert(new Products(apiModels));
    }
}
