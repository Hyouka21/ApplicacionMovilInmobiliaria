package com.sosa.trabajofinalsosagaston.ui.pago;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sosa.trabajofinalsosagaston.modelo.Contrato;
import com.sosa.trabajofinalsosagaston.modelo.Inmueble;
import com.sosa.trabajofinalsosagaston.modelo.Pago;
import com.sosa.trabajofinalsosagaston.request.ApiClient;

import java.util.ArrayList;

public class PagoViewModel extends ViewModel {
    MutableLiveData<ArrayList<Pago>> lista ;
    MutableLiveData<Integer> visibilidad;
    ApiClient api;
    public PagoViewModel(){
        lista = new MutableLiveData<>();
        visibilidad = new MutableLiveData<>();
        api = ApiClient.getApi();
    }

    public MutableLiveData<Integer> getVisibilidad() {
        return visibilidad;
    }

    public MutableLiveData<ArrayList<Pago>> getLista() {
        return lista;
    }

    public void setPagos(Bundle bundle){
        Contrato c = (Contrato) bundle.getSerializable("contrato");
        if(api.obtenerPropiedadesAlquiladas().size() == 0){
            visibilidad.setValue(View.VISIBLE);
        }else {
            visibilidad.setValue(View.INVISIBLE);
            lista.setValue(api.obtenerPagos(c));
        }
    }
}