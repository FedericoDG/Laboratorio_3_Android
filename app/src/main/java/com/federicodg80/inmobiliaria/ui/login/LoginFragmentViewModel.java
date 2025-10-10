package com.federicodg80.inmobiliaria.ui.login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.federicodg80.inmobiliaria.api.ApiClient;
import com.federicodg80.inmobiliaria.api.ApiService;
import com.federicodg80.inmobiliaria.api.auth.LoginRequest;
import com.federicodg80.inmobiliaria.api.auth.LoginResponse;
import com.federicodg80.inmobiliaria.utils.ErrorHandler;
import com.federicodg80.inmobiliaria.utils.PreferencesManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragmentViewModel extends AndroidViewModel {
    private static final String TAG = "LOGIN";
    MutableLiveData<Boolean> mLoginSuccess;
    MutableLiveData<String> mErrorMessage;

    public LoginFragmentViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Boolean> isLoginSuccess() {
        if (mLoginSuccess == null) {
            mLoginSuccess = new MutableLiveData<>();
        }
        return mLoginSuccess;
    }

    public LiveData<String> getErrorMessage() {
        if (mErrorMessage == null) {
            mErrorMessage = new MutableLiveData<>();
        }
        return mErrorMessage;
    }

    public void login(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            mErrorMessage.setValue("Email y contraseña son obligatorios");
            return;
        }

        ApiService api = ApiClient.getClient().create(ApiService.class);
        retrofit2.Call<LoginResponse> call = api.login(new LoginRequest(email, password));

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    LoginResponse loginResponse = response.body();
                    PreferencesManager.saveToken(getApplication(), loginResponse.token);
                    mLoginSuccess.postValue(true);
                } else {
                    ErrorHandler.handleErrorResponse(response, TAG, new ErrorHandler.ErrorCallback() {
                        @Override
                        public void onError(String message) {
                            mErrorMessage.postValue(message);
                        }
                    });
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                ErrorHandler.handleErrorFailure(t, TAG, new ErrorHandler.ErrorCallback() {
                    @Override
                    public void onError(String message) {
                        mErrorMessage.postValue(message);
                    }
                });
            }
        });
    }
}
