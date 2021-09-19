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

import com.sosa.trabajofinalsosagaston.R;
import com.sosa.trabajofinalsosagaston.databinding.InmuebleDetalleFragmentBinding;
import com.sosa.trabajofinalsosagaston.modelo.Inmueble;

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
        mViewModel = new ViewModelProvider(this).get(InmuebleDetalleViewModel.class);
        Inmueble i = (Inmueble) getArguments().getSerializable("inmueble");

        mViewModel.setInmueble(i);
        mViewModel.getInmueble().observe(getViewLifecycleOwner(), new Observer<Inmueble>() {
            @Override
            public void onChanged(Inmueble inmueble) {

                binding.TVCodigoDI.setText("Codigo:\n"+inmueble.getIdInmueble());
                binding.CBEstadoDI.setChecked(inmueble.isEstado());
                binding.TVAmbienteDI.setText("Ambientes:\n"+inmueble.getAmbientes());
                binding.TVDireccionDI.setText("Dirección:\n"+inmueble.getDireccion());
                binding.TVPrecioDI.setText("Precio:\n$"+inmueble.getPrecio());
                binding.TVTipoDI.setText("Tipo:\n"+inmueble.getTipo());
                binding.TVUsoDI.setText("Uso:\n"+inmueble.getUso());

            }
        });


        return root;
    }



}