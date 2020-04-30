package com.robinson.anyrentalapp.UI;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.robinson.anyrentalapp.APIs.UserAPI;
import com.robinson.anyrentalapp.BLL.UserBLL;
import com.robinson.anyrentalapp.Helper.EditTextValidation;
import com.robinson.anyrentalapp.Helper.RetrofitClient;
import com.robinson.anyrentalapp.Helper.SetImage;
import com.robinson.anyrentalapp.Helper.StrictMode;
import com.robinson.anyrentalapp.Helper.UserSession;
import com.robinson.anyrentalapp.Model.User;
import com.robinson.anyrentalapp.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProfile extends AppCompatActivity implements View.OnClickListener {
    TextInputLayout tl_fname, tl_lname, tl_email, tl_phoneno, tl_address;
    TextInputEditText et_fname, et_lname, et_email, et_phoneno, et_address;
    private HashMap<String, TextInputLayout> errorMap;
    FloatingActionButton btnaddimage;
    Button btnupdate;
    String token, id, image;
    ImageView profileimage;
    UserBLL userBLL;
    UserSession userSession;
    User user;
    Uri imageUri;
    Bitmap bitmap;
    private static final int PICK_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        tl_fname = findViewById(R.id.tl_update_fname);
        et_fname = findViewById(R.id.et_update_fname);
        tl_lname = findViewById(R.id.tl_update_lname);
        et_lname = findViewById(R.id.et_update_lname);
        tl_email = findViewById(R.id.tl_update_email);
        et_email = findViewById(R.id.et_update_email);
        tl_phoneno = findViewById(R.id.tl_update_phoneno);
        et_phoneno = findViewById(R.id.et_update_phoneno);
        tl_address = findViewById(R.id.tl_update_address);
        et_address = findViewById(R.id.et_update_address);
        et_address = findViewById(R.id.et_update_address);
        profileimage = findViewById(R.id.img_update_profile);
        btnaddimage = findViewById(R.id.btn_update_add);


        errorMap = new HashMap<>();
        errorMap.put("firstName", tl_fname);
        errorMap.put("lastName", tl_lname);
        errorMap.put("email", tl_email);
        errorMap.put("Phonenumber", tl_phoneno);
        errorMap.put("address", tl_address);
        userBLL = new UserBLL();
        userSession = new UserSession(this);
        user = userSession.getUser();
        token = userSession.getToken();
        id = user.get_id();
        getProfile();
        initToolbar();

        btnupdate = findViewById(R.id.btn_update_update);
        btnupdate.setOnClickListener(this);
        btnaddimage.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_update_update:
                Update();
                break;
            case R.id.btn_update_add:
                Opengallery();
                break;
        }
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Update Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private void getProfile() {
        if (userSession.isActive()) {
            StrictMode.StrictMode();
            user = userBLL.userProfile(id, token);
            if (user != null) {
                et_email.setText(user.getEmail());
                et_lname.setText(user.getLastname());
                et_fname.setText(user.getFirstname());
                et_address.setText(user.getAddress());
                et_phoneno.setText(user.getPhoneno());
                SetImage.setImage(user.getImage(), profileimage);
            } else {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
            }

        }
    }


    public void Update() {
        if (isUpdateDetailsValid()) {
            StrictMode.StrictMode();
            String email = tl_email.getEditText().getText().toString().trim();
            String fname = tl_fname.getEditText().getText().toString().trim();
            String lname = tl_lname.getEditText().getText().toString().trim();
            String phoneno = tl_phoneno.getEditText().getText().toString().trim();
            String address = tl_address.getEditText().getText().toString().trim();

            if (userBLL.UpdateProfile(id, token, fname, lname, email, phoneno, address, image)) {
                Toast.makeText(this, "Update Successfull", Toast.LENGTH_SHORT).show();
                tl_email.clearFocus();
                tl_address.clearFocus();
                tl_fname.clearFocus();
                tl_lname.clearFocus();
                tl_phoneno.clearFocus();
            } else {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please Fill all the details", Toast.LENGTH_SHORT).show();
        }


    }

    private boolean isUpdateDetailsValid() {
        if (EditTextValidation.isEmpty(tl_address) | EditTextValidation.isEmpty(tl_email) |
                EditTextValidation.isEmpty(tl_fname) | EditTextValidation.isEmpty(tl_lname) |
                EditTextValidation.isEmpty(tl_phoneno)) {
            return false;
        }
        return true;
    }

    private void Opengallery() {
        Intent gallery = new Intent();
        gallery.setType("image/*");
        gallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(gallery, "Choose Image"), PICK_IMAGE);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            imageUri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                profileimage.setImageBitmap(bitmap);
                uploadImage(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadImage(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] bytes = stream.toByteArray();
        try {

            File file = new File(getCacheDir(), "image.jpeg");
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bytes);
            fos.flush();
            fos.close();

            RequestBody rb = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part body = MultipartBody.Part.createFormData("imageName", file.getName(), rb);

            UserAPI userApI = RetrofitClient.getInstance().create(UserAPI.class);
            Call<String> imageModelCall = userApI.uploadImage(body);
            imageModelCall.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    image = response.body();
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(UpdateProfile.this, "Error" + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

}
