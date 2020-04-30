package com.robinson.anyrentalapp.UI;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.robinson.anyrentalapp.BLL.UserBLL;
import com.robinson.anyrentalapp.Helper.EditTextValidation;
import com.robinson.anyrentalapp.Helper.StrictMode;
import com.robinson.anyrentalapp.Helper.UserSession;
import com.robinson.anyrentalapp.Model.User;
import com.robinson.anyrentalapp.R;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private TextInputLayout etEmail, etPassword;
    private HashMap<String, TextInputLayout> errorMap;
    private Button btnlogin;
    private UserBLL userBLL;
    private UserSession userSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etEmail = findViewById(R.id.tl_login_email);
        etPassword = findViewById(R.id.tl_update_address);
        btnlogin = findViewById(R.id.btn_login);
        btnlogin.setOnClickListener(this);

        errorMap = new HashMap<>();
        errorMap.put("email", etEmail);
        errorMap.put("password", etPassword);

        userBLL = new UserBLL();
        userSession = new UserSession(this);

    }

    private boolean isSignInDetailsValid() {
        if (EditTextValidation.isEmpty(etEmail) | EditTextValidation.isEmpty(etPassword)) {
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        login();
    }

    private void login() {
        if (isSignInDetailsValid()) {
            StrictMode.StrictMode();

            String email = etEmail.getEditText().getText().toString().trim();
            String password = etPassword.getEditText().getText().toString().trim();
            User user = userBLL.loginUser(email, password);
            if (user != null) {
                userSession.startSession(user);
                Intent intent = new Intent(LoginActivity.this, Dashboard.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Username or Password is incorrect", Toast.LENGTH_SHORT).show();
                Vibrator vibrator=(Vibrator) getSystemService(VIBRATOR_SERVICE);
                vibrator.vibrate(1000);
            }


        }
    }
}
