package com.sosa.trabajofinalsosagaston.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sosa.trabajofinalsosagaston.modelo.Propietario;
import com.sosa.trabajofinalsosagaston.request.ApiClient;

public class LoginViewModel extends ViewModel {
    private MutableLiveData<Propietario> propietario ;
    private MutableLiveData<Boolean> mensaje ;
    ApiClient api;
    public LoginViewModel (){
        propietario =  new MutableLiveData<>();
        mensaje =  new MutableLiveData<>();
        api = ApiClient.getApi();
    }

    public MutableLiveData<Propietario> getPropietario() {
        return propietario;
    }

    public void setPropietario(MutableLiveData<Propietario> propietario) {
        this.propietario = propietario;
    }

    public MutableLiveData<Boolean> getMensaje() {
        return mensaje;
    }

    public void setMensaje(MutableLiveData<Boolean> mensaje) {
        this.mensaje = mensaje;
    }

    public void iniciar(String email , String clave){
       Propietario p = api.login(email.replace(" ",""),clave);
       if(p!=null){
           propietario.setValue(p);

       }else{
           mensaje.setValue(true);
       }
    }
}
