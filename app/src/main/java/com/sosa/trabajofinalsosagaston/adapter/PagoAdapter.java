package com.sosa.trabajofinalsosagaston.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;
import com.sosa.trabajofinalsosagaston.R;
import com.sosa.trabajofinalsosagaston.modelo.Pago;

import java.util.ArrayList;
import java.util.List;

public class PagoAdapter extends RecyclerView.Adapter<PagoAdapter.MiViewHolder>{
    private List<Pago> lista;
    private View root ;
    private LayoutInflater inflater;

    public PagoAdapter(List<Pago> lista, View root, LayoutInflater inflater) {
        this.lista = lista;
        this.root = root;
        this.inflater = inflater;
    }


    @NonNull
    @Override
    public PagoAdapter.MiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_pago, parent, false);
        return new PagoAdapter.MiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PagoAdapter.MiViewHolder holder, int position) {

        Pago p = lista.get(position);
        holder.TVCod.setText("Código de pago: "+p.getIdPago());
        holder.TVCodC.setText("Código de contrato: "+p.getContrato().getIdContrato());
        holder.TVFechaP.setText("Fecha de pago: "+p.getFechaDePago());
        holder.TVImporte.setText("Importe: $"+p.getImporte());
        holder.TVNumP.setText("Numero de pago: "+p.getNumero());
    };


    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class MiViewHolder extends RecyclerView.ViewHolder {

        private TextView TVCod,TVNumP,TVCodC,TVImporte,TVFechaP;

        public MiViewHolder(@NonNull View itemView) {
            super(itemView);
            TVCod=itemView.findViewById(R.id.TVCodP);
            TVNumP=itemView.findViewById(R.id.TVNumP);
            TVCodC=itemView.findViewById(R.id.TVCodCP);
            TVImporte=itemView.findViewById(R.id.TVImpP);
            TVFechaP=itemView.findViewById(R.id.TVFechP);

        }
    }
}

