package com.sosa.trabajofinalsosagaston;

import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.sosa.trabajofinalsosagaston.modelo.Propietario;
import com.sosa.trabajofinalsosagaston.request.ApiClient;
import com.sosa.trabajofinalsosagaston.ui.login.Login;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends AndroidViewModel {
    private MutableLiveData<Propietario> propietario ;
    private Context context;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public MutableLiveData<Propietario> getPropietario() {
        if(propietario == null){
            propietario =  new MutableLiveData<>();
        }
        return propietario;
    }
    public void actualizarPerfil(){
        SharedPreferences sp = context.getSharedPreferences("datos",0);
        String token = sp.getString("token","-1");
        Log.d("exce" ,token);
        Call<Propietario> prop = ApiClient.getMyApiClient().obtenerUsuario(token);
        prop.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if(response.isSuccessful()){
                    Log.d("exce3" ,response.code()+""+response.message());
                    propietario.postValue(response.body());

                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                Log.d("exce1" ,t.getMessage());
            }
        });
    }
    public void actualizarPerfil(Propietario p){

//      propietario.setValue(p);

    }


    }


