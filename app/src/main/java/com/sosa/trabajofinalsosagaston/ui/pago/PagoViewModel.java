package com.sosa.trabajofinalsosagaston.ui.pago;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.icu.lang.UProperty;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sosa.trabajofinalsosagaston.modelo.Contrato;
import com.sosa.trabajofinalsosagaston.modelo.Inmueble;
import com.sosa.trabajofinalsosagaston.modelo.Pago;
import com.sosa.trabajofinalsosagaston.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PagoViewModel extends AndroidViewModel {
    private MutableLiveData<List<Pago>> lista ;
    private MutableLiveData<Integer> visibilidad;
    private Context context;

    public PagoViewModel(@NonNull Application application) {
        super(application);
        lista = new MutableLiveData<>();
        visibilidad = new MutableLiveData<>();

        context = application.getApplicationContext();
    }


    public MutableLiveData<Integer> getVisibilidad() {
        return visibilidad;
    }

    public MutableLiveData<List<Pago>> getLista() {
        return lista;
    }

    public void setPagos(Bundle bundle){
        Contrato c = (Contrato) bundle.getSerializable("contrato");
        SharedPreferences sp = context.getSharedPreferences("datos",0);
        String token = sp.getString("token","-1");
        Call<List<Pago>> pag = ApiClient.getMyApiClient().obtenerPagos(token,c.getIdContrato());
        pag.enqueue(new Callback<List<Pago>>() {
            @Override
            public void onResponse(Call<List<Pago>> call, Response<List<Pago>> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null){
                        visibilidad.setValue(View.INVISIBLE);
                        lista.setValue(response.body());
                    }else{
                        visibilidad.setValue(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Pago>> call, Throwable t) {
                Toast.makeText(context, "Ocurrio un error inesperado"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }
}