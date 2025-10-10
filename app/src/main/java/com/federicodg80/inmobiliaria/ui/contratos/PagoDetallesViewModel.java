package com.federicodg80.inmobiliaria.ui.contratos;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.federicodg80.inmobiliaria.modelos.Pago;

import java.util.ArrayList;
import java.util.List;

public class PagoDetallesViewModel extends AndroidViewModel {
    private MutableLiveData<List<Pago>> mListaPagos;

    public PagoDetallesViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Pago>> getListaPagos() {
        if (mListaPagos == null) {
            mListaPagos = new MutableLiveData<>();
        }
        return mListaPagos;
    }

    public void loadPagos(Bundle bundle) {
        List<Pago> pagos = (ArrayList<Pago>) bundle.getSerializable("pagos");
        mListaPagos.setValue(pagos);
    }

}