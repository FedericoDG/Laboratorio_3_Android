package com.federicodg80.inmobiliaria.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.federicodg80.inmobiliaria.MainActivity;
import com.federicodg80.inmobiliaria.R;
import com.federicodg80.inmobiliaria.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {
    private FragmentLoginBinding binding;
    private LoginFragmentViewModel viewmodel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        viewmodel = new LoginFragmentViewModel(getActivity().getApplication());

        // Observers
        viewmodel.isLoginSuccess().observe(getViewLifecycleOwner(), success -> {
            // Inicia MainActivity y finaliza LoginActivity
            requireActivity().startActivity(new Intent(requireContext(), MainActivity.class));
            requireActivity().finish();

        });

        viewmodel.getErrorMessage().observe(getViewLifecycleOwner(), message -> {
                binding.tvErrorMessage.setText(message);
                binding.tvErrorMessage.setVisibility(View.VISIBLE);
        });

        // Eventos
        binding.btnLogin.setOnClickListener(view -> {
            viewmodel.login(binding.etUsername.getText().toString(), binding.etPassword.getText().toString());
        });

        binding.tvRecoveryLink.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_recoveryFragment);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}
