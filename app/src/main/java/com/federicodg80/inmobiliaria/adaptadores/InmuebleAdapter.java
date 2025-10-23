package com.federicodg80.inmobiliaria.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.federicodg80.inmobiliaria.BuildConfig;
import com.federicodg80.inmobiliaria.R;
import com.federicodg80.inmobiliaria.modelos.Inmueble;

import java.util.List;

public class InmuebleAdapter extends RecyclerView.Adapter<InmuebleAdapter.ViewHolder> {
    public List<Inmueble> listaInmuebles;
    public Context context;
    public LayoutInflater inflater;
    private OnItemClickListener listener;
    private static final String BASE_URL = BuildConfig.API_BASE_URL;

    public InmuebleAdapter(List<Inmueble> listaInmuebles, Context context, LayoutInflater inflater, OnItemClickListener listener) {
        this.listaInmuebles = listaInmuebles;
        this.context = context;
        this.inflater = inflater;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_inmueble, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Inmueble inmueble = listaInmuebles.get(position);
        holder.tvDireccion.setText(String.valueOf(inmueble.getDireccion()));
        holder.tvPrecio.setText(String.valueOf("$" + inmueble.getPrecio()));
        String imagenUrl = inmueble.getImagen();
        if (imagenUrl != null && !imagenUrl.isEmpty()) {
            Glide.with(context)
                .load(BASE_URL + imagenUrl)
                .placeholder(R.drawable.placeholder_inmueble)
                .error(R.drawable.placeholder_inmueble)
                .into(holder.ivInmueble);
        } else {
            holder.ivInmueble.setImageResource(R.drawable.placeholder_inmueble);
        }
        holder.cardInmueble.setOnClickListener(v -> {
            if (listener != null) listener.onItemClick(inmueble);
        });
    }

    @Override
    public int getItemCount() {
        return listaInmuebles.size();
    }

    public static class ViewHolder  extends RecyclerView.ViewHolder {
        ImageView ivInmueble;
        TextView tvDireccion;
        TextView tvPrecio;
        View cardInmueble;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDireccion = itemView.findViewById(R.id.tvDireccion);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
            ivInmueble = itemView.findViewById(R.id.ivInmueble);
            cardInmueble = itemView.findViewById(R.id.cardInmueble);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Inmueble inmueble);
    }
}
