package com.robinson.anyrentalapp.BLL;

import com.robinson.anyrentalapp.APIs.FeedAPI;
import com.robinson.anyrentalapp.Helper.RetrofitClient;
import com.robinson.anyrentalapp.Model.Feed;
import com.robinson.anyrentalapp.Response.FeedResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class FeedBLL {
    private FeedAPI feedAPI;
    public static String token;

    public FeedBLL() {

        feedAPI = RetrofitClient.getInstance().create(FeedAPI.class);
    }

    public List<Feed> getFeed() {
        List<Feed> feedlist = new ArrayList<>();
        Call<FeedResponse> feedCall = feedAPI.FeedList();
        try {
            Response<FeedResponse> feedResponse = feedCall.execute();
            if (!feedResponse.isSuccessful()) {
                return feedlist;
            } else if (feedResponse.body().getFeed() != null) {
                feedlist = feedResponse.body().getFeed();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return feedlist;
    }
}
