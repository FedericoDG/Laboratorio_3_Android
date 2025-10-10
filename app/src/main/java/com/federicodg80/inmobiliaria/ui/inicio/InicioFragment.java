package com.federicodg80.inmobiliaria.ui.inicio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.federicodg80.inmobiliaria.databinding.FragmentHomeBinding;

import org.maplibre.android.MapLibre;
import org.maplibre.android.WellKnownTileServer;

public class InicioFragment extends Fragment {
    private FragmentHomeBinding binding;
    private InicioViewModel viewmodel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        MapLibre.getInstance(getActivity(), "Xe23W0QKIv1Ui8hTKQTi", WellKnownTileServer.MapTiler);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        viewmodel = new ViewModelProvider(this).get(InicioViewModel.class);

        // Observers
        viewmodel.getMapaActualMutable().observe(getViewLifecycleOwner(), mapaActual -> {
            binding.mapa.getMapAsync(mapaActual);
        });

        viewmodel.cargarMapa();

        return root;
    }

    // Delegar los métodos del ciclo de vida al MapView
    @Override
    public void onStart() {
        super.onStart();
        binding.mapa.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.mapa.onResume();
    }

    @Override
    public void onPause() {
        binding.mapa.onPause();
        super.onPause();
    }

    @Override
    public void onStop() {
        binding.mapa.onStop();
        super.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }


    @Override
    public void onDestroyView() {
        binding.mapa.onDestroy();
        binding = null;
        super.onDestroyView();
    }
}