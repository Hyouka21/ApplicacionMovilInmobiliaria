package com.sosa.trabajofinalsosagaston.ui.perfil;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sosa.trabajofinalsosagaston.modelo.Propietario;
import com.sosa.trabajofinalsosagaston.request.ApiClient;

public class PerfilViewModel extends ViewModel {
    private MutableLiveData<Propietario> propietario ;
    private MutableLiveData<Boolean> guardar ;
    private MutableLiveData<Boolean> editar ;
    ApiClient api;
    public PerfilViewModel (){
        propietario =  new MutableLiveData<>();
        api = ApiClient.getApi();
        guardar =  new MutableLiveData<>();
        editar =  new MutableLiveData<>();
    }

    public MutableLiveData<Propietario> getPropietario() {
        if(propietario == null){
            propietario =  new MutableLiveData<>();
        }
        return propietario;
    }

    public MutableLiveData<Boolean> getGuardar() {
        if(guardar == null){
            guardar =  new MutableLiveData<>();
        }
        return guardar;
    }

    public MutableLiveData<Boolean> getEditar() {
        if(editar == null){
            editar =  new MutableLiveData<>();
        }
        return editar;
    }
    public void iniciar(){

        propietario.setValue(api.obtenerUsuarioActual());
    }
    public void editar(){
        editar.setValue(true);
    }
    public void guardar(){
        guardar.setValue(true);
    }
    public void cambiar(Propietario p){
        api.actualizarPerfil(p);
    }

}