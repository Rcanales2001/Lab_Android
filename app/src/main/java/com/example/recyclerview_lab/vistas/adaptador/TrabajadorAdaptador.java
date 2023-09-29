package com.example.recyclerview_lab.vistas.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview_lab.R;
import com.example.recyclerview_lab.modelo.Trabajador;
import com.example.recyclerview_lab.vistas.vistaHolders.VistaHolderTrabajador;

import java.util.ArrayList;

public class TrabajadorAdaptador extends RecyclerView.Adapter<VistaHolderTrabajador>{
    private ArrayList<Trabajador> datos;
    public TrabajadorAdaptador(ArrayList<Trabajador> datos) {
        this.datos = datos;
    }

    @NonNull
    @Override
    public VistaHolderTrabajador onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trabajadores,parent,false);
        return new VistaHolderTrabajador(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VistaHolderTrabajador holder, int position) {

        holder.getCodigoPersona().setText(String.valueOf(datos.get(position).getCodigoPersona()));
        holder.getNombrePersona().setText(datos.get(position).getNombrePersona() + " " + datos.get(position).getApellidoPersona());
        holder.getTipoTrabajador().setText(datos.get(position).getTipoTrabajador() == 1? "TP" : "TB" );
        holder.getTotalPagar().setText(Float.toString(datos.get(position).getTotalPagar()));
    }
    @Override
    public int getItemCount() {
        return datos.size();
    }
}
