package com.robinson.anyrentalapp.UI;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.robinson.anyrentalapp.BLL.CartBLL;
import com.robinson.anyrentalapp.BLL.ProductBLL;
import com.robinson.anyrentalapp.Helper.Notification;
import com.robinson.anyrentalapp.Helper.SetImage;
import com.robinson.anyrentalapp.Helper.StrictMode;
import com.robinson.anyrentalapp.Helper.UserSession;
import com.robinson.anyrentalapp.R;
import com.robinson.anyrentalapp.Response.ProductResponse;

public class DetailAcitivity extends AppCompatActivity implements View.OnClickListener {
    TextView prodprice, prodname, proddesc, prodbrand, prodmadein;
    ImageView prodimage;
    Button btnaddcart;
    ProductBLL productBLL;
    CartBLL cartBLL;
    private String prodid = "";
    private String userid = "";
    UserSession userSession;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_acitivity);
        prodname = findViewById(R.id.txt_detail_prodname);
        proddesc = findViewById(R.id.txt_detail_desc);
        prodprice = findViewById(R.id.txt_detail_price);
        prodmadein = findViewById(R.id.txt_detail_madein);
        prodbrand = findViewById(R.id.txt_detail_brand);
        prodimage = findViewById(R.id.img_detail_image);
        btnaddcart = findViewById(R.id.btn_detail_cart);
        btnaddcart.setOnClickListener(this);
        prodid = getIntent().getStringExtra("prodid");
        userSession = new UserSession(this);
        userid = userSession.getUser().get_id();
        productBLL = new ProductBLL();
        cartBLL = new CartBLL();
        initToolbar();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getSingleProduct();
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Product Detial");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void getSingleProduct() {
        if (prodid != null) {
            StrictMode.StrictMode();
            ProductResponse productResponse = productBLL.fetchSingleProduct(prodid);
            if (productResponse != null) {
                prodbrand.setText(productResponse.getProduct().getBrand());
                prodmadein.setText(productResponse.getProduct().getMadein());
                prodprice.setText(productResponse.getProduct().getPrice());
                prodname.setText(productResponse.getProduct().getProductname());
                proddesc.setText(productResponse.getProduct().getDescription());
                SetImage.setImage(productResponse.getProduct().getImage(), prodimage);
            } else {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_detail_cart:
                AddtoCart();
                break;
        }
    }

    private void AddtoCart() {
        StrictMode.StrictMode();
        if (!userid.isEmpty() && !prodid.isEmpty()) {
            if (cartBLL.addCart(prodid, userid)) {
                Toast.makeText(this, "Added to Cart", Toast.LENGTH_SHORT).show();
                Notification.givenotification(DetailAcitivity.this,"Your Cart Has been added");
            } else {
                Toast.makeText(this, "Cart Added Failed", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
        }

    }
}
