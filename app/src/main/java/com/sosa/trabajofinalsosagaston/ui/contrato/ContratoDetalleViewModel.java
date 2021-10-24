package com.sosa.trabajofinalsosagaston.ui.contrato;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sosa.trabajofinalsosagaston.modelo.Contrato;
import com.sosa.trabajofinalsosagaston.modelo.Inmueble;
import com.sosa.trabajofinalsosagaston.modelo.Inquilino;
import com.sosa.trabajofinalsosagaston.request.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContratoDetalleViewModel extends AndroidViewModel {
    private MutableLiveData<Contrato> contrato ;
    private Inmueble i;

    private Context context;

    public ContratoDetalleViewModel(@NonNull Application application) {
        super(application);
        contrato = new MutableLiveData<>();

        context = application.getApplicationContext();
    }


    public MutableLiveData<Contrato> getContrato() {
        return contrato;
    }

    public void setContrato(Bundle bundle) {
        i = (Inmueble) bundle.getSerializable("inmueble");
        SharedPreferences sp = context.getSharedPreferences("datos",0);
        String token = sp.getString("token","-1");
        Call<Contrato> con =ApiClient.getMyApiClient().obtenerContratos(token,i.getIdInmueble());
        con.enqueue(new Callback<Contrato>() {
            @Override
            public void onResponse(Call<Contrato> call, Response<Contrato> response) {
                if(response.isSuccessful()){
                    contrato.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Contrato> call, Throwable t) {

            }


        });

    }
}