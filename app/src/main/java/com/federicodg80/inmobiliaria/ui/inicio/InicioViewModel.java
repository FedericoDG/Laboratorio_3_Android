package com.federicodg80.inmobiliaria.ui.inicio;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.maplibre.android.annotations.MarkerOptions;
import org.maplibre.android.camera.CameraPosition;
import org.maplibre.android.camera.CameraUpdateFactory;
import org.maplibre.android.geometry.LatLng;
import org.maplibre.android.maps.MapLibreMap;
import org.maplibre.android.maps.OnMapReadyCallback;
import org.maplibre.android.maps.Style;

public class InicioViewModel extends AndroidViewModel {
    private MutableLiveData<MapaActual> mapaActualMutable;

    public InicioViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<MapaActual> getMapaActualMutable() {
        if (mapaActualMutable == null) {
            mapaActualMutable = new MutableLiveData<>();
        }
        return mapaActualMutable;
    }

    public static class MapaActual implements OnMapReadyCallback {
        LatLng marcador = new LatLng(-34.636222, -58.420022);

        @Override
        public void onMapReady(@NonNull MapLibreMap mapLibreMap) {
            // Configuramos estilo primero
            // https://api.maptiler.com/maps/topo-v2/style.json?key=Xe23W0QKIv1Ui8hTKQTi
            // https://maputnik.github.io/osm-liberty/style.json
            // https://demotiles.maplibre.org/style.json
            mapLibreMap.setStyle("https://api.maptiler.com/maps/basic-v2/style.json?key=Xe23W0QKIv1Ui8hTKQTi", new Style.OnStyleLoaded() {
                @Override
                public void onStyleLoaded(@NonNull Style style) {
                    mapLibreMap.addMarker(new MarkerOptions().position(marcador).title("Casa"));

                    CameraPosition cameraPosition = new CameraPosition.Builder()
                            .target(marcador) // centro de la cámara
                            .zoom(15) // nivel de zoom, entre 0 y 22
                            .bearing(0) // orientación de la cámara, entre 0 y 360 grados
                            .tilt(0) // inclinación de la cámara, entre 0 y 60 grados
                            .build(); // creamos la posición de cámara

                    mapLibreMap.easeCamera(
                            CameraUpdateFactory.newCameraPosition(cameraPosition),
                            2000
                    );
                }
            });
        }
    }

    public void cargarMapa() {
        // creamos un callback y lo publicamos en el LiveData
        MapaActual mapa = new MapaActual();
        mapaActualMutable.setValue(mapa);
    }
}