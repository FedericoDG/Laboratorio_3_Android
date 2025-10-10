package com.federicodg80.inmobiliaria.ui.contratos;

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
import com.federicodg80.inmobiliaria.databinding.FragmentContratosBinding;

public class ContratosFragment extends Fragment {
    private FragmentContratosBinding binding;
    private ContratosViewModel viewmodel;

    public static ContratosFragment newInstance() {
        return new ContratosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentContratosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        viewmodel = new ViewModelProvider(this).get(ContratosViewModel.class);

        // Observers
        viewmodel.getListaInmuebles().observe(getViewLifecycleOwner(), inmuebles -> {
            GridLayoutManager glm = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
            binding.rvContratos.setLayoutManager(glm);

            InmuebleAdapter.OnItemClickListener listener = inmueble -> {
                Bundle bundle = new Bundle();
                bundle.putSerializable("inmueble", inmueble);
                // Navegar a detalles
                Navigation.findNavController(root).navigate(R.id.nav_contrato_detalles, bundle);
            };

            InmuebleAdapter newAdapter = new InmuebleAdapter(inmuebles, getContext(), inflater, listener);
            binding.rvContratos.setAdapter(newAdapter);
        });

        viewmodel.getError();
        viewmodel.getProperties();

        return root;
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}