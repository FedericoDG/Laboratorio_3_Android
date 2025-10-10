package com.federicodg80.inmobiliaria.utils;

import android.util.Log;

import org.json.JSONObject;

import retrofit2.Response;

public class ErrorHandler {

    /**
     * Interfaz simple para callback de errores
     */
    public interface ErrorCallback {
        void onError(String message);
    }

    /**
     * Maneja respuestas de error del servidor
     * Extraee el campo "message" del JSON de error
     */
    public static void handleErrorResponse(Response<?> response, String tag, ErrorCallback callback) {
        try {
            String errorJson = null;
            if (response.errorBody() != null) {
                errorJson = response.errorBody().string();
            }

            if (errorJson != null) {
                JSONObject jsonObj = new JSONObject(errorJson);
                String message = jsonObj.optString("message", "Error desconocido");
                callback.onError(message);
            } else {
                callback.onError("Error desconocido");
            }
        } catch (Exception e) {
            callback.onError("Error desconocido");
            Log.e(tag, "Error al procesar respuesta de error", e);
        }
        Log.e(tag, "Código HTTP: " + response.code());
    }

    /**
     * Maneja errores de red
     */
    public static void handleErrorFailure(Throwable t, String tag, ErrorCallback callback) {
        String message = t.getMessage() != null ? t.getMessage() : "Error de conexión";
        callback.onError(message);
        Log.d(tag, "onFailure: " + message);
    }
}
