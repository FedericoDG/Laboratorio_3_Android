package com.federicodg80.inmobiliaria;

import android.app.Application;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class LoginActivityViewModel extends AndroidViewModel {
    private MutableLiveData<Boolean> mutableSacudida = new MutableLiveData<>();
    private SensorManager sensorManager;
    private List<Sensor> listaSensores;
    private ManejaSensor manejaSensor;
    public LoginActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Boolean> getMutableSacudida() {
        return mutableSacudida;
    }

    public void activarLecturasSensor() {
        sensorManager = (SensorManager) getApplication().getSystemService(Context.SENSOR_SERVICE);
        listaSensores = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);

        if (listaSensores.isEmpty()) return;

        manejaSensor = new ManejaSensor();
        sensorManager.registerListener(manejaSensor, listaSensores.get(0), SensorManager.SENSOR_DELAY_GAME);
    }

    private class ManejaSensor implements SensorEventListener {
        private long ultimaActualizacion = 0;
        private float ultimoX, ultimoY, ultimoZ;
        private static final int SACUDIR = 5000;

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            sensorManager.unregisterListener(manejaSensor);
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            long tiempoActual = System.currentTimeMillis();
            if ((tiempoActual - ultimaActualizacion) > 100) {
                long diferenciaTiempo = (tiempoActual - ultimaActualizacion);
                ultimaActualizacion = tiempoActual;

                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];

                float speed = Math.abs(x + y + z - ultimoX - ultimoY - ultimoZ) / diferenciaTiempo * 10000;

                if (speed > SACUDIR) {
                    mutableSacudida.setValue(true);
                }

                ultimoX = x;
                ultimoY = y;
                ultimoZ = z;
            }
        }
    }

}
