package com.sosa.trabajofinalsosagaston.ui.contrato;

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
import com.sosa.trabajofinalsosagaston.adapter.ContratoAdapter;
import com.sosa.trabajofinalsosagaston.modelo.Inmueble;

import java.util.ArrayList;

public class ContratoFragment extends Fragment {
    private RecyclerView RVContrato;
    private ContratoAdapter contratoAdapter;
    private ContratoViewModel mViewModel;

    public static ContratoFragment newInstance() {
        return new ContratoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.contrato_fragment, container, false);
        RVContrato = (RecyclerView) root.findViewById(R.id.RVContrato);
        mViewModel = new ViewModelProvider(this).get(ContratoViewModel.class);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        mViewModel.getVisibilidad().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                root.findViewById(R.id.TVConEstado).setVisibility(integer);
            }
        });
        mViewModel.getLista().observe(getViewLifecycleOwner(), new Observer<ArrayList<Inmueble>>() {
            @Override
            public void onChanged(ArrayList<Inmueble> inmuebles) {
                RVContrato.setLayoutManager(linearLayoutManager);
                contratoAdapter = new ContratoAdapter(inmuebles,root,getLayoutInflater());

                RVContrato.setAdapter(contratoAdapter);
            }
        });
        mViewModel.setInmueblesAlquilados();
        return root;
    }



}