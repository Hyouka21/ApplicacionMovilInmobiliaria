package com.sosa.trabajofinalsosagaston.ui.inmueble;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sosa.trabajofinalsosagaston.modelo.Inmueble;
import com.sosa.trabajofinalsosagaston.request.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmuebleDetalleViewModel extends AndroidViewModel {
    private MutableLiveData<Inmueble> inmueble ;
    private ApiClient api;
    private Inmueble i;
    private Context context;

    public InmuebleDetalleViewModel(@NonNull Application application) {
        super(application);
        inmueble = new MutableLiveData<>();
        context = application.getApplicationContext();
    }


    public MutableLiveData<Inmueble> getInmueble() {
        return inmueble;
    }
    public void setInmueble(Bundle bundle){
        i = (Inmueble) bundle.getSerializable("inmueble");
        inmueble.setValue(i);
    }
    public void guardarCambios(boolean cheak){

        i.setEstado(cheak?1:0);
        SharedPreferences sp = context.getSharedPreferences("datos",0);
        String token = sp.getString("token","-1");
        Call<Inmueble> inm = ApiClient.getMyApiClient().actualizarInmueble(token, i.getIdInmueble(), i.isEstado());
        inm.enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                if(response.isSuccessful()){
                    Toast.makeText(context, "Se actualizo con exito", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Inmueble> call, Throwable t) {
                Toast.makeText(context, "Ocurrio un error inesperado"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}