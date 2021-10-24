package com.sosa.trabajofinalsosagaston.ui.contrato;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sosa.trabajofinalsosagaston.modelo.Inmueble;
import com.sosa.trabajofinalsosagaston.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContratoViewModel extends AndroidViewModel {
    private MutableLiveData<List<Inmueble>> lista ;
    private MutableLiveData<Integer> visibilidad;

    private Context context;

    public ContratoViewModel(@NonNull Application application) {
        super(application);
        lista = new MutableLiveData<>();
        visibilidad = new MutableLiveData<>();

        context = application.getApplicationContext();
    }

    public MutableLiveData<Integer> getVisibilidad() {
        return visibilidad;
    }

    public MutableLiveData<List<Inmueble>> getLista() {
        return lista;
    }

    public void setInmueblesAlquilados(){
        SharedPreferences sp = context.getSharedPreferences("datos",0);
        String token = sp.getString("token","-1");

        Call<List<Inmueble>> inm = ApiClient.getMyApiClient().obtenerInmueblesAlquilados(token);
        inm.enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                if(response.isSuccessful()){
                    if(response.body().size()>0) {
                        visibilidad.setValue(View.INVISIBLE);
                        lista.postValue(response.body());
                    }else{
                        visibilidad.setValue(View.VISIBLE);
                    }
                }else{
                    visibilidad.setValue(View.VISIBLE);
                }

            }

            @Override
            public void onFailure(Call<List<Inmueble>> call, Throwable t) {
                Log.d("excepcion" ,t.getMessage());
            }
        });
    }
}