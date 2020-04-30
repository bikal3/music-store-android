package com.robinson.anyrentalapp.BLL;

import com.robinson.anyrentalapp.APIs.ProductAPI;
import com.robinson.anyrentalapp.Helper.RetrofitClient;
import com.robinson.anyrentalapp.Model.Product;
import com.robinson.anyrentalapp.Response.ProductResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class ProductBLL {
    private ProductAPI prodAPI;

    public ProductBLL() {

        prodAPI = RetrofitClient.getInstance().create(ProductAPI.class);
    }

    public List<Product> getProduct(String feedid) {
        List<Product> prodlist = new ArrayList<>();
        Call<ProductResponse> feedCall = prodAPI.getproduct(feedid);
        try {
            Response<ProductResponse> prodResponse = feedCall.execute();
            if (!prodResponse.isSuccessful()) {
                return prodlist;
            } else if (prodResponse.body().getProducts() != null) {
                prodlist = prodResponse.body().getProducts();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prodlist;
    }

    public List<Product> getAllProduct() {
        List<Product> prodlist = new ArrayList<>();
        Call<ProductResponse> feedCall = prodAPI.getallproduct();
        try {
            Response<ProductResponse> prodResponse = feedCall.execute();
            if (!prodResponse.isSuccessful()) {
                return prodlist;
            } else if (prodResponse.body().getProducts() != null) {
                prodlist = prodResponse.body().getProducts();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prodlist;
    }

    public ProductResponse fetchSingleProduct(String id) {
        ProductResponse product = null;
        Call<ProductResponse> prodcall = prodAPI.getproductbyid(id);

        try {
            Response<ProductResponse> prodResponse = prodcall.execute();
            if (!prodResponse.isSuccessful()) {
                return product;
            } else if (prodResponse.body().getProduct() != null) {
                product = prodResponse.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return product;
    }

}
