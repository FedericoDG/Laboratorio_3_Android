package com.federicodg80.inmobiliaria.ui.contratos;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.federicodg80.inmobiliaria.adaptadores.PagoAdaptater;
import com.federicodg80.inmobiliaria.databinding.FragmentPagoDetallesBinding;

public class PagoDetallesFragment extends Fragment {
    private FragmentPagoDetallesBinding binding;
    private PagoDetallesViewModel viewmodel;

    public static PagoDetallesFragment newInstance() {
        return new PagoDetallesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentPagoDetallesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        viewmodel = new ViewModelProvider(this).get(PagoDetallesViewModel.class);

        // Observers
        viewmodel.getListaPagos().observe(getViewLifecycleOwner(), pagos -> {
            GridLayoutManager glm = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
            binding.rvPagos.setLayoutManager(glm);

            PagoAdaptater newAdapter = new PagoAdaptater(pagos, getContext(), inflater);
            binding.rvPagos.setAdapter(newAdapter);
        });

        viewmodel.loadPagos(getArguments());

        return root;
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}