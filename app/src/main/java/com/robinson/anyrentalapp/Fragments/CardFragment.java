package com.robinson.anyrentalapp.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.robinson.anyrentalapp.Adapter.CardAdapter;
import com.robinson.anyrentalapp.BLL.CartBLL;
import com.robinson.anyrentalapp.Helper.StrictMode;
import com.robinson.anyrentalapp.Helper.UserSession;
import com.robinson.anyrentalapp.Model.Product;
import com.robinson.anyrentalapp.R;
import com.robinson.anyrentalapp.Response.CartResponse;

import org.jetbrains.annotations.TestOnly;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CardFragment extends Fragment {
    UserSession userSession;
    String userid = "";
    CartBLL cartBLL;
    private List<Product> cartList;
    private RecyclerView cartRecycler;
    TextView subtotal;
    SharedPreferences sharedPreferences;
    CartResponse cartResponse;

    public CardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cartBLL = new CartBLL();
        cartList = new ArrayList<>();
        userSession = new UserSession(getActivity());
        userid = userSession.getUser().get_id();
        sharedPreferences = getActivity().getSharedPreferences("SUB_TOTAL", Context.MODE_PRIVATE);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_card, container, false);
        cartRecycler = view.findViewById(R.id.recycler_cart);
        LinearLayoutManager linearLayout = new LinearLayoutManager(getActivity());
        cartRecycler.setLayoutManager(linearLayout);
        subtotal = view.findViewById(R.id.txt_cart_totalamount);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getCart();
        getCartTotal();
    }

    private void getCartTotal() {
        cartResponse = cartBLL.getCartTotal(userid);
        subtotal.setText(cartResponse.getTotal());
    }

    private void getCart() {
        StrictMode.StrictMode();
        cartList = cartBLL.getProduct(userid);
        Collections.shuffle(cartList);
        CardAdapter cardAdapter = new CardAdapter(getActivity(), cartList);
        cartRecycler.setAdapter(cardAdapter);

    }

    public static CardFragment newInstance() {
        CardFragment cardFragment = new CardFragment();
        return cardFragment;
    }

}
