package com.sosa.trabajofinalsosagaston.ui.perfil;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sosa.trabajofinalsosagaston.modelo.Propietario;
import com.sosa.trabajofinalsosagaston.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {
    private MutableLiveData<Propietario> propietario ;
    private MutableLiveData<Boolean> guardar ;
    private MutableLiveData<Boolean> editar ;

    private Context context;

    public PerfilViewModel(@NonNull Application application) {
        super(application);
        propietario =  new MutableLiveData<>();

        guardar =  new MutableLiveData<>();
        editar =  new MutableLiveData<>();
        context = application.getApplicationContext();
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

        SharedPreferences sp = context.getSharedPreferences("datos",0);
        String token = sp.getString("token","-1");
        Call<Propietario> prop = ApiClient.getMyApiClient().obtenerUsuario(token);
        prop.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if(response.isSuccessful()){
                    propietario.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                Toast.makeText(context, "Ocurrio un error inesperado"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void editar(){
        editar.setValue(true);
    }
    public void guardar(){
        guardar.setValue(true);
    }
    public void cambiar(Propietario p){
        SharedPreferences sp = context.getSharedPreferences("datos",0);
        String token = sp.getString("token","-1");
        Call<Propietario> prop = ApiClient.getMyApiClient().actualizarPropietario(token,p);
        prop.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if(response.isSuccessful()){
                    propietario.postValue(response.body());
                    Toast.makeText(context, "Se Modifico con exito", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "No se pudo modificar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {

                Toast.makeText(context, "Ocurrio un error inesperado"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}