package com.sosa.trabajofinalsosagaston.ui.pago;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sosa.trabajofinalsosagaston.R;
import com.sosa.trabajofinalsosagaston.adapter.InquilinoAdapter;
import com.sosa.trabajofinalsosagaston.adapter.PagoAdapter;
import com.sosa.trabajofinalsosagaston.modelo.Inmueble;
import com.sosa.trabajofinalsosagaston.modelo.Pago;

import java.util.ArrayList;
import java.util.List;

public class PagoFragment extends Fragment {

    private PagoViewModel mViewModel;
    private RecyclerView RVPago;
    private PagoAdapter pagoAdapter;
    public static PagoFragment newInstance() {
        return new PagoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.pago_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(PagoViewModel.class);
        RVPago = (RecyclerView) root.findViewById(R.id.RVPago);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        mViewModel.getVisibilidad().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                root.findViewById(R.id.TVEstadoPago).setVisibility(integer);
            }
        });
        mViewModel.getLista().observe(getViewLifecycleOwner(), new Observer<List<Pago>>() {
            @Override
            public void onChanged(List<Pago> pagos) {
                RVPago.setLayoutManager(linearLayoutManager);
                pagoAdapter = new PagoAdapter(pagos,root,getLayoutInflater());

                RVPago.setAdapter(pagoAdapter);
            }
        });
        mViewModel.setPagos(getArguments());
        return root;
    }



}