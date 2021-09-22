package com.sosa.trabajofinalsosagaston.ui.inquilino;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sosa.trabajofinalsosagaston.R;
import com.sosa.trabajofinalsosagaston.databinding.InmuebleDetalleFragmentBinding;
import com.sosa.trabajofinalsosagaston.databinding.InquilinoDetalleFragmentBinding;
import com.sosa.trabajofinalsosagaston.modelo.Inquilino;

public class InquilinoDetalleFragment extends Fragment {

    private InquilinoDetalleViewModel mViewModel;
    private InquilinoDetalleFragmentBinding binding;

    public static InquilinoDetalleFragment newInstance() {
        return new InquilinoDetalleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = InquilinoDetalleFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mViewModel = new ViewModelProvider(this).get(InquilinoDetalleViewModel.class);
        mViewModel.getInquilino().observe(getViewLifecycleOwner(), new Observer<Inquilino>() {
            @Override
            public void onChanged(Inquilino inquilino) {
                binding.TVInqNombre.setText("Nombre\n"+inquilino.getNombre());
                binding.TVInqApellido.setText("Apellido\n" + inquilino.getApellido());
                binding.TVInqCodigo.setText("Codigo\n"+ inquilino.getIdInquilino());
                binding.TVInqDni.setText("Dni\n"+inquilino.getDNI());
                binding.TVInqEmail.setText("Email\n"+inquilino.getEmail());
                binding.TVInqGarante.setText("Garante\n"+inquilino.getNombreGarante());
                binding.TvInqGaraTelefono.setText("Teléfono Garante\n"+inquilino.getTelefonoGarante());
                binding.TVInqTelefono.setText("Telefono\n"+inquilino.getTelefonoGarante());
            }
        });
        mViewModel.setInquilino(getArguments());
        return root;
    }



}