package com.federicodg80.inmobiliaria.ui.inmuebles;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.federicodg80.inmobiliaria.api.ApiClient;
import com.federicodg80.inmobiliaria.api.ApiService;
import com.federicodg80.inmobiliaria.modelos.Inmueble;
import com.federicodg80.inmobiliaria.utils.ErrorHandler;
import com.federicodg80.inmobiliaria.utils.PreferencesManager;

import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class InmuebleCrearViewModel extends AndroidViewModel {
    private static final String TAG = "PROPERTY_CREATE";
    private MutableLiveData<String> mError;
    private MutableLiveData<String> mSuccess;

    private int parsedAmbientes;
    private double parsedPrecio;

    public InmuebleCrearViewModel(@NonNull Application application) {
        super(application);
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

    public void createProperty(String ambientes, String direccion, String precio, String uso, String tipo, Uri imagenUri) {
        // Validación y parseo
        if (!validate(ambientes, direccion, precio, uso, tipo)) {
            return;
        }

        ApiService api = ApiClient.getClient().create(ApiService.class);

        // Crear RequestBody para cada parte
        RequestBody ambientesBody = RequestBody.create(MediaType.parse("text/plain"), ambientes);
        RequestBody direccionBody = RequestBody.create(MediaType.parse("text/plain"), direccion);
        RequestBody precioBody = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(parsedPrecio));
        RequestBody usoBody = RequestBody.create(MediaType.parse("text/plain"), uso);
        RequestBody tipoBody = RequestBody.create(MediaType.parse("text/plain"), tipo);

        // si imagenUri no es null, crear MultipartBody.Part
        MultipartBody.Part imagenPart = null;
        if (imagenUri != null) {
            try {
                InputStream inputStream = getApplication().getContentResolver().openInputStream(imagenUri);
                byte[] bytes = new byte[inputStream.available()];
                inputStream.read(bytes);
                RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), bytes);
                imagenPart = MultipartBody.Part.createFormData("imagen", "image.jpg", requestFile);
                inputStream.close();
            } catch (Exception e) {
                mError.postValue("Error al procesar la imagen.");
                return;
            }
        }

        Call<Inmueble> call = api.createProperty("Bearer " + PreferencesManager.getToken(getApplication()), ambientesBody, direccionBody, precioBody, usoBody, tipoBody, imagenPart);

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<Inmueble> call, @NonNull Response<Inmueble> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ((MutableLiveData<String>) getSuccess()).postValue("El inmueble ha sido creado correctamente");
                } else {
                    ErrorHandler.handleErrorResponse(response, TAG, message -> ((MutableLiveData<String>) getError()).postValue(message));
                }
            }

            @Override
            public void onFailure(@NonNull Call<Inmueble> call, @NonNull Throwable t) {
                ErrorHandler.handleErrorFailure(t, TAG, message -> ((MutableLiveData<String>) getError()).postValue(message));
            }
        });
    }

    private boolean validate(String ambientes, String direccion, String precio, String uso, String tipo) {
        String ambTrim = ambientes != null ? ambientes.trim() : "";
        String dirTrim = direccion != null ? direccion.trim() : "";
        String precioTrim = precio != null ? precio.trim() : "";
        String usoTrim = uso != null ? uso.trim() : "";
        String tipoTrim = tipo != null ? tipo.trim() : "";

        if (ambTrim.isEmpty() || dirTrim.isEmpty() || precioTrim.isEmpty() || usoTrim.isEmpty() || tipoTrim.isEmpty()) {
            ((MutableLiveData<String>) getError()).setValue("Todos los campos son obligatorios.");
            return false;
        }

        try {
            parsedAmbientes = Integer.parseInt(ambTrim);
        } catch (NumberFormatException e) {
            ((MutableLiveData<String>) getError()).setValue("Ambientes debe ser un número entero válido.");
            return false;
        }

        try {
            // Aceptar comas como separador decimal (e.g. 1234,56)
            String precioNormalized = precioTrim.replace(',', '.');
            parsedPrecio = Double.parseDouble(precioNormalized);
        } catch (NumberFormatException e) {
            ((MutableLiveData<String>) getError()).setValue("Precio debe ser un número válido.");
            return false;
        }

        return true;
    }
}