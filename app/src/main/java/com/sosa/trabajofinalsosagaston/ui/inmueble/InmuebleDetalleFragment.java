package com.sosa.trabajofinalsosagaston.ui.inmueble;


import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.sosa.trabajofinalsosagaston.R;
import com.sosa.trabajofinalsosagaston.databinding.InmuebleDetalleFragmentBinding;
import com.sosa.trabajofinalsosagaston.modelo.Inmueble;
import com.sosa.trabajofinalsosagaston.ui.login.LoginViewModel;

public class InmuebleDetalleFragment extends Fragment {
    private InmuebleDetalleFragmentBinding binding;
    private InmuebleDetalleViewModel mViewModel;

    public static InmuebleDetalleFragment newInstance() {
        return new InmuebleDetalleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = InmuebleDetalleFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(InmuebleDetalleViewModel.class);;



        mViewModel.getInmueble().observe(getViewLifecycleOwner(), new Observer<Inmueble>() {
            @Override
            public void onChanged(Inmueble inmueble) {

                binding.TVCodigoDI.setText("Codigo:\n"+inmueble.getIdInmueble());
                binding.CBEstadoDI.setChecked(inmueble.isEstado()==1?false:true);
                binding.TVAmbienteDI.setText("Ambientes:\n"+inmueble.getAmbientes());
                binding.TVDireccionDI.setText("Dirección:\n"+inmueble.getDireccion());
                binding.TVPrecioDI.setText("Precio:\n$"+inmueble.getPrecio());
                binding.TVTipoDI.setText("Tipo:\n"+inmueble.getTipo());
                Glide.with(getContext())//contexto
                        .load(inmueble.getImagen())//url de la imagen
                        .diskCacheStrategy(DiskCacheStrategy.ALL)// guarda en el cache
                        .into(binding.ImgVDI); // se encarga de setear la imagen
                binding.TVUsoDI.setText("Uso:\n"+inmueble.getUso());
                binding.CBEstadoDI.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mViewModel.guardarCambios(binding.CBEstadoDI.isChecked());
                    }
                });
            }
        });

        mViewModel.setInmueble(getArguments());
        return root;
    }



}