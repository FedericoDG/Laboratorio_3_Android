package com.federicodg80.inmobiliaria.ui.inmuebles;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.core.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.federicodg80.inmobiliaria.R;
import com.federicodg80.inmobiliaria.databinding.FragmentInmuebleCrearBinding;

public class InmuebleCrearFragment extends Fragment {
    private FragmentInmuebleCrearBinding binding;
    private InmuebleCrearViewModel viewmodel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentInmuebleCrearBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        viewmodel = new ViewModelProvider(this).get(InmuebleCrearViewModel.class);

        // Observers
        viewmodel.getError().observe(getViewLifecycleOwner(), mensaje -> {
            if (mensaje == null || mensaje.trim().isEmpty()) {
                binding.tvErrorMessage.setVisibility(View.GONE);
            } else {
                binding.tvErrorMessage.setVisibility(View.VISIBLE);
                binding.tvErrorMessage.setText(mensaje);
            }
        });

        viewmodel.getSuccess().observe(getViewLifecycleOwner(),mensaje -> {
            if (mensaje == null || mensaje.trim().isEmpty()) {
                binding.tvErrorMessage.setVisibility(View.GONE);
            } else {
                binding.tvErrorMessage.setVisibility(View.VISIBLE);
                binding.tvErrorMessage.setTextColor(ContextCompat.getColor(requireContext(), R.color.green_500));
                binding.tvErrorMessage.setText(mensaje);
            }
        });

        // Eventos
        binding.btnGuardar.setOnClickListener(v -> {
            binding.tvErrorMessage.setVisibility(View.GONE);
            String ambientes = binding.etAmbientes.getText() != null ? binding.etAmbientes.getText().toString() : "";
            String direccion = binding.etDireccion.getText() != null ? binding.etDireccion.getText().toString() : "";
            String precio = binding.etPrecio.getText() != null ? binding.etPrecio.getText().toString() : "";
            String uso = binding.etUso.getText() != null ? binding.etUso.getText().toString() : "";
            String tipo = binding.etTipo.getText() != null ? binding.etTipo.getText().toString() : "";

            viewmodel.createProperty(ambientes, direccion, precio, uso, tipo);

            binding.etAmbientes.setText("");
            binding.etDireccion.setText("");
            binding.etPrecio.setText("");
            binding.etUso.setText("");
            binding.etTipo.setText("");
        });

        binding.btnAbrirCamara.setOnClickListener(v -> {
        // TODO: Implementar funcionalidad de abrir la cámara
            Toast.makeText(getActivity(), "Jajaja. ¡Iluso!", Toast.LENGTH_LONG).show();
        });

        return root;
    }
    
    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}