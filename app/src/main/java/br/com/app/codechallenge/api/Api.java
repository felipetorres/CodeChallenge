package br.com.app.codechallenge.api;

import br.com.app.codechallenge.api.models.Products;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class Api {

    private final Service service;

    public Api() {
        service = new Retrofit.Builder().baseUrl("https://api.myjson.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(Service.class);
    }

    public Service get() { return service; }

    public interface Service {

        @GET("/bins/4bwec") Call<Products> getProducts();
    }
}
