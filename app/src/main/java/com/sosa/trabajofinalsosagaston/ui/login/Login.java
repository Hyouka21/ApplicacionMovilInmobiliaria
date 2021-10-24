package com.sosa.trabajofinalsosagaston.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sosa.trabajofinalsosagaston.MainActivity;
import com.sosa.trabajofinalsosagaston.MainActivityViewModel;
import com.sosa.trabajofinalsosagaston.R;

import com.sosa.trabajofinalsosagaston.modelo.Propietario;
import com.sosa.trabajofinalsosagaston.modelo.Token;

import java.util.List;


public class Login extends AppCompatActivity {
    private LoginViewModel loginViewModel;
    private EditText emailE;
    private EditText claveE;
    private Button iniciarB;
    private TextView mensaje;
    private SensorManager sensorManager;
    private LeeSensor leeSensor;
    private List<Sensor> listaSensores;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginViewModel.class);
        inicializar();
        loginViewModel.inicioAutomatico();
        loginViewModel.getTokenMD().observe(this, new Observer<Token>() {
            @Override
            public void onChanged(Token token) {
                loginViewModel.token(token);
            }
        });
        iniciarB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginViewModel.iniciar(emailE.getText().toString(),claveE.getText().toString());
            }
        });
        loginViewModel.getMensaje().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                mensaje.setVisibility(View.VISIBLE);
            }
        });
        loginViewModel.getPropietario().observe(this, new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario p) {
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
            }
        });
        leeSensor = new LeeSensor();
        //Obtener la lista de sensores disponibles
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        listaSensores = sensorManager.getSensorList(Sensor.TYPE_GYROSCOPE);
        if(listaSensores.size()>0) {
            sensorManager.registerListener(leeSensor, listaSensores.get(0), SensorManager.SENSOR_DELAY_GAME);
        }
        loginViewModel.getLlamar().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {


                Intent llamada = new Intent(Intent.ACTION_CALL);
                llamada.setData(Uri.parse("tel://911"));
                startActivity(llamada);

            }
        });
        loginViewModel.getCordenada().observe(this, new Observer<LoginViewModel.Coordenada>() {
            @Override
            public void onChanged(LoginViewModel.Coordenada coordenada) {
                loginViewModel.controlador(coordenada);
            }
        });


    }
    public void inicializar(){

        emailE = findViewById(R.id.ETEmail);
        claveE = findViewById(R.id.ETClave);
        iniciarB =findViewById(R.id.BTIniciar);
        mensaje = findViewById(R.id.TVMensaje);
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M
                && checkSelfPermission(android.Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE},1000);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(leeSensor, listaSensores.get(0), SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(leeSensor);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    private class LeeSensor implements SensorEventListener {

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        loginViewModel.cordenadas(sensorEvent.values[0],sensorEvent.values[1]);


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
}