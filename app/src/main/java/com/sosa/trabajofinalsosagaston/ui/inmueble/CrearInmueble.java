package com.sosa.trabajofinalsosagaston.ui.inmueble;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.sosa.trabajofinalsosagaston.R;
import com.sosa.trabajofinalsosagaston.databinding.CrearInmuebleFragmentBinding;
import com.sosa.trabajofinalsosagaston.databinding.InmuebleDetalleFragmentBinding;
import com.sosa.trabajofinalsosagaston.modelo.Inmueble;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

public class CrearInmueble extends Fragment {
    private ImageView imagen1;
    private Inmueble inmueble;
    private static int REQUEST_IMAGE_CAPTURE=1;
    private CrearInmuebleViewModel mViewModel;
    private CrearInmuebleFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = CrearInmuebleFragmentBinding.inflate(inflater, container, false);
        View root= binding.getRoot();
        mViewModel = new ViewModelProvider(this).get(CrearInmuebleViewModel.class);
        inmueble=new Inmueble();
        configView();
        mViewModel.getInmueble().observe(getViewLifecycleOwner(), new Observer<Inmueble>() {
            @Override
            public void onChanged(Inmueble inmueble2) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("inmueble",inmueble2);
                Navigation.findNavController(root).navigate(R.id.inmuebleDetalleFragment,bundle);
                //limpiar();

            }
        });
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tomarFoto(v);
            }
        });
        binding.BTCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inmueble.setId(0);
                inmueble.setAmbientes(Integer.parseInt(binding.ETAmbiente.getText().toString()));
                inmueble.setEstado(0);
                inmueble.setIdPropietario(0);
                inmueble.setDireccion(binding.ETDirrecionC.getText().toString());
                inmueble.setLatitud(Integer.parseInt(binding.ETLatitud.getText().toString()));
                inmueble.setLongitud(Integer.parseInt(binding.ETLongitud.getText().toString()));
                inmueble.setSuperficie(Integer.parseInt(binding.ETSuperf.getText().toString()));
                inmueble.setPrecio(Double.parseDouble(binding.ETPrecio.getText().toString()));
                mViewModel.crearInmueble(inmueble);

            }
        });


        return root;
    }
    public void configView(){
        //imagen1=binding.imageView;



        mViewModel.getFoto().observe(getViewLifecycleOwner(), new Observer<byte []>() {
            @Override
            public void onChanged(byte [] bitmap) {
               // imagen1.setImageBitmap(bitmap);

                    String encoded = Base64.getEncoder().encodeToString(bitmap);

                    inmueble.setImagenGuardar(encoded);

                    binding.TVFotoE.setText("Imagen Guardada");
            }
        });
    }


    public void tomarFoto(View v){
//startActivityForResult es otra forma de iniciar una activity, pero esperando desde donde la llamé un resultado
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    //Este método es llamado automáticamente cuando retorna de la cámara.
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mViewModel.respuestaDeCamara(requestCode,resultCode,data,REQUEST_IMAGE_CAPTURE);
    }
    public void limpiar(){
        binding.TVFotoE.setText("Sin foto guardada");
        binding.ETAmbiente.setText("");
        binding.ETDirrecionC.setText("");
        binding.ETLatitud.setText("");
        binding.ETLongitud.setText("");
        binding.ETPrecio.setText("");
        binding.ETSuperf.setText("");
        mViewModel.limpiar();

    }


}