package com.robinson.anyrentalapp.APIs;

import com.robinson.anyrentalapp.Response.CartResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CartAPI {
    @FormUrlEncoded
    @POST("cartpost")
    Call<CartResponse> addtocart(@Field("productId") String productId,
                                 @Field("userId") String userId);

    @GET("cart-items/{userID}")
    Call<CartResponse> getCart(@Path("userID") String userid);

    @GET("cart-total/{userID}")
    Call<CartResponse> getCartTotal(@Path("userID") String userid);

    @PUT("cart-delete/{userId}/{prodId}")
    Call<CartResponse> deletecart(@Path("userId") String userid, @Path("prodId") String prodId);
}
