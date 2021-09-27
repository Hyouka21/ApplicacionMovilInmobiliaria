package com.sosa.trabajofinalsosagaston;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.sosa.trabajofinalsosagaston.modelo.Propietario;
import com.sosa.trabajofinalsosagaston.ui.login.Login;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<Propietario> propietario ;
    public MainActivityViewModel(){
        propietario =  new MutableLiveData<>();
    }
    public MutableLiveData<Propietario> getPropietario() {
        if(propietario == null){
            propietario =  new MutableLiveData<>();
        }
        return propietario;
    }
    public void actualizarPerfil(Propietario p){
        propietario.setValue(p);
    }


    }


