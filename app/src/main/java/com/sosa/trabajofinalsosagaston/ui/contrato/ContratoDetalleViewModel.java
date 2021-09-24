package com.sosa.trabajofinalsosagaston.ui.contrato;

import android.os.Bundle;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sosa.trabajofinalsosagaston.modelo.Contrato;
import com.sosa.trabajofinalsosagaston.modelo.Inmueble;
import com.sosa.trabajofinalsosagaston.modelo.Inquilino;
import com.sosa.trabajofinalsosagaston.request.ApiClient;

public class ContratoDetalleViewModel extends ViewModel {
    MutableLiveData<Contrato> contrato ;
    Inmueble i;
    ApiClient api ;

    public ContratoDetalleViewModel() {
        contrato = new MutableLiveData<>();
        api = ApiClient.getApi();
    }

    public MutableLiveData<Contrato> getContrato() {
        return contrato;
    }

    public void setContrato(Bundle bundle) {
        i = (Inmueble) bundle.getSerializable("inmueble");

        contrato.setValue(api.obtenerContratoVigente(i));
    }
}