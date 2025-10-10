package com.federicodg80.inmobiliaria.ui.contratos;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.federicodg80.inmobiliaria.modelos.Alquiler;
import com.federicodg80.inmobiliaria.modelos.Inmueble;
import com.federicodg80.inmobiliaria.modelos.Inquilino;
import com.federicodg80.inmobiliaria.modelos.Pago;

import java.util.ArrayList;
import java.util.List;

public class ContratoDetallesViewModel extends AndroidViewModel {
    private MutableLiveData<Alquiler> mContrato;
    private MutableLiveData<Inquilino> mInquilino;
    private MutableLiveData<Inmueble> mInmueble;
    private MutableLiveData<List<Pago>> mPagos = new MutableLiveData<>(new ArrayList<>());

    public ContratoDetallesViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Alquiler> getContrato() {
        if (mContrato == null) {
            mContrato = new MutableLiveData<>();
        }
        return mContrato;
    }

    public LiveData<Inquilino> getInquilino() {
        if (mInquilino == null) {
            mInquilino = new MutableLiveData<>();
        }
        return mInquilino;
    }

    public LiveData<Inmueble> getInmueble() {
        if (mInmueble == null) {
            mInmueble = new MutableLiveData<>();
        }
        return mInmueble;
    }

    public LiveData<List<Pago>> getPagos() {
        if (mPagos == null) {
            mPagos = new MutableLiveData<>();
        }
        return mPagos;
    }

    public void loadContratoDetails(Bundle bundle) {
        Inmueble inmueble = (Inmueble) bundle.getSerializable("inmueble");
        Alquiler contrato = inmueble.getAlquiler();
        Inquilino inquilino = inmueble.getInquilino();
        List<Pago> pagos = contrato.getPagos();
        Log.d("XXX", "loadContratoDetails: " + pagos);
        mInmueble.setValue(inmueble);
        mContrato.setValue(contrato);
        mInquilino.setValue(inquilino);
        mPagos.setValue(pagos);
    }
}