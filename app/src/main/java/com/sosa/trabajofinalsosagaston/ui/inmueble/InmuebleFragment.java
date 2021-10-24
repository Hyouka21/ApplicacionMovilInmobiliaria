package com.sosa.trabajofinalsosagaston.ui.inmueble;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sosa.trabajofinalsosagaston.R;
import com.sosa.trabajofinalsosagaston.adapter.InmuebleAdapter;
import com.sosa.trabajofinalsosagaston.modelo.Inmueble;
import com.sosa.trabajofinalsosagaston.request.ApiClient;
import com.sosa.trabajofinalsosagaston.ui.login.LoginViewModel;

import java.util.ArrayList;
import java.util.List;

public class InmuebleFragment extends Fragment {
    private RecyclerView RVInmueble;
    private InmuebleAdapter inmuebleAdapter;

    private InmuebleViewModel mViewModel;

    public static InmuebleFragment newInstance() {
        return new InmuebleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.inmueble_fragment, container, false);
        mViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(InmuebleViewModel.class);
        RVInmueble = (RecyclerView) rootView.findViewById(R.id.RVInmueble);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        mViewModel.getInmuebles().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> inmuebles) {

                RVInmueble.setLayoutManager(linearLayoutManager);
                inmuebleAdapter = new InmuebleAdapter(inmuebles,rootView,getLayoutInflater());

                RVInmueble.setAdapter(inmuebleAdapter);
            }
        });
        mViewModel.setInmuebles();
        return rootView;
    }



}