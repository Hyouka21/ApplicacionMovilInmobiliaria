package com.sosa.trabajofinalsosagaston.ui.inmueble;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sosa.trabajofinalsosagaston.modelo.Inmueble;

public class InmuebleDetalleViewModel extends ViewModel {
    MutableLiveData<Inmueble> inmueble ;
    public InmuebleDetalleViewModel(){
        inmueble = new MutableLiveData<>();
    }

    public MutableLiveData<Inmueble> getInmueble() {
        return inmueble;
    }
    public void setInmueble(Inmueble i){
        inmueble.setValue(i);
    }
}