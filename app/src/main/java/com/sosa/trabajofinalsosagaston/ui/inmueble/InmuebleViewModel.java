package com.sosa.trabajofinalsosagaston.ui.inmueble;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

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

public class InmuebleViewModel extends AndroidViewModel {
    private MutableLiveData<List<Inmueble>> inmuebles ;
    private Context context;

    public InmuebleViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }


    public MutableLiveData<List<Inmueble>> getInmuebles() {
         if(inmuebles==null){
             inmuebles = new MutableLiveData<>();
         }
        return inmuebles;
    }
    public void setInmuebles(){
        SharedPreferences sp = context.getSharedPreferences("datos",0);
        String token = sp.getString("token","-1");
        Call<List<Inmueble>> inm = ApiClient.getMyApiClient().obtenerInmuebles(token);
        inm.enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
            if(response.isSuccessful()){
                inmuebles.postValue(response.body());
            }

            }

            @Override
            public void onFailure(Call<List<Inmueble>> call, Throwable t) {
                Log.d("excepcion" ,t.getMessage());
            }
        });


    }
}