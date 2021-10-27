package com.sosa.trabajofinalsosagaston.ui.contrato;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sosa.trabajofinalsosagaston.MainActivityViewModel;
import com.sosa.trabajofinalsosagaston.R;
import com.sosa.trabajofinalsosagaston.databinding.ContratoDetalleFragmentBinding;
import com.sosa.trabajofinalsosagaston.databinding.InquilinoDetalleFragmentBinding;
import com.sosa.trabajofinalsosagaston.modelo.Contrato;
import com.sosa.trabajofinalsosagaston.ui.login.LoginViewModel;

public class ContratoDetalleFragment extends Fragment {

    private ContratoDetalleViewModel mViewModel;
    private ContratoDetalleFragmentBinding binding;

    public static ContratoDetalleFragment newInstance() {
        return new ContratoDetalleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = ContratoDetalleFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mViewModel = new ViewModelProvider(this).get(ContratoDetalleViewModel.class);
        mViewModel.getContrato().observe(getViewLifecycleOwner(), new Observer<Contrato>() {
            @Override
            public void onChanged(Contrato contrato) {
                binding.TVConCodigo.setText("Codigo contrato\n"+contrato.getIdContrato());
                binding.TVConFechaIni.setText("Fecha de inicio\n"+contrato.getFechaInicio());
                binding.TVConFechaFin.setText("Fecha finalizacion\n"+contrato.getFechaFin());
                binding.TVConMonto.setText("Monto del alquiler\n$"+contrato.getMontoAlquiler());
                binding.TvConInqNom.setText("Inquilino\n"+contrato.getInquilino().getNombre());
                binding.TVConDirec.setText("Inmueble\nDirrecion: "+contrato.getInmueble().getDireccion());
                binding.BTPagos.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("contrato",contrato);
                        Navigation.findNavController(root).navigate(R.id.pagoFragment,bundle);
                    }
                });
            }
        });
        mViewModel.setContrato(getArguments());
        return root;
    }



}