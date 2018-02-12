package com.example.a21650521.appsqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.a21650521.appsqlite.model.Contacto;
import com.example.a21650521.appsqlite.rvUtils.Adapter;
import com.example.a21650521.appsqlite.sqliteDB.ContactosDatasource;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rv;
    private LinearLayoutManager llm;
    private ContactosDatasource cds;
    private ArrayList<Contacto>listaContactos;
    private Adapter ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.rvContactos);
        cds = new ContactosDatasource(this);
        listaContactos = new ArrayList<Contacto>();
        cargarRecyclerView();

    }

    private void cargarRecyclerView() {
        rv.setHasFixedSize(true);

        llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        ad = new Adapter(listaContactos);
        rv.setAdapter(ad);
    }

    public void consultar(View v){
        listaContactos = cds.leerContactos();

        if(listaContactos.isEmpty()){
            Toast.makeText(this, "Esto está vacio", Toast.LENGTH_SHORT).show();
        }else{
            cargarRecyclerView();
        }

    }

    public void insertar(View v){
        Intent i = new Intent(this,InsertarActivity.class);
        startActivity(i);
    }
}
