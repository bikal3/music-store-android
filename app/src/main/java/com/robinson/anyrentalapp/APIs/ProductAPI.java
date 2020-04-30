package com.robinson.anyrentalapp.APIs;

import com.robinson.anyrentalapp.Response.ProductResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductAPI {
    @GET("productlistbyfeed/{feedid}")
    Call<ProductResponse> getproduct(@Path("feedid") String feedid);

    @GET("productDetail/{id}")
    Call<ProductResponse> getproductbyid(@Path("id") String id);

    @GET("productlist")
    Call<ProductResponse> getallproduct();

}
