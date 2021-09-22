package com.sosa.trabajofinalsosagaston.ui.inmueble;

import android.os.Bundle;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sosa.trabajofinalsosagaston.modelo.Inmueble;
import com.sosa.trabajofinalsosagaston.request.ApiClient;

public class InmuebleDetalleViewModel extends ViewModel {
    MutableLiveData<Inmueble> inmueble ;
    ApiClient api;
    Inmueble i;
    public InmuebleDetalleViewModel(){
        inmueble = new MutableLiveData<>();
    }

    public MutableLiveData<Inmueble> getInmueble() {
        return inmueble;
    }
    public void setInmueble(Bundle bundle){
        i = (Inmueble) bundle.getSerializable("inmueble");
        inmueble.setValue(i);
    }
    public void guardarCambios(boolean cheak){
        api = ApiClient.getApi();
        i.setEstado(cheak);
        api.actualizarInmueble(i);

    }
}