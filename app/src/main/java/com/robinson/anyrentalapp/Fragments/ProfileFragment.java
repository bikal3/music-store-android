package com.robinson.anyrentalapp.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.robinson.anyrentalapp.BLL.UserBLL;
import com.robinson.anyrentalapp.Helper.SetImage;
import com.robinson.anyrentalapp.Helper.StrictMode;
import com.robinson.anyrentalapp.Helper.UserSession;
import com.robinson.anyrentalapp.Model.User;
import com.robinson.anyrentalapp.R;
import com.robinson.anyrentalapp.UI.UpdateProfile;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener {
    TextView name, email, phonenumber, address, orderlist, wishlist, purchaselist;
    ImageView profileimage;
    Button btnupdate;
    UserBLL userBLL;
    UserSession userSession;
    User user;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        initialize(view);
        getProfile();
        return view;
    }


    private void initialize(View view) {
        name = view.findViewById(R.id.txt_name_profile);
        email = view.findViewById(R.id.txt_profile_email);
        phonenumber = view.findViewById(R.id.txt_phoneno_profile);
        address = view.findViewById(R.id.txt_address_profile);
        orderlist = view.findViewById(R.id.txt_orderlist_profile);
        purchaselist = view.findViewById(R.id.txt_purchase_profile);
        wishlist = view.findViewById(R.id.txt_wishlist_profile);
        profileimage = view.findViewById(R.id.img_profile);
        btnupdate = view.findViewById(R.id.btn_update_profile);
        btnupdate.setOnClickListener(this);

        userBLL = new UserBLL();
        userSession = new UserSession(getActivity());
        user = userSession.getUser();
    }

    public static ProfileFragment newInstance() {
        ProfileFragment profileFragment = new ProfileFragment();
        return profileFragment;
    }

    private void getProfile() {
        if (userSession.isActive()) {
            StrictMode.StrictMode();
            String token = userSession.getToken();
            String id = user.get_id();
            user = userBLL.userProfile(id, token);
            if (user != null) {
                email.setText(user.getEmail());
                name.setText(user.getFirstname() + " " + user.getLastname());
                address.setText(user.getAddress());
                phonenumber.setText(user.getPhoneno());
                SetImage.setImage(user.getImage(), profileimage);
            } else {
                Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getActivity(), "Session Fail", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_update_profile:
                Intent intent = new Intent(getActivity(), UpdateProfile.class);
                startActivity(intent);
                break;
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        getProfile();
    }
}
