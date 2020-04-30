package com.robinson.anyrentalapp.BLL;

import com.robinson.anyrentalapp.APIs.UserAPI;
import com.robinson.anyrentalapp.Helper.RetrofitClient;
import com.robinson.anyrentalapp.Model.User;
import com.robinson.anyrentalapp.Response.UserResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class UserBLL {
    private UserAPI userAPI;
    public static String token;

    public UserBLL() {

        userAPI = RetrofitClient.getInstance().create(UserAPI.class);
    }

    public boolean registerUser(User user) {
        boolean isSignUpSuccessful = false;

        Call<UserResponse> signUpCall = userAPI.registerUser(user);

        try {
            Response<UserResponse> signUpResponse = signUpCall.execute();
            if (!signUpResponse.isSuccessful()) {
                return isSignUpSuccessful;
            } else if (signUpResponse.body().getSuccess() != null) {
                isSignUpSuccessful = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return isSignUpSuccessful;
    }

    public User loginUser(String email, String password) {
        User user = null;
        Call<UserResponse> loginCall = userAPI.loginUsers(email, password);

        try {
            Response<UserResponse> loginResponse = loginCall.execute();
            if (!loginResponse.isSuccessful()) {
                return user;
            } else if (loginResponse.body().getUser() != null) {
                token = loginResponse.body().getToken();
                user = loginResponse.body().getUser();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return user;
    }

    public User userProfile(String id, String token) {
        User user = null;
        Call<UserResponse> userProfilecall = userAPI.loadprofile(id, token);

        try {
            Response<UserResponse> profileResponse = userProfilecall.execute();
            if (!profileResponse.isSuccessful()) {
                return user;
            } else if (profileResponse.body().getUser() != null) {
                user = profileResponse.body().getUser();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return user;
    }

    public boolean UpdateProfile(String id, String token, String fname, String lname, String email, String phoneno, String address, String image) {
        boolean isUpdateSuccessfull = false;
        Call<UserResponse> userProfilecall = userAPI.updateProfile(id, token, fname, lname, email, phoneno, address, image);

        try {
            Response<UserResponse> profileResponse = userProfilecall.execute();
            if (!profileResponse.isSuccessful()) {
                return isUpdateSuccessfull;
            } else if (profileResponse.body().getSuccess() != null) {
                isUpdateSuccessfull = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return isUpdateSuccessfull;
    }


}
