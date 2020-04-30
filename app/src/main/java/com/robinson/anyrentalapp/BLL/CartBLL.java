package com.robinson.anyrentalapp.BLL;

import com.robinson.anyrentalapp.APIs.CartAPI;
import com.robinson.anyrentalapp.Helper.RetrofitClient;
import com.robinson.anyrentalapp.Model.Product;
import com.robinson.anyrentalapp.Model.User;
import com.robinson.anyrentalapp.Response.CartResponse;
import com.robinson.anyrentalapp.Response.ProductResponse;
import com.robinson.anyrentalapp.Response.UserResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class CartBLL {
    private CartAPI cartAPI;
    public static String subtotal;

    public CartBLL() {
        cartAPI = RetrofitClient.getInstance().create(CartAPI.class);
    }


    public boolean addCart(String productid, String userid) {
        boolean iscartSuccessful = false;

        Call<CartResponse> cartCall = cartAPI.addtocart(productid, userid);

        try {
            Response<CartResponse> callResponse = cartCall.execute();
            if (!callResponse.isSuccessful()) {
                return iscartSuccessful;
            } else if (callResponse.body().getSuccess() != null) {
                iscartSuccessful = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return iscartSuccessful;
    }

    public List<Product> getProduct(String userid) {
        List<Product> prodlist = new ArrayList<>();
        Call<CartResponse> getcartCall = cartAPI.getCart(userid);
        try {
            Response<CartResponse> cartResponse = getcartCall.execute();
            if (!cartResponse.isSuccessful()) {
                return prodlist;
            } else if (cartResponse.body().getCart() != null) {
                prodlist = cartResponse.body().getCart();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prodlist;
    }

    public CartResponse getCartTotal(String userid) {
        CartResponse total = null;
        Call<CartResponse> getcartCall = cartAPI.getCartTotal(userid);
        try {
            Response<CartResponse> cartResponse = getcartCall.execute();
            if (!cartResponse.isSuccessful()) {
                return total;
            } else if (cartResponse.body() != null) {
                total = cartResponse.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return total;
    }

    public CartResponse deleteCart(String userid, String prodid) {
        CartResponse deletecart = null;
        Call<CartResponse> deletecall = cartAPI.deletecart(userid, prodid);
        try {
            Response<CartResponse> cartResponse = deletecall.execute();
            if (!cartResponse.isSuccessful()) {
                return deletecart;
            } else if (cartResponse.body() != null) {
                deletecart = cartResponse.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return deletecart;
    }
}
