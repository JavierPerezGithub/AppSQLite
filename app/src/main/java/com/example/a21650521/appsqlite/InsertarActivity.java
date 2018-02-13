package com.example.a21650521.appsqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a21650521.appsqlite.model.Contacto;
import com.example.a21650521.appsqlite.sqliteDB.ContactosDatasource;

public class InsertarActivity extends AppCompatActivity {

    private EditText etNombre;
    private EditText etEmail;
    ContactosDatasource cds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);

        etEmail = findViewById(R.id.etEmail);
        etNombre = findViewById(R.id.etNombre);
        cds = new ContactosDatasource(this);
    }
    public void insertarContacto(View v) {
        Contacto contacto = new Contacto(etNombre.getText().toString(), etEmail.getText().toString());
        long idc = cds.insertarContacto(contacto);

        if (idc != -1) {
            Toast.makeText(this, "La inserccion se ha realizado correctamente", Toast.LENGTH_LONG).show();
            etNombre.setText("");
            etEmail.setText("");
        } else {
            Toast.makeText(this, "No se ha podido realizar la inserccion", Toast.LENGTH_LONG).show();
        }
    }
}
