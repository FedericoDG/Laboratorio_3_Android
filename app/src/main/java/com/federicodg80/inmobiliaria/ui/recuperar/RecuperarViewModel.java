package com.federicodg80.inmobiliaria.ui.recuperar;

import android.app.Application;
import android.util.Patterns;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class RecuperarViewModel extends AndroidViewModel {
    MutableLiveData<String> mError;
    MutableLiveData<String> mSuccess;
    public RecuperarViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<String> getError() {
        if (mError == null) {
            mError = new MutableLiveData<>();
        }
        return mError;
    }

    public MutableLiveData<String> getSuccess() {
        if (mSuccess == null) {
            mSuccess = new MutableLiveData<>();
        }
        return mSuccess;
    }

    public void passwordRecovery(String email) {
       if (!validate(email)){
           return;
       }

       mSuccess.setValue("Funcionalidad no implementada.");
    }

    private boolean validate(String mail) {
        // Normalizar entrada
        String mailTrim = mail != null ? mail.trim() : "";

        if (!Patterns.EMAIL_ADDRESS.matcher(mailTrim).matches()) {
            getError().setValue("Ingrese un email válido.");
            return false;
        }

        return true;
    }
}