package com.example.a21650521.appsqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.a21650521.appsqlite.model.Contacto;
import com.example.a21650521.appsqlite.sqliteDB.ContactosDatasource;

public class SeleccionActivity extends AppCompatActivity {
    private Long id;
    private EditText nombre, email;
    private Contacto contacto;
    ContactosDatasource cds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion);
        id = getIntent().getLongExtra("ID",-1);
        nombre = findViewById(R.id.etNombreS);
        email = findViewById(R.id.etEmailS);
        cds = new ContactosDatasource(this);
        contacto = cds.leerContacto(id);

        nombre.setText(contacto.getNombre());
        email.setText(contacto.getEmail());

    }
}
