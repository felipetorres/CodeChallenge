package br.com.app.codechallenge.api;

import com.google.gson.Gson;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import br.com.app.codechallenge.api.models.ProductApiModel;
import br.com.app.codechallenge.api.models.Products;

public class ApiTest {

    @Test
    public void shouldBuildApiResponseModel() {
        Products products = new Gson().fromJson(FakeResponse.get(), Products.class);

        List<ProductApiModel> apiModels = products.get();

        Assert.assertEquals(3, apiModels.size());

        ProductApiModel first = apiModels.get(0);
        Assert.assertEquals("VOUCHER", first.getCode());
        Assert.assertEquals("Cabify Voucher", first.getName());
        Assert.assertEquals(5, first.getPrice(), 0.0001);

        ProductApiModel second = apiModels.get(1);
        Assert.assertEquals("TSHIRT", second.getCode());
        Assert.assertEquals("Cabify T-Shirt", second.getName());
        Assert.assertEquals(20, second.getPrice(), 0.0001);

        ProductApiModel third = apiModels.get(2);
        Assert.assertEquals("MUG", third.getCode());
        Assert.assertEquals("Cabify Coffee Mug", third.getName());
        Assert.assertEquals(7.5, third.getPrice(), 0.0001);
    }

    private static class FakeResponse {

        static String get() {
            return "{\"products\":[{\"code\":\"VOUCHER\",\"name\":\"Cabify Voucher\",\"price\":5}," +
                                  "{\"code\":\"TSHIRT\",\"name\":\"Cabify T-Shirt\",\"price\":20}," +
                                  "{\"code\":\"MUG\",\"name\":\"Cabify Coffee Mug\",\"price\":7.5}]}";
        }
    }
}
