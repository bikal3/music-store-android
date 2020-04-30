package com.robinson.anyrentalapp.APIs;

import com.robinson.anyrentalapp.Model.User;
import com.robinson.anyrentalapp.Response.UserResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface UserAPI {

    // register a new user
    @POST("users/registration")
    Call<UserResponse> registerUser(@Body User user);

    //User Login
    @FormUrlEncoded
    @POST("users/login")
    Call<UserResponse> loginUsers(@Field("email") String email,
                                  @Field("password") String password);

    //User Get Profile
    @FormUrlEncoded
    @POST("users/profile")
    Call<UserResponse> loadprofile(@Field("_id") String id,
                                   @Field("token") String token);

    //User Profile Update
    @FormUrlEncoded
    @PUT("users/userupdate")
    Call<UserResponse> updateProfile(@Field("_id") String id,
                                     @Field("token") String token,
                                     @Field("firstname") String firstname,
                                     @Field("lastname") String lastname,
                                     @Field("email") String email,
                                     @Field("phoneno") String phoneno,
                                     @Field("address") String address,
                                     @Field("image") String image
    );

    @Multipart
    @POST("uploadimage")
    Call<String> uploadImage(@Part MultipartBody.Part body);

}
