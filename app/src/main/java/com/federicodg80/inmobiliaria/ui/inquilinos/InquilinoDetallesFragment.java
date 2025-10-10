package com.federicodg80.inmobiliaria.ui.inquilinos;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.federicodg80.inmobiliaria.databinding.FragmentInquilinoDetallesBinding;

public class InquilinoDetallesFragment extends Fragment {
    private FragmentInquilinoDetallesBinding binding;
    private InquilinoDetallesViewModel viewmodel;

    public static InquilinoDetallesFragment newInstance() {
        return new InquilinoDetallesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentInquilinoDetallesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        viewmodel = new ViewModelProvider(this).get(InquilinoDetallesViewModel.class);

        // Observers
        viewmodel.getInquilino().observe(getViewLifecycleOwner(), inquilino -> {
            binding.etCodigo.setText(String.valueOf(inquilino.getIdInquilino()));
            binding.etDni.setText(String.valueOf(inquilino.getDni()));
            binding.etNombre.setText(inquilino.getNombre());
            binding.etApellido.setText(inquilino.getApellido());
            binding.etDireccion.setText(inquilino.getDireccion());
            binding.etTelefono.setText(String.valueOf(inquilino.getTelefono()));
        });

        viewmodel.loadInquilino(getArguments());


        return root;
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}