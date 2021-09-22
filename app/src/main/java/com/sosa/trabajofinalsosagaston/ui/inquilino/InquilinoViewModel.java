package com.sosa.trabajofinalsosagaston.ui.inquilino;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sosa.trabajofinalsosagaston.modelo.Inmueble;
import com.sosa.trabajofinalsosagaston.request.ApiClient;

import java.util.ArrayList;

public class InquilinoViewModel extends ViewModel {
    MutableLiveData<ArrayList<Inmueble>> lista ;
    ApiClient api;
    public InquilinoViewModel(){
        lista = new MutableLiveData<>();
        api = ApiClient.getApi();
    }

    public MutableLiveData<ArrayList<Inmueble>> getLista() {
        return lista;
    }
    public void setInmueblesAlquilados(){

        lista.setValue(api.obtenerPropiedadesAlquiladas());
    }
}