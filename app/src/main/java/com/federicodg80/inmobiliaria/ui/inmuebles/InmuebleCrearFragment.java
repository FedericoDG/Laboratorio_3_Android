package com.federicodg80.inmobiliaria.ui.inmuebles;

import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
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
    private Uri selectedImageUri;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentInmuebleCrearBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        viewmodel = new ViewModelProvider(this).get(InmuebleCrearViewModel.class);

        // Observers
        viewmodel.getError().observe(getViewLifecycleOwner(), mensaje -> {
            binding.tvErrorMessage.setVisibility(View.VISIBLE);
            binding.tvErrorMessage.setTextColor(getResources().getColor(R.color.red_500));
            binding.tvErrorMessage.setText(mensaje);
        });

        viewmodel.getSuccess().observe(getViewLifecycleOwner(),mensaje -> {
            binding.tvErrorMessage.setVisibility(View.VISIBLE);
            binding.tvErrorMessage.setTextColor(ContextCompat.getColor(requireContext(), R.color.green_500));
            binding.tvErrorMessage.setText(mensaje);
        });

        // Eventos
        binding.btnGuardar.setOnClickListener(v -> {
            binding.tvErrorMessage.setVisibility(View.GONE);
            String ambientes = binding.etAmbientes.getText() != null ? binding.etAmbientes.getText().toString() : "";
            String direccion = binding.etDireccion.getText() != null ? binding.etDireccion.getText().toString() : "";
            String precio = binding.etPrecio.getText() != null ? binding.etPrecio.getText().toString() : "";
            String uso = binding.etUso.getText() != null ? binding.etUso.getText().toString() : "";
            String tipo = binding.etTipo.getText() != null ? binding.etTipo.getText().toString() : "";

            // viewmodel.createProperty(ambientes, direccion, precio, uso, tipo);
            viewmodel.createProperty(ambientes, direccion, precio, uso, tipo, selectedImageUri);

            binding.etAmbientes.setText("");
            binding.etDireccion.setText("");
            binding.etPrecio.setText("");
            binding.etUso.setText("");
            binding.etTipo.setText("");
        });

        binding.btnAbrirCamara.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*"); // Solo imágenes
            startActivityForResult(intent, 101); // requestCode
        });

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101 && resultCode == Activity.RESULT_OK && data != null) {
            selectedImageUri = data.getData();  // Recupero la imagen
            binding.ivInmueble.setImageURI(selectedImageUri);
        }
    }



    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}