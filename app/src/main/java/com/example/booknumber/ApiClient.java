package com.example.booknumber;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    // 10.0.2.2 correspond à localhost pour l'émulateur Android.
    // Pour un vrai téléphone, mettre l'adresse IP locale du PC (ex: 192.168.x.x)
    private static final String BASE_URL = "http://10.0.2.2/Booknumber/api/";
    private static Retrofit retrofitInstance;

    public static Retrofit getInstance() {
        if (retrofitInstance == null) {
            retrofitInstance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitInstance;
    }
}
