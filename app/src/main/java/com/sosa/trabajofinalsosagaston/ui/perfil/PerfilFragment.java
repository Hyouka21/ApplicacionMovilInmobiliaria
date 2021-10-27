package com.sosa.trabajofinalsosagaston.ui.perfil;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sosa.trabajofinalsosagaston.MainActivityViewModel;
import com.sosa.trabajofinalsosagaston.databinding.PerfilFragmentBinding;
import com.sosa.trabajofinalsosagaston.modelo.Propietario;

public class PerfilFragment extends Fragment {

    private PerfilViewModel mViewModel;
    private PerfilFragmentBinding binding;
    private Propietario p ;
    public static PerfilFragment newInstance() {
        return new PerfilFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);
        binding = PerfilFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mViewModel.iniciar();

        //para referirme al view model que ya esta creado en la activity requireActivity()
       MainActivityViewModel  mView =new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
       // MainActivityViewModel  mView = new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication()).create(MainActivityViewModel.class);

        mViewModel.getPropietario().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario propietario) {
                mView.actualizarPerfil();
                p=propietario;
                binding.ETNombreP.setEnabled(false);
                binding.ETApellidoP.setEnabled(false);
                binding.ETDniP.setEnabled(false);
                binding.ETEmailP.setEnabled(false);
                binding.ETTelefonoP.setEnabled(false);
                binding.ETClaveP.setEnabled(false);
                binding.ImgP.setImageResource(propietario.getAvatar());
                binding.ETNombreP.setText(propietario.getNombre());
                binding.ETApellidoP.setText(propietario.getApellido());
                binding.ETDniP.setText(propietario.getDni().toString());
                binding.ETEmailP.setText(propietario.getEmail());
                binding.ETTelefonoP.setText(propietario.getTelefono());
                binding.ETClaveP.setText("");
                binding.BTEditar.setVisibility(View.VISIBLE);
                binding.BTGuardar.setVisibility(View.INVISIBLE);
            }
        });
        binding.BTEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.editar();
            }
        });
        mViewModel.getEditar().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                binding.BTEditar.setVisibility(View.INVISIBLE);
                binding.BTGuardar.setVisibility(View.VISIBLE);
                binding.ETNombreP.setEnabled(true);
                binding.ETApellidoP.setEnabled(true);
                binding.ETDniP.setEnabled(true);
                binding.ETEmailP.setEnabled(true);
                binding.ETTelefonoP.setEnabled(true);
                binding.ETClaveP.setEnabled(true);
            }
        });
        binding.BTGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.guardar();
            }
        });
        mViewModel.getGuardar().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                p.setDni(binding.ETDniP.getText().toString());
                p.setNombre(binding.ETNombreP.getText().toString());
                p.setEmail(binding.ETEmailP.getText().toString());
                p.setClave(binding.ETClaveP.getText().toString());
                p.setTelefono(binding.ETTelefonoP.getText().toString());
                p.setApellido(binding.ETApellidoP.getText().toString());
                mViewModel.cambiar(p);
                mViewModel.iniciar();


            }
        });
        return root;

    }




}