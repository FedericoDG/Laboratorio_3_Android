package com.federicodg80.inmobiliaria.ui.inmuebles;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.federicodg80.inmobiliaria.api.ApiClient;
import com.federicodg80.inmobiliaria.api.ApiService;
import com.federicodg80.inmobiliaria.api.inmueble.UpdateDisponibleRequest;
import com.federicodg80.inmobiliaria.modelos.Inmueble;
import com.federicodg80.inmobiliaria.utils.ErrorHandler;
import com.federicodg80.inmobiliaria.utils.PreferencesManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmuebleDetallesViewModel extends AndroidViewModel {
    private static final String TAG = "PROPERTY_DETAILS";
    private MutableLiveData<Inmueble> mInmueble;
    private MutableLiveData<String> mError;
    private MutableLiveData<String> mSuccess;
    public InmuebleDetallesViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Inmueble> getInmueble() {
        if (mInmueble == null) {
            mInmueble = new MutableLiveData<>();
        }
        return mInmueble;
    }

    public LiveData<String> getError() {
        if (mError == null) {
            mError = new MutableLiveData<>();
        }
        return mError;
    }

    public LiveData<String> getSuccess() {
        if (mSuccess == null) {
            mSuccess = new MutableLiveData<>();
        }
        return mSuccess;
    }

    public void getProperty(Bundle args) {
        int idInmueble = args.getSerializable("inmueble") != null ? ((Inmueble) args.getSerializable("inmueble")).getIdInmueble() : -1;
        ApiService api = ApiClient.getClient().create(ApiService.class);
        Call<Inmueble> call = api.getPropertyById("Bearer " + PreferencesManager.getToken(getApplication()), idInmueble);

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<Inmueble> call, @NonNull Response<Inmueble> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Inmueble profileResponse = response.body();
                    mInmueble.postValue(profileResponse);
                } else {
                    ErrorHandler.handleErrorResponse(response, TAG, message -> mError.postValue(message));
                }
            }

            @Override
            public void onFailure(@NonNull Call<Inmueble> call, @NonNull Throwable t) {
                ErrorHandler.handleErrorFailure(t, TAG, message -> mError.postValue(message));
            }
        });
    }

    public void updateProperty(Bundle args, Boolean disponible) {
        int idInmueble = args.getSerializable("inmueble") != null ? ((Inmueble) args.getSerializable("inmueble")).getIdInmueble() : -1;
        ApiService api = ApiClient.getClient().create(ApiService.class);
        Call<Inmueble> call = api.updateProretyById("Bearer " + PreferencesManager.getToken(getApplication()), idInmueble, new UpdateDisponibleRequest(disponible));

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<Inmueble> call, @NonNull Response<Inmueble> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Inmueble profileResponse = response.body();
                    mInmueble.postValue(profileResponse);
                    mSuccess.postValue("Inmueble actualizado correctamente");
                } else {
                    ErrorHandler.handleErrorResponse(response, TAG, message -> mError.setValue(message));
                }
            }

            @Override
            public void onFailure(@NonNull Call<Inmueble> call, @NonNull Throwable t) {
                ErrorHandler.handleErrorFailure(t, TAG, message -> mError.setValue(message));
            }
        });
    }

    public boolean isDisponibleSafe() {
        Inmueble i = mInmueble != null ? mInmueble.getValue() : null;
        return i != null && i.isDisponible();
    }
}