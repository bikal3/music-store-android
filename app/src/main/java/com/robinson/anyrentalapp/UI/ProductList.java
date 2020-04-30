package com.robinson.anyrentalapp.UI;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.robinson.anyrentalapp.Adapter.ProductAdapter;
import com.robinson.anyrentalapp.BLL.ProductBLL;
import com.robinson.anyrentalapp.Helper.StrictMode;
import com.robinson.anyrentalapp.Model.Product;
import com.robinson.anyrentalapp.R;

import java.util.ArrayList;
import java.util.List;

public class ProductList extends AppCompatActivity {
    private List<Product> productList;
    private ProductBLL prodBLL;
    private RecyclerView productRecycler;
    private String feedid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        prodBLL = new ProductBLL();
        productList = new ArrayList<>();
        productRecycler = findViewById(R.id.recycler_prod);
        initToolbar();
        feedid = getIntent().getStringExtra("feedid");
        productRecycler.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Products");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!feedid.isEmpty()) {
            getproductfeed();
        } else {
            getallproduct();
        }
    }

    private void getproductfeed() {
        StrictMode.StrictMode();
        productList = prodBLL.getProduct(feedid);
        ProductAdapter prodadapt = new ProductAdapter(this, productList);
        productRecycler.setAdapter(prodadapt);
    }

    private void getallproduct() {
        StrictMode.StrictMode();
        productList = prodBLL.getAllProduct();
        ProductAdapter prodadapt = new ProductAdapter(this, productList);
        productRecycler.setAdapter(prodadapt);
    }

}
