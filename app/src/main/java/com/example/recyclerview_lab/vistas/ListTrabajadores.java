package com.example.recyclerview_lab.vistas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.recyclerview_lab.R;
import com.example.recyclerview_lab.modelo.Trabajador;
import com.example.recyclerview_lab.repositorios.TrabajadorRespositos;
import com.example.recyclerview_lab.servicios.ServiceLocator;
import com.example.recyclerview_lab.vistas.adaptador.TrabajadorAdaptador;

import java.util.ArrayList;
import java.util.List;

public class ListTrabajadores extends AppCompatActivity implements  TrabajadorAdaptador.OnItemLongClickListener {

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

        personaAdapter.setOnItemLongClickListener(this);
    }
    private void editarTrabajador(int position) {
        Trabajador trabajador = personaAdapter.getTrabajador(position);

        //Trabajador trabajador = personaAdapter.getTrabajador(position);

        //Intent intent = new Intent(this, EditarTrabajadorActivity.class);
        //intent.putExtra("trabajador", trabajador);
        //startActivityForResult(intent, EDITAR_TRABAJADOR_REQUEST);
    }

    private void eliminarTrabajador(int position) {
        AlertDialog.Builder deleteBuilder = new AlertDialog.Builder(this);
        deleteBuilder.setTitle("Eliminar Trabajador")
                .setMessage("¿Estás seguro de que quieres eliminar este trabajador?")
                .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Trabajador trabajador = personaAdapter.getTrabajador(position);
                        TrabajadorRespositos dbSource = ServiceLocator.getInstance().getDBSource();
                        dbSource.removeTrabajador(trabajador);

                        personaAdapter.removeTrabajador(position);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    @Override
    public void onItemLongClick(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Opciones")
                .setItems(new CharSequence[]{"Editar", "Eliminar"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                editarTrabajador(position);
                                break;
                            case 1:
                                eliminarTrabajador(position);
                                break;
                        }
                    }
                })
                .show();
    }
}