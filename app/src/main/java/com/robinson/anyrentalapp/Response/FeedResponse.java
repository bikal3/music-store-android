package com.robinson.anyrentalapp.Response;

import com.robinson.anyrentalapp.Model.Feed;
import com.robinson.anyrentalapp.Model.Product;

import java.util.List;

public class FeedResponse {
    String success;
    private List<Feed> feed;
    private List<Product> products;

    public String getSuccess() {
        return success;
    }

    public List<Feed> getFeed() {
        return feed;
    }

    public List<Product> getProducts() {
        return products;
    }
}
