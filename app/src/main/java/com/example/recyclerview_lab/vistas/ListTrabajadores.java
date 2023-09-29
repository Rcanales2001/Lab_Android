package com.example.recyclerview_lab.vistas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.recyclerview_lab.R;
import com.example.recyclerview_lab.modelo.Trabajador;
import com.example.recyclerview_lab.repositorios.TrabajadorRespositos;
import com.example.recyclerview_lab.servicios.ServiceLocator;
import com.example.recyclerview_lab.vistas.adaptador.TrabajadorAdaptador;

import java.util.ArrayList;
import java.util.List;

public class ListTrabajadores extends AppCompatActivity {

    private TrabajadorAdaptador personaAdapter;

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_trabajadores);

        TrabajadorRespositos dbSource = ServiceLocator.getInstance().getDBSource();
        List<Trabajador> listaTrabajadores = dbSource.getAllListTrabajadores();

        // Configurando adaptador
        personaAdapter = new TrabajadorAdaptador((ArrayList<Trabajador>) listaTrabajadores);
        layoutManager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.rcvTrabajadores);
        recyclerView.setAdapter(personaAdapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
    }
}