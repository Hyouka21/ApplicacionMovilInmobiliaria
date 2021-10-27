package com.sosa.trabajofinalsosagaston.ui.inquilino;

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
import com.sosa.trabajofinalsosagaston.modelo.Inmueble;
import com.sosa.trabajofinalsosagaston.ui.contrato.ContratoViewModel;
import com.sosa.trabajofinalsosagaston.ui.login.LoginViewModel;

import java.util.ArrayList;
import java.util.List;

public class InquilinoFragment extends Fragment {
    private RecyclerView RVInquilino;
    private InquilinoAdapter inquilinoAdapter;
    private InquilinoViewModel mViewModel;

    public static InquilinoFragment newInstance() {
        return new InquilinoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.inquilino_fragment, container, false);
        RVInquilino = (RecyclerView) root.findViewById(R.id.RVInquilino);
        mViewModel = new ViewModelProvider(this).get(InquilinoViewModel.class);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        mViewModel.getVisibilidad().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                root.findViewById(R.id.TVIFEs).setVisibility(integer);
            }
        });
        mViewModel.getLista().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> inmuebles) {
                RVInquilino.setLayoutManager(linearLayoutManager);
                inquilinoAdapter = new InquilinoAdapter(inmuebles,root,getLayoutInflater());

                RVInquilino.setAdapter(inquilinoAdapter);
            }
        });
        mViewModel.setInmueblesAlquilados();
        return root;
    }



}