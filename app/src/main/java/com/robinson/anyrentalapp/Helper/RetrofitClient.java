package com.robinson.anyrentalapp.Helper;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit;

//    private static final String BASE_URL = "http://10.0.2.2:5000/api/";
//    public static final String IMAGE_URL = "http://10.0.2.2:5000/";

    private static final String BASE_URL = "http://192.168.10.109:5000/api/";
    public static final String IMAGE_URL = "http://192.168.10.109:5000/";

    public static Retrofit getInstance() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }

}