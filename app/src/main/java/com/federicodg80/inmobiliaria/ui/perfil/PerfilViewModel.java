package com.federicodg80.inmobiliaria.ui.perfil;

import android.app.Application;
import android.util.Patterns;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.federicodg80.inmobiliaria.api.ApiClient;
import com.federicodg80.inmobiliaria.api.ApiService;
import com.federicodg80.inmobiliaria.modelos.Propietario;
import com.federicodg80.inmobiliaria.utils.ErrorHandler;
import com.federicodg80.inmobiliaria.utils.PreferencesManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {
    private static final String TAG = "PROFILE";
    private MutableLiveData<Propietario> mPropietario;
    private MutableLiveData<String> mError;
    private MutableLiveData<String> mSuccess;
    private int mIdPropietarioInt;
    private int mDniInt;
    private int mTelefonoInt;

    public PerfilViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<Propietario> getPropietario() {
        if (mPropietario == null) {
            mPropietario = new MutableLiveData<>();
        }
        return mPropietario;
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

    public void getProfile() {
        ApiService api = ApiClient.getClient().create(ApiService.class);
        Call<Propietario> call = api.getProfile("Bearer " + PreferencesManager.getToken(getApplication()));

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<Propietario> call, @NonNull Response<Propietario> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Propietario profileResponse = response.body();
                    getPropietario().setValue(profileResponse);
                } else {
                    ErrorHandler.handleErrorResponse(response, TAG, message -> getError().setValue(message));
                }
            }

            @Override
            public void onFailure(@NonNull Call<Propietario> call, @NonNull Throwable t) {
                ErrorHandler.handleErrorFailure(t, TAG, message -> getError().setValue(message));
            }
        });
    }

    public void updateProfile(String idPropietario, String dni, String nombre, String apellido, String mail, String telefono, String password) {
        if (!validate(idPropietario, dni, telefono, nombre, apellido, mail)) {
            return;
        }

        ApiService api = ApiClient.getClient().create(ApiService.class);
        Call<Propietario> call = api.updateProfile("Bearer " + PreferencesManager.getToken(getApplication()),
                new Propietario(mIdPropietarioInt, mDniInt, apellido, nombre, mTelefonoInt, mail, password));

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<Propietario> call, @NonNull Response<Propietario> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Propietario profileResponse = response.body();
                    getPropietario().postValue(profileResponse);
                    getSuccess().postValue("Perfil actualizado con éxito");
                } else {
                    ErrorHandler.handleErrorResponse(response, TAG, message -> getError().postValue(message));
                }
            }

            @Override
            public void onFailure(@NonNull Call<Propietario> call, @NonNull Throwable t) {
                ErrorHandler.handleErrorFailure(t, TAG, message -> getError().postValue(message));
            }
        });
    }

    private boolean validate(String idPropietario, String dni, String telefono, String nombre, String apellido, String mail) {
        // Normalizar entradass
        String idTrim = idPropietario != null ? idPropietario.trim() : "";
        String dniTrim = dni != null ? dni.trim() : "";
        String telefonoTrim = telefono != null ? telefono.trim() : "";
        String nombreTrim = nombre != null ? nombre.trim() : "";
        String apellidoTrim = apellido != null ? apellido.trim() : "";
        String mailTrim = mail != null ? mail.trim() : "";

        // Validar campos numéricos
        try {
            mIdPropietarioInt = Integer.parseInt(idTrim);
            mDniInt = Integer.parseInt(dniTrim);
            mTelefonoInt = Integer.parseInt(telefonoTrim);
        } catch (NumberFormatException e) {
            getError().setValue("Ingrese valores numéricos válidos para Código, DNI y Teléfono.");
            return false;
        }

        // Validar campos de texto no vacíos
        if (nombreTrim.isEmpty() || apellidoTrim.isEmpty() || mailTrim.isEmpty()) {
            getError().setValue("Nombre, Apellido y Email no pueden estar vacíos.");
            return false;
        }

        // Validar formato de email
        if (!Patterns.EMAIL_ADDRESS.matcher(mailTrim).matches()) {
            getError().setValue("Ingrese un email válido.");
            return false;
        }

        return true;
    }
}