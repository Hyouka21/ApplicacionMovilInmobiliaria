package com.sosa.trabajofinalsosagaston.ui.contrato;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sosa.trabajofinalsosagaston.modelo.Inmueble;
import com.sosa.trabajofinalsosagaston.request.ApiClient;

import java.util.ArrayList;

public class ContratoViewModel extends ViewModel {
    MutableLiveData<ArrayList<Inmueble>> lista ;
    MutableLiveData<Integer> visibilidad;
    ApiClient api;
    public ContratoViewModel(){
        lista = new MutableLiveData<>();
        visibilidad = new MutableLiveData<>();
        api = ApiClient.getApi();
    }

    public MutableLiveData<Integer> getVisibilidad() {
        return visibilidad;
    }

    public MutableLiveData<ArrayList<Inmueble>> getLista() {
        return lista;
    }

    public void setInmueblesAlquilados(){
        if(api.obtenerPropiedadesAlquiladas().size() == 0){
            visibilidad.setValue(View.VISIBLE);
        }else {
            visibilidad.setValue(View.INVISIBLE);
            lista.setValue(api.obtenerPropiedadesAlquiladas());
        }
    }
}