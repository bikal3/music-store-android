package com.robinson.anyrentalapp.Response;

import com.robinson.anyrentalapp.Model.Product;

import java.util.List;

public class ProductResponse {
    private List<Product> products;
    private Product product;

    public List<Product> getProducts() {
        return products;
    }

    public Product getProduct() {
        return product;
    }
}
