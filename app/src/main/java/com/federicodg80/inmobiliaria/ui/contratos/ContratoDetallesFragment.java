package com.federicodg80.inmobiliaria.ui.contratos;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.federicodg80.inmobiliaria.R;
import com.federicodg80.inmobiliaria.databinding.FragmentContratoDetallesBinding;
import com.federicodg80.inmobiliaria.modelos.Pago;

import java.util.ArrayList;
import java.util.List;

public class ContratoDetallesFragment extends Fragment {
    private FragmentContratoDetallesBinding binding;
    private ContratoDetallesViewModel viewmodel;

    public static ContratoDetallesFragment newInstance() {
        return new ContratoDetallesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentContratoDetallesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        viewmodel = new ViewModelProvider(this).get(ContratoDetallesViewModel.class);

        // Observers
        viewmodel.getContrato().observe(getViewLifecycleOwner(), contrato -> {
            binding.etCodigo.setText(String.valueOf(contrato.getIdAlquiler()));
            binding.etFechaInicio.setText(contrato.getFechaInicioFormatted());
            binding.etFechaFinalizacion.setText(contrato.getFechaFinFormatted());
            binding.etMonto.setText(String.valueOf(contrato.getPrecio()));
        });

        viewmodel.getInquilino().observe(getViewLifecycleOwner(), inquilino -> {
            binding.etInquilino.setText(inquilino.getApellido() + ", " + inquilino.getNombre());
        });

        viewmodel.getInmueble().observe(getViewLifecycleOwner(), inmueble -> {
            binding.etInmueble.setText(inmueble.getDireccion());
        });

        viewmodel.loadContratoDetails(getArguments());

        // Eventos
        binding.btnVerPagos.setOnClickListener(view -> {
            List<Pago> pagos = viewmodel.getPagos().getValue();
            Bundle bundle = new Bundle();
            bundle.putSerializable("pagos", new ArrayList<>(pagos));
            Navigation.findNavController(view).navigate(R.id.nav_pago_detalles, bundle);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}