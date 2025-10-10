package com.federicodg80.inmobiliaria.api;

import com.federicodg80.inmobiliaria.api.auth.LoginRequest;
import com.federicodg80.inmobiliaria.api.auth.LoginResponse;
import com.federicodg80.inmobiliaria.api.inmueble.UpdateDisponibleRequest;
import com.federicodg80.inmobiliaria.modelos.Inmueble;
import com.federicodg80.inmobiliaria.modelos.Propietario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {
    @POST("auth/login")
    Call<LoginResponse> login(@Body LoginRequest request);

    @GET("auth/me")
    Call<LoginResponse> me(@Header("Authorization") String token);

    @GET("propietarios/me")
    Call<Propietario> getProfile(@Header("Authorization") String token);

    @PUT("propietarios/me")
    Call<Propietario> updateProfile(@Header("Authorization") String token, @Body Propietario propietario);

    @GET("inmuebles/me")
    Call<List<Inmueble>> getMyProperties(@Header("Authorization") String token);

    @GET("inmuebles/mis-contratos-activos")
    Call<List<Inmueble>> getMyPropertiesWithActiveContracts(@Header("Authorization") String token);

    @GET("inmuebles/id/{id}")
    Call<Inmueble> getPropertyById(@Header("Authorization") String token, @Path("id") int id);

    @PUT("inmuebles/id/{id}")
    Call<Inmueble> updateProretyById(@Header("Authorization") String token, @Path("id") int id, @Body UpdateDisponibleRequest body);

    @POST("inmuebles")
    Call<Inmueble> createProperty(@Header("Authorization") String token, @Body Inmueble inmueble);
}
