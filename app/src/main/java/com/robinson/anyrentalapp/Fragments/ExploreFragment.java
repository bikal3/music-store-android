package com.robinson.anyrentalapp.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.robinson.anyrentalapp.Adapter.ExploreAdapter;
import com.robinson.anyrentalapp.Adapter.ExploreProductAdapter;
import com.robinson.anyrentalapp.Adapter.ProductAdapter;
import com.robinson.anyrentalapp.BLL.FeedBLL;
import com.robinson.anyrentalapp.BLL.ProductBLL;
import com.robinson.anyrentalapp.Helper.StrictMode;
import com.robinson.anyrentalapp.Model.Feed;
import com.robinson.anyrentalapp.Model.Product;
import com.robinson.anyrentalapp.R;
import com.robinson.anyrentalapp.UI.MapActivity;
import com.robinson.anyrentalapp.UI.ProductList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExploreFragment extends Fragment implements View.OnClickListener {
    SearchView searchView;
    private List<Feed> feedList;
    private List<Product> productList;
    private FeedBLL feedBLL;
    private ProductBLL productBLL;
    private RecyclerView feedRecycler;
    private RecyclerView prodRecycler;
    private TextView viewall;
    private ImageView imageView;


    public ExploreFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        feedBLL = new FeedBLL();
        productBLL = new ProductBLL();
        feedList = new ArrayList<>();
        productList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_explore, container, false);
        feedRecycler = view.findViewById(R.id.recycler_inst);
        prodRecycler = view.findViewById(R.id.recycler_exp_prod);
        viewall = view.findViewById(R.id.txt_exp_viewall);
        imageView = view.findViewById(R.id.img_exp_map);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        feedRecycler.setLayoutManager(layoutManager);
        prodRecycler.setLayoutManager(layoutManager2);
        viewall.setOnClickListener(this);
        imageView.setOnClickListener(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        FeedList();
        ProdList();
    }

    private void ProdList() {
        StrictMode.StrictMode();
        productList = productBLL.getAllProduct();
        Collections.shuffle(productList);
        ExploreProductAdapter exploreProductAdapter = new ExploreProductAdapter(getActivity(), productList);
        prodRecycler.setAdapter(exploreProductAdapter);
    }

    public static ExploreFragment newInstance() {
        ExploreFragment exploreFragment = new ExploreFragment();
        return exploreFragment;
    }

    private void FeedList() {
        StrictMode.StrictMode();
        feedList = feedBLL.getFeed();
        ExploreAdapter exploreAdapter = new ExploreAdapter(getActivity(), feedList);
        feedRecycler.setAdapter(exploreAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_exp_viewall:
                AllProduct();
                break;
            case R.id.img_exp_map:
                Intent intent = new Intent(getActivity(), MapActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void AllProduct() {
        Intent intent = new Intent(getActivity(), ProductList.class);
        startActivity(intent);

    }
}
