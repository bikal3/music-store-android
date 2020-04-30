package com.robinson.anyrentalapp.Helper;

import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class SetImage {
    public static void setImage(String iconName, ImageView imageView) {
        try {
            String imageURI = RetrofitClient.IMAGE_URL + iconName;
            URL url = new URL(imageURI);
            imageView.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
