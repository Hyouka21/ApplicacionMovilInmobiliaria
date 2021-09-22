package com.sosa.trabajofinalsosagaston.ui.inmueble;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sosa.trabajofinalsosagaston.modelo.Inmueble;
import com.sosa.trabajofinalsosagaston.request.ApiClient;

import java.util.ArrayList;

public class InmuebleViewModel extends ViewModel {
    MutableLiveData<ArrayList<Inmueble>> inmuebles ;
    ApiClient api ;

    public InmuebleViewModel() {
        this.inmuebles = new MutableLiveData<>();
    }

    public MutableLiveData<ArrayList<Inmueble>> getInmuebles() {

        return inmuebles;
    }
    public void setInmuebles(){
        api = ApiClient.getApi();

        inmuebles.setValue(api.obtnerPropiedades());
    }
}