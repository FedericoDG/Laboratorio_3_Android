package com.federicodg80.inmobiliaria.api;

import android.util.Log;

import com.federicodg80.inmobiliaria.BuildConfig;
import com.federicodg80.inmobiliaria.utils.LocalDateTimeDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.time.LocalDateTime;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit = null;
    private static final String BASE_URL = BuildConfig.API_BASE_URL;
    // private static final String BASE_URL = "https://ddf15a1dfc8b.ngrok-free.app";

    public static Retrofit getClient() {
        Log.d("ApiClient", "BASE_URL: " + BASE_URL);

        if (retrofit == null) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer())
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}