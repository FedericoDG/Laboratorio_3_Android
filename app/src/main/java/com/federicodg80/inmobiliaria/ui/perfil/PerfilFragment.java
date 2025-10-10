package com.federicodg80.inmobiliaria.ui.perfil;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.federicodg80.inmobiliaria.R;
import com.federicodg80.inmobiliaria.databinding.FragmentPerfilBinding;

public class PerfilFragment extends Fragment {
    private FragmentPerfilBinding binding;
    private PerfilViewModel viewmodel;

    public static PerfilFragment newInstance() {
        return new PerfilFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        viewmodel = new ViewModelProvider(this).get(PerfilViewModel.class);

        // Observers
        viewmodel.getPropietario().observe(getViewLifecycleOwner(), propietario -> {
            binding.etCodigo.setText(String.valueOf(propietario.getIdPropietario()));
            binding.etDni.setText(String.valueOf(propietario.getDni()));
            binding.etNombre.setText(propietario.getNombre());
            binding.etApellido.setText(propietario.getApellido());
            binding.etEmail.setText(propietario.getMail());
            binding.etTelefono.setText(String.valueOf(propietario.getTelefono()));
        });

        viewmodel.getError().observe(getViewLifecycleOwner(), mensaje -> {
            binding.tvErrorMessage.setVisibility(View.VISIBLE);
            binding.tvErrorMessage.setTextColor(getResources().getColor(R.color.red_500));
            binding.tvErrorMessage.setText(mensaje);
        });

        viewmodel.getSuccess().observe(getViewLifecycleOwner(), mensaje -> {
            binding.tvErrorMessage.setVisibility(View.VISIBLE);
            binding.tvErrorMessage.setTextColor(getResources().getColor(R.color.green_500));
            binding.tvErrorMessage.setText(mensaje);
        });

        viewmodel.getProfile();
        viewmodel.getError();
        viewmodel.getSuccess();

        // Eventos
        binding.btnEditar.setOnClickListener(v -> {
            String idPropietario = binding.etCodigo.getText().toString();
            String dni = binding.etDni.getText().toString();
            String nombre = binding.etNombre.getText().toString();
            String apellido = binding.etApellido.getText().toString();
            String email = binding.etEmail.getText().toString();
            String telefono = binding.etTelefono.getText().toString();
            String password = binding.etPassword.getText().toString();

            viewmodel.updateProfile(idPropietario, dni, nombre, apellido, email, telefono, password);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}