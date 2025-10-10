package com.federicodg80.inmobiliaria.ui.inmuebles;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.federicodg80.inmobiliaria.R;
import com.federicodg80.inmobiliaria.adaptadores.InmuebleAdapter;
import com.federicodg80.inmobiliaria.databinding.FragmentInmueblesBinding;

public class InmueblesFragment extends Fragment {
    private FragmentInmueblesBinding binding;
    private InmueblesViewModel viewmodel;

    public static InmueblesFragment newInstance() {
        return new InmueblesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentInmueblesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        viewmodel = new ViewModelProvider(this).get(InmueblesViewModel.class);

        binding.fabAgregarInmueble.setOnClickListener(vista -> {
            // Navegar a crear
            Navigation.findNavController(root).navigate(R.id.nav_inmueble_crear);
        });

        viewmodel.getListaInmuebles().observe(getViewLifecycleOwner(), inmuebles -> {
            GridLayoutManager glm = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
            binding.rvInmuebles.setLayoutManager(glm);

            InmuebleAdapter.OnItemClickListener listener = inmueble -> {
                Bundle bundle = new Bundle();
                bundle.putSerializable("inmueble", inmueble);
                // Naveegar a detalles
                Navigation.findNavController(root).navigate(R.id.nav_inmueble_detalles, bundle);
            };

            InmuebleAdapter newAdapter = new InmuebleAdapter(inmuebles, getContext(), inflater, listener);
            binding.rvInmuebles.setAdapter(newAdapter);
        });

        viewmodel.getProperties();

        return root;
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}