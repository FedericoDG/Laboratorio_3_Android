package com.federicodg80.inmobiliaria.ui.inmuebles;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.federicodg80.inmobiliaria.api.ApiClient;
import com.federicodg80.inmobiliaria.api.ApiService;
import com.federicodg80.inmobiliaria.modelos.Inmueble;
import com.federicodg80.inmobiliaria.utils.ErrorHandler;
import com.federicodg80.inmobiliaria.utils.PreferencesManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmueblesViewModel extends AndroidViewModel {
    private static final String TAG = "PROPERTIES";
    private MutableLiveData<List<Inmueble>> mListaInmuebles;
    private MutableLiveData<String> mError;

    public InmueblesViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Inmueble>> getListaInmuebles() {
        if (mListaInmuebles == null) {
            mListaInmuebles = new MutableLiveData<>();
        }
        return mListaInmuebles;
    }

    public LiveData<String> getError() {
        if (mError == null) {
            mError = new MutableLiveData<>();
        }
        return mError;
    }

    public void getProperties() {
        ApiService api = ApiClient.getClient().create(ApiService.class);
        Call<List<Inmueble>> call = api.getMyProperties("Bearer " + PreferencesManager.getToken(getApplication()));

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<List<Inmueble>> call, @NonNull Response<List<Inmueble>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Inmueble> profileResponse = response.body();
                    mListaInmuebles.postValue(profileResponse);
                } else {
                    ErrorHandler.handleErrorResponse(response, TAG, message -> mError.setValue(message));
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Inmueble>> call, @NonNull Throwable t) {
                ErrorHandler.handleErrorFailure(t, TAG, message -> mError.setValue(message));
            }
        });
    }
}