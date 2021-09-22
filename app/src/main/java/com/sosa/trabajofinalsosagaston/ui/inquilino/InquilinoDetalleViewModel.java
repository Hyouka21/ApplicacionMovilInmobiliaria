package com.sosa.trabajofinalsosagaston.ui.inquilino;

import android.os.Bundle;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sosa.trabajofinalsosagaston.modelo.Inmueble;
import com.sosa.trabajofinalsosagaston.modelo.Inquilino;
import com.sosa.trabajofinalsosagaston.request.ApiClient;

public class InquilinoDetalleViewModel extends ViewModel {
    MutableLiveData<Inquilino> inquilino ;
    Inmueble i;
    ApiClient api ;

    public InquilinoDetalleViewModel() {
        inquilino = new MutableLiveData<>();
        api = ApiClient.getApi();
    }

    public MutableLiveData<Inquilino> getInquilino() {
        return inquilino;
    }

    public void setInquilino(Bundle bundle) {
        i = (Inmueble) bundle.getSerializable("inmueble");
        inquilino.setValue(api.obtenerInquilino(i));
    }
}