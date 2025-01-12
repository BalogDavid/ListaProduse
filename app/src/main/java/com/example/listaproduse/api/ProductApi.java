package com.example.listaproduse.api;

import com.example.listaproduse.response.ProductResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProductApi {
    @GET("products/search")
    Call<ProductResponse> searchProducts(@Query("q") String query);
}
