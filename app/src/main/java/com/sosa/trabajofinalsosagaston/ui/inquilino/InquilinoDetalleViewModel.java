package com.sosa.trabajofinalsosagaston.ui.inquilino;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sosa.trabajofinalsosagaston.modelo.Inmueble;
import com.sosa.trabajofinalsosagaston.modelo.Inquilino;
import com.sosa.trabajofinalsosagaston.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InquilinoDetalleViewModel extends AndroidViewModel {
    private MutableLiveData<Inquilino> inquilino ;
    private Inmueble i;

    private Context context;

    public InquilinoDetalleViewModel(@NonNull Application application) {
        super(application);
        inquilino = new MutableLiveData<>();

        context = application.getApplicationContext();
    }


    public MutableLiveData<Inquilino> getInquilino() {
        return inquilino;
    }

    public void setInquilino(Bundle bundle) {
        i = (Inmueble) bundle.getSerializable("inmueble");
        SharedPreferences sp = context.getSharedPreferences("datos",0);
        String token = sp.getString("token","-1");
        Call<Inquilino> inq =ApiClient.getMyApiClient().obtenerInquilino(token,i.getIdInmueble());
        inq.enqueue(new Callback<Inquilino>() {
            @Override
            public void onResponse(Call<Inquilino> call, Response<Inquilino> response) {
                if(response.isSuccessful()){
                    inquilino.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Inquilino> call, Throwable t) {

            }
        });

    }
}