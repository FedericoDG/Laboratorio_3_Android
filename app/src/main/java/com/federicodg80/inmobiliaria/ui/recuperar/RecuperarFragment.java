package com.federicodg80.inmobiliaria.ui.recuperar;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.federicodg80.inmobiliaria.databinding.FragmentRecoveryBinding;

public class RecuperarFragment extends Fragment {

    private FragmentRecoveryBinding binding;
    private RecuperarViewModel viewModel;

    public static RecuperarFragment newInstance() {
        return new RecuperarFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentRecoveryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        viewModel = new ViewModelProvider(this).get(RecuperarViewModel.class);

        // Observadores
        viewModel.getError().observe(getViewLifecycleOwner(), s -> {
            binding.tvErrorMessage.setText(s);
            binding.tvErrorMessage.setVisibility(View.VISIBLE);
        });

        viewModel.getSuccess().observe(getViewLifecycleOwner(), s -> {
            binding.tvErrorMessage.setText(s);
            binding.tvErrorMessage.setVisibility(View.VISIBLE);
        });

        // Eventos
        binding.btnRecuperar.setOnClickListener(view -> {
            binding.tvErrorMessage.setVisibility(View.VISIBLE);

        });

        return root;
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }

}