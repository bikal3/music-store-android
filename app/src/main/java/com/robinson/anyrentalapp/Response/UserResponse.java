package com.robinson.anyrentalapp.Response;

import com.robinson.anyrentalapp.Model.User;

public class UserResponse {
    String success, token;
    User user;

    public String getSuccess() {
        return success;
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }
}
