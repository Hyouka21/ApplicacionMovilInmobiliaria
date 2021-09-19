package com.sosa.trabajofinalsosagaston.ui.inmueble;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sosa.trabajofinalsosagaston.modelo.Inmueble;

import java.util.ArrayList;

public class InmuebleViewModel extends ViewModel {
    MutableLiveData<ArrayList<Inmueble>> inmuebles ;

    public InmuebleViewModel() {
        this.inmuebles = new MutableLiveData<>();
    }

    public MutableLiveData<ArrayList<Inmueble>> getInmuebles() {
        return inmuebles;
    }
    public void setInmuebles(ArrayList<Inmueble> lista){
        inmuebles.setValue(lista);
    }
}