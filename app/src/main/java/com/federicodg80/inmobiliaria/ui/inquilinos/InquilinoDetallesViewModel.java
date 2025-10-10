package com.federicodg80.inmobiliaria.ui.inquilinos;

import android.os.Bundle;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.federicodg80.inmobiliaria.modelos.Inmueble;
import com.federicodg80.inmobiliaria.modelos.Inquilino;

public class InquilinoDetallesViewModel extends AndroidViewModel {
    private MutableLiveData<Inquilino> mInquilino;
    public InquilinoDetallesViewModel(android.app.Application application) {
        super(application);
    }

    public LiveData<Inquilino> getInquilino() {
        if (mInquilino == null) {
            mInquilino = new MutableLiveData<>();
        }
        return mInquilino;
    }
    public void loadInquilino(Bundle bundle) {
        Inmueble inmueble = (Inmueble) bundle.getSerializable("inmueble");
        Inquilino inquilino = inmueble.getInquilino();
        mInquilino.setValue(inquilino);
    }
}