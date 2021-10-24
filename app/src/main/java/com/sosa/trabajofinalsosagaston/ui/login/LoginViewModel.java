package com.sosa.trabajofinalsosagaston.ui.login;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sosa.trabajofinalsosagaston.modelo.Propietario;
import com.sosa.trabajofinalsosagaston.modelo.Token;
import com.sosa.trabajofinalsosagaston.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends AndroidViewModel {
    private MutableLiveData<Propietario> propietario ;
    private MutableLiveData<Token> tokenMD ;
    private MutableLiveData<Boolean> mensaje ;
    private MutableLiveData<Coordenada> cordenada;
    private MutableLiveData<Boolean> llamar ;
    private Context context;
    private boolean estado =false;
    private int contador=0;


    public LoginViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        propietario =  new MutableLiveData<>();
        mensaje =  new MutableLiveData<>();
        cordenada =  new MutableLiveData<>();
       llamar =  new MutableLiveData<>();
        tokenMD = new MutableLiveData<>();

    }


    public MutableLiveData<Token> getTokenMD() {
        return tokenMD;
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

    public void token(Token token){

        Call<Propietario> callProp = ApiClient.getMyApiClient().obtenerUsuario("Bearer "+token.getToken());
        callProp.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if (response.isSuccessful()) {

                    Propietario p = response.body();

                    if (p != null) {

                        propietario.postValue(p);

                    } else {

                    }
                }else{
                    mensaje.postValue(true);
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                Toast.makeText(context, "Ocurrio un error inesperado"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void iniciar(String email , String clave){
        Call<Token> callTok = ApiClient.getMyApiClient().login(email,clave);

        callTok.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if (response.isSuccessful()) {
                    SharedPreferences sp = context.getSharedPreferences("datos",0);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("token", "Bearer " + response.body().getToken());
                    editor.commit();
                    tokenMD.postValue(response.body());

                }
            }
                @Override
                public void onFailure(Call<Token> call, Throwable t) {
                    Toast.makeText(context, "Ocurrio un error inesperado"+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
    }

    public void inicioAutomatico() {
        SharedPreferences sp = context.getSharedPreferences("datos",0);
        String token = sp.getString("token","-1");
        if(token!=null) {
            Call<Propietario> callProp = ApiClient.getMyApiClient().obtenerUsuario(token);
            callProp.enqueue(new Callback<Propietario>() {
                @Override
                public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                    if (response.isSuccessful()) {

                        Propietario p = response.body();

                        if (p != null) {

                            propietario.postValue(p);

                        } else {

                        }
                    }else {

                    }
                }

                @Override
                public void onFailure(Call<Propietario> call, Throwable t) {
                    Toast.makeText(context, "Ocurrio un error inesperado"+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
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
