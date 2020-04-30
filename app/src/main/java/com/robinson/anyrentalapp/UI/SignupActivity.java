package com.robinson.anyrentalapp.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.robinson.anyrentalapp.BLL.UserBLL;
import com.robinson.anyrentalapp.Helper.EditTextValidation;
import com.robinson.anyrentalapp.Helper.StrictMode;
import com.robinson.anyrentalapp.MainActivity;
import com.robinson.anyrentalapp.Model.User;
import com.robinson.anyrentalapp.R;
import com.robinson.anyrentalapp.Response.UserResponse;

import java.util.HashMap;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {
    private TextInputLayout etfName, etlName, etEmail, etPassword;
    private HashMap<String, TextInputLayout> errorMap;
    Button btnsignup;
    CheckBox checkBox1, checkBox2;
    UserBLL userBLL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        etfName = findViewById(R.id.tl_update_fname);
        etlName = findViewById(R.id.tl_update_lname);
        etEmail = findViewById(R.id.tl_update_email);
        etPassword = findViewById(R.id.tl_update_address);
        checkBox1 = findViewById(R.id.checkBox);
        checkBox2 = findViewById(R.id.checkBox2);

        errorMap = new HashMap<>();
        errorMap.put("firstName", etfName);
        errorMap.put("lastName", etlName);
        errorMap.put("email", etEmail);
        errorMap.put("password", etPassword);
        userBLL = new UserBLL();
        btnsignup = findViewById(R.id.btnsignup);
        btnsignup.setOnClickListener(this);

    }

    private boolean isSignUpDetailsValid() {
        if (EditTextValidation.isEmpty(etfName) | EditTextValidation.isEmpty(etlName) |
                EditTextValidation.isEmpty(etEmail) | EditTextValidation.isEmpty(etPassword)) {
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        signup();

    }

    private void signup() {

        if (isSignUpDetailsValid() && checkBox2.isChecked() && checkBox1.isChecked()) {

            StrictMode.StrictMode();

            String fName = etfName.getEditText().getText().toString().trim();
            String lName = etlName.getEditText().getText().toString().trim();
            String email = etEmail.getEditText().getText().toString().trim();
            String password = etPassword.getEditText().getText().toString().trim();

            User user = new User(fName, lName, email, password);
            UserResponse userResponse = null;

            if (userBLL.registerUser(user)) {
                Toast.makeText(this, "Successfully registered !", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SignupActivity.this, MainActivity.class));
            } else {
                Toast.makeText(this, userResponse.getSuccess(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please Accept the terms and condition", Toast.LENGTH_SHORT).show();
        }
    }

}
