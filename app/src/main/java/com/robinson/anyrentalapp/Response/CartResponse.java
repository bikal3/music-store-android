package com.robinson.anyrentalapp.Response;

import com.robinson.anyrentalapp.Model.Product;

import java.util.List;

public class CartResponse {
    String success, total;
    Product carts;
    private List<Product> cart;

    public String getSuccess() {
        return success;
    }

    public Product getCarts() {
        return carts;
    }

    public List<Product> getCart() {
        return cart;
    }

    public String getTotal() {
        return total;
    }
}
