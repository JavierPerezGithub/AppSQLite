package com.example.a21650521.appsqlite.rvUtils;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a21650521.appsqlite.R;
import com.example.a21650521.appsqlite.model.Contacto;

import java.util.ArrayList;

/**
 * Created by 21650521 on 12/02/2018.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.VHContacto> {

    private ArrayList<Contacto> datos;

    public Adapter(ArrayList<Contacto> datos){
        this.datos = datos;
    }
    public static class VHContacto extends RecyclerView.ViewHolder{
        private TextView tvNombre;
        private TextView tvEmail;

        public TextView getTvNombre() {
            return tvNombre;
        }

        public TextView getTvEmail() {
            return tvEmail;
        }

        public VHContacto(View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvEmail = itemView.findViewById(R.id.tvEmail);
        }
    }

    @Override
    public VHContacto onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contacto,parent,false);
        VHContacto vhc = new VHContacto(v);
        return vhc;
    }

    @Override
    public void onBindViewHolder(VHContacto holder, int position) {
        holder.tvNombre.setText(datos.get(position).getNombre());
        holder.tvEmail.setText(datos.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }
}
