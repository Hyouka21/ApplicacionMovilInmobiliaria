package com.sosa.trabajofinalsosagaston.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.sosa.trabajofinalsosagaston.R;
import com.sosa.trabajofinalsosagaston.modelo.Inmueble;
import com.sosa.trabajofinalsosagaston.ui.inmueble.InmuebleDetalleFragment;

import java.util.ArrayList;
import java.util.List;

public class InmuebleAdapter extends RecyclerView.Adapter<InmuebleAdapter.MiViewHolder> {
    private List<Inmueble> lista;
    private View root ;
    private LayoutInflater inflater;

    public InmuebleAdapter(List<Inmueble> lista, View root, LayoutInflater inflater) {
        this.lista = lista;
        this.root = root;
        this.inflater = inflater;
    }


    @NonNull
    @Override
    public MiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_inmueble, parent, false);
        return new MiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MiViewHolder holder, int position) {

        Inmueble i = lista.get(position);
        holder.TVDetalle.setText((i.getDireccion() + "\n $" + i.getPrecio()));
        Glide.with(root.getContext())//contexto
                .load(i.getImagen())//url de la imagen
                .diskCacheStrategy(DiskCacheStrategy.ALL)// guarda en el cache
                .into(holder.imagen); // se encarga de setear la imagen

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putSerializable("inmueble",i);
                Navigation.findNavController(root).navigate(R.id.inmuebleDetalleFragment,bundle);

            }
        });

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class MiViewHolder extends RecyclerView.ViewHolder {
        private ImageView imagen;
        private TextView TVDetalle;

        public MiViewHolder(@NonNull View itemView) {
            super(itemView);
            imagen = itemView.findViewById(R.id.IVCVInmueble);
            TVDetalle = itemView.findViewById(R.id.TVCVDetalles);

        }
    }
}
