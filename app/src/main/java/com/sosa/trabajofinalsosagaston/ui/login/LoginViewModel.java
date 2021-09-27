package com.sosa.trabajofinalsosagaston.ui.login;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sosa.trabajofinalsosagaston.modelo.Propietario;
import com.sosa.trabajofinalsosagaston.request.ApiClient;

public class LoginViewModel extends ViewModel {
    private MutableLiveData<Propietario> propietario ;
    private MutableLiveData<Boolean> mensaje ;
    private MutableLiveData<Coordenada> cordenada;
    private MutableLiveData<Boolean> llamar ;
    private boolean estado =false;
    private int contador=0;
    ApiClient api;
    public LoginViewModel (){
        propietario =  new MutableLiveData<>();
        mensaje =  new MutableLiveData<>();
        cordenada =  new MutableLiveData<>();
        llamar =  new MutableLiveData<>();
        api = ApiClient.getApi();
    }

    public MutableLiveData<Boolean> getLlamar() {
        return llamar;
    }

    public MutableLiveData<Coordenada> getCordenada() {
        return cordenada;
    }

    public void cordenadas(float X , float Y){
        if(estado){

        }
        else{
            cordenada.setValue(new Coordenada(X,Y));
        }
    }
    public void controlador(Coordenada coor){
        int contadorA=0;
        int contadorB=0;
        if(coor.cordenadaX > 7 && contadorA<8){
            contadorA++;
            contador++;
        }
        if(coor.cordenadaX< -7&& contadorB<8){
            contadorB++;
            contador++;
        }
        if(contador==16){
            contador=0;
            estado=true;
            cordenada.setValue(new Coordenada(0,0));
            llamar.setValue(true);
            Thread th = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                    Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    estado=false;

                }
            });
            th.start();
        }

    }

    public MutableLiveData<Propietario> getPropietario() {
        return propietario;
    }

    public MutableLiveData<Boolean> getMensaje() {
        return mensaje;
    }


    public void iniciar(String email , String clave){
       Propietario p = api.login(email.replace(" ",""),clave);
       if(p!=null){
           propietario.setValue(p);

       }else{
           mensaje.setValue(true);
       }
    }
    public class Coordenada {
        float cordenadaX;
        float cordenadaY;

        public Coordenada(float cordenadaX, float cordenadaY) {
            this.cordenadaX = cordenadaX;
            this.cordenadaY = cordenadaY;
        }

        public float getCordenadaX() {
            return cordenadaX;
        }

        public void setCordenadaX(float cordenadaX) {
            this.cordenadaX = cordenadaX;
        }

        public float getCordenadaY() {
            return cordenadaY;
        }

        public void setCordenadaY(float cordenadaY) {
            this.cordenadaY = cordenadaY;
        }
    }

}
