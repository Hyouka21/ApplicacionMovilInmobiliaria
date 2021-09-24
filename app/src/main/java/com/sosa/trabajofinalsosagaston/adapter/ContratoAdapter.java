package com.sosa.trabajofinalsosagaston.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.sosa.trabajofinalsosagaston.R;
import com.sosa.trabajofinalsosagaston.modelo.Inmueble;

import java.util.ArrayList;

public class ContratoAdapter extends RecyclerView.Adapter<ContratoAdapter.MiViewHolder>{
    private ArrayList<Inmueble> lista;
    private View root ;
    private LayoutInflater inflater;

    public ContratoAdapter(ArrayList<Inmueble> lista, View root, LayoutInflater inflater) {
        this.lista = lista;
        this.root = root;
        this.inflater = inflater;
    }


    @NonNull
    @Override
    public ContratoAdapter.MiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_inquilino_inmueble, parent, false);
        return new ContratoAdapter.MiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContratoAdapter.MiViewHolder holder, int position) {

        Inmueble i = lista.get(position);
        holder.TVDetalle.setText(i.getDireccion());
        Glide.with(root.getContext())//contexto
                .load(i.getImagen())//url de la imagen
                .diskCacheStrategy(DiskCacheStrategy.ALL)// guarda en el cache
                .into(holder.imagen); // se encarga de setear la imagen

        holder.BTVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putSerializable("inmueble",i);
                Navigation.findNavController(root).navigate(R.id.contratoDetalleFragment,bundle);

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
        private Button BTVer;
        public MiViewHolder(@NonNull View itemView) {
            super(itemView);
            BTVer = itemView.findViewById(R.id.BTCVInqVer);
            imagen = itemView.findViewById(R.id.IVCVInq);
            TVDetalle = itemView.findViewById(R.id.TVCVInqDetalle);

        }
    }
}
