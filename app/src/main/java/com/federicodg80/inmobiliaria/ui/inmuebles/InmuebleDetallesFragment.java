package com.federicodg80.inmobiliaria.ui.inmuebles;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.federicodg80.inmobiliaria.R;
import com.federicodg80.inmobiliaria.databinding.FragmentInmuebleDetallesBinding;

public class InmuebleDetallesFragment extends Fragment {
    private FragmentInmuebleDetallesBinding binding;
    private InmuebleDetallesViewModel viewmodel;

    public static InmuebleDetallesFragment newInstance() {
        return new InmuebleDetallesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentInmuebleDetallesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        viewmodel = new ViewModelProvider(this).get(InmuebleDetallesViewModel.class);

        // Observers
        viewmodel.getInmueble().observe(getViewLifecycleOwner(), inmueble -> {
            Glide.with(requireContext())
                    .load(inmueble.getImagen())
                    .placeholder(R.drawable.placeholder_inmueble)
                    .error(R.drawable.placeholder_inmueble)
                    .into(binding.ivInmueble);
            binding.switchActivo.setChecked(inmueble.isDisponible());
            binding.etCodigo.setText(String.valueOf(inmueble.getIdInmueble()));
            binding.etAmbientes.setText(String.valueOf(inmueble.getAmbientes()));
            binding.etDireccion.setText(inmueble.getDireccion());
            binding.etPrecio.setText(String.valueOf(inmueble.getPrecio()));
            binding.etUso.setText(inmueble.getUso());
            binding.etTipo.setText(inmueble.getTipo());
        });

        viewmodel.getError().observe(getViewLifecycleOwner(), mensaje -> {
            binding.tvErrorMessage.setVisibility(View.VISIBLE);
            binding.tvErrorMessage.setText(mensaje);
        });

        viewmodel.getSuccess().observe(getViewLifecycleOwner(), mensaje -> {
            binding.tvErrorMessage.setVisibility(View.VISIBLE);
            binding.tvErrorMessage.setTextColor(getResources().getColor(R.color.green_500));
            binding.tvErrorMessage.setText(mensaje);
        });

        viewmodel.getProperty(getArguments());
        viewmodel.getInmueble();
        viewmodel.getError();
        viewmodel.getSuccess();

        // Eventos
        viewmodel.getInmueble().observe(getViewLifecycleOwner(), inmueble -> {
            // Quita el listener temporalmente
            binding.switchActivo.setOnCheckedChangeListener(null);

            // Usa un método helper que nunca falla (devuelve false si null)
            binding.switchActivo.setChecked(viewmodel.isDisponibleSafe());

            // Vuelve a poner el listener
            binding.switchActivo.setOnCheckedChangeListener((buttonView, isChecked) -> {
                viewmodel.updateProperty(getArguments(), isChecked);
            });
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}