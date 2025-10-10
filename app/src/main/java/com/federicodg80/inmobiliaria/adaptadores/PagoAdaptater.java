package com.federicodg80.inmobiliaria.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.federicodg80.inmobiliaria.R;
import com.federicodg80.inmobiliaria.modelos.Pago;

import java.util.List;

public class PagoAdaptater extends RecyclerView.Adapter<PagoAdaptater.ViewHolder> {
    public List<Pago> listaPagos;
    public Context context;
    public LayoutInflater inflater;

    public PagoAdaptater(List<Pago> listaPagos, Context context, LayoutInflater inflater) {
        this.listaPagos = listaPagos;
        this.context = context;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public PagoAdaptater.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_pago, parent, false);

        return new PagoAdaptater.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PagoAdaptater.ViewHolder holder, int position) {
        Pago pago = listaPagos.get(position);
        holder.tvCodigo.setText(String.valueOf("Código de Pago: " + pago.getIdPago()));
        holder.tvNumero.setText(String.valueOf("Número de Pago:" + pago.getNroPago()));
        holder.tvFecha.setText(String.valueOf("Fecha de Pago: " + pago.getFechaFormatted()));
        holder.tvImporte.setText(String.valueOf("Importe: $" + pago.getImporte()));
    }

    @Override
    public int getItemCount() {
        return listaPagos.size();
    }

    public static class ViewHolder  extends RecyclerView.ViewHolder {
        TextView tvCodigo;
        TextView tvNumero;
        TextView tvFecha;
        TextView tvImporte;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCodigo = itemView.findViewById(R.id.tvCodigo);
            tvNumero = itemView.findViewById(R.id.tvNumero);
            tvFecha = itemView.findViewById(R.id.tvFecha);
            tvImporte = itemView.findViewById(R.id.tvImporte);
        }
    }
}
