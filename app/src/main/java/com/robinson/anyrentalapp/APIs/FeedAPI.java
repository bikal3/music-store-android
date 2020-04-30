package com.robinson.anyrentalapp.APIs;

import com.robinson.anyrentalapp.Response.FeedResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FeedAPI {

    @GET("feedlist")
    Call<FeedResponse> FeedList();


}
