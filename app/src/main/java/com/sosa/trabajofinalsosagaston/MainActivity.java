package com.sosa.trabajofinalsosagaston;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.sosa.trabajofinalsosagaston.databinding.ActivityMainBinding;
import com.sosa.trabajofinalsosagaston.modelo.Propietario;
import com.sosa.trabajofinalsosagaston.request.ApiClient;
import com.sosa.trabajofinalsosagaston.ui.login.LoginViewModel;
import com.sosa.trabajofinalsosagaston.ui.perfil.PerfilViewModel;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private MainActivityViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        mViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        iniciarHeader();
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_perfil,R.id.nav_inmueble,R.id.nav_inquilino,R.id.nav_contrato)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }



    private void iniciarHeader(){
        NavigationView navigationView = binding.navView;
        View header = navigationView.getHeaderView(0);
        ApiClient api = ApiClient.getApi();
        Propietario p = api.obtenerUsuarioActual();
        mViewModel.actualizarPerfil(p);
        mViewModel.getPropietario().observe(this, new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario propietario) {
                ImageView avatar = header.findViewById(R.id.ImgAvatar);
                TextView nombre = header.findViewById(R.id.TVNombre);
                TextView correo = header.findViewById(R.id.TVCorreo);
                avatar.setImageResource(propietario.getAvatar());
                nombre.setText(propietario.getNombre()+" "+propietario.getApellido());
                correo.setText(propietario.getEmail());

            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //finishAffinity();//esto cierra la aplicacion completa

    }
}