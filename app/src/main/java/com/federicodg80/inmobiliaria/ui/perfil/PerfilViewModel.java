package com.federicodg80.inmobiliaria.ui.perfil;

import android.app.Application;
import android.util.Log;
import android.util.Patterns;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.federicodg80.inmobiliaria.api.ApiClient;
import com.federicodg80.inmobiliaria.api.ApiService;
import com.federicodg80.inmobiliaria.api.propietario.PropietarioUpdateRequest;
import com.federicodg80.inmobiliaria.modelos.Propietario;
import com.federicodg80.inmobiliaria.utils.ErrorHandler;
import com.federicodg80.inmobiliaria.utils.PreferencesManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {
    private static final String TAG = "PROFILE";
    private MutableLiveData<Propietario> mPropietario = new MutableLiveData<>();
    private MutableLiveData<String> mError = new MutableLiveData<>();
    private MutableLiveData<String> mSuccess = new MutableLiveData<>();
    private int mIdPropietarioInt;
    private int mDniInt;
    private int mTelefonoInt;

    public PerfilViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Propietario> getPropietario() {
        if (mPropietario == null) {
            mPropietario = new MutableLiveData<>();
        }
        return mPropietario;
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

    public void getProfile() {
        ApiService api = ApiClient.getClient().create(ApiService.class);
        Call<Propietario> call = api.getProfile("Bearer " + PreferencesManager.getToken(getApplication()));

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<Propietario> call, @NonNull Response<Propietario> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Propietario profileResponse = response.body();
                    mPropietario.postValue(profileResponse);
                } else {
                    ErrorHandler.handleErrorResponse(response, TAG, message -> mError.postValue(message));
                }
            }

            @Override
            public void onFailure(@NonNull Call<Propietario> call, @NonNull Throwable t) {
                ErrorHandler.handleErrorFailure(t, TAG, message -> mError.postValue(message));
            }
        });
    }

    public void updateProfile(String idPropietario, String dni, String nombre, String apellido, String mail, String telefono, String passwordViejo, String password, String password2) {
        if (!validate(idPropietario, dni, telefono, nombre, apellido, mail, passwordViejo,  password, password2)) {
            return;
        }

        ApiService api = ApiClient.getClient().create(ApiService.class);
        Call<Propietario> call = api.updateProfile("Bearer " + PreferencesManager.getToken(getApplication()),
                new PropietarioUpdateRequest(mIdPropietarioInt, mDniInt, apellido, nombre, mTelefonoInt, mail, password, passwordViejo));

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<Propietario> call, @NonNull Response<Propietario> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Propietario profileResponse = response.body();
                    mPropietario.postValue(profileResponse);
                    mSuccess.postValue("Perfil actualizado con éxito");
                } else {
                    ErrorHandler.handleErrorResponse(response, TAG, message -> mError.postValue(message));
                }
            }

            @Override
            public void onFailure(@NonNull Call<Propietario> call, @NonNull Throwable t) {
                ErrorHandler.handleErrorFailure(t, TAG, message -> mError.postValue(message));
            }
        });
    }

    private boolean validate(String idPropietario, String dni, String telefono, String nombre, String apellido, String mail, String passwordViejo, String password, String password2) {
        // Normalizar entradass
        String idTrim = idPropietario != null ? idPropietario.trim() : "";
        String dniTrim = dni != null ? dni.trim() : "";
        String telefonoTrim = telefono != null ? telefono.trim() : "";
        String nombreTrim = nombre != null ? nombre.trim() : "";
        String apellidoTrim = apellido != null ? apellido.trim() : "";
        String mailTrim = mail != null ? mail.trim() : "";

        // verificar que si password trae algo tambien lo debe hacer passwordViejo
        if (!password.isEmpty() && passwordViejo.isEmpty()) {
            mError.setValue("Debe ingresar su contraseña actual si quiere cambiarla.");
            return false;
        }

        if (!password.equals(password2)) {
            mError.setValue("Las contraseñas no coinciden.");
            return false;
        } else {
            Log.d(TAG, "validate: " + password + " - " + password2);
        }

        // Validar campos numéricos
        try {
            mIdPropietarioInt = Integer.parseInt(idTrim);
            mDniInt = Integer.parseInt(dniTrim);
            mTelefonoInt = Integer.parseInt(telefonoTrim);
        } catch (NumberFormatException e) {
            mError.setValue("Ingrese valores numéricos válidos para Código, DNI y Teléfono.");
            return false;
        }

        // Validar campos de texto no vacíos
        if (nombreTrim.isEmpty() || apellidoTrim.isEmpty() || mailTrim.isEmpty()) {
            mError.setValue("Nombre, Apellido y Email no pueden estar vacíos.");
            return false;
        }

        // Validar formato de email
        if (!Patterns.EMAIL_ADDRESS.matcher(mailTrim).matches()) {
            mError.setValue("Ingrese un email válido.");
            return false;
        }

        return true;
    }
}