package com.example.a21650521.appsqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a21650521.appsqlite.model.Contacto;
import com.example.a21650521.appsqlite.sqliteDB.ContactosDatasource;

public class BorrarActualizarActivity extends AppCompatActivity {
    private Contacto contacto;
    private EditText nombre, email;
    ContactosDatasource contactosDatasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar_actualizar);
        nombre = findViewById(R.id.detaNombre);
        email = findViewById(R.id.detaEmail);
        contactosDatasource = new ContactosDatasource(this);

        contacto = (Contacto) getIntent().getExtras().get("CONTACTO");
        nombre.setText(contacto.getNombre().toString());
        email.setText(contacto.getEmail().toString());
    }

    public void borrar(View view) {
        contactosDatasource.borrarContacto(contacto.getId());
        startActivity(new Intent(BorrarActualizarActivity.this,MainActivity.class));
        Toast.makeText(this,"El borrado se ha realizado correctamente..."
                ,Toast.LENGTH_LONG).show();
    }

    public void actualizar(View view) {

        String nom = nombre.getText().toString();
        String ema = email.getText().toString();
        Contacto paquete = new Contacto(nom,ema);
        paquete.setId(contacto.getId());
        contactosDatasource.actualizarContacto(paquete);
        startActivity(new Intent(BorrarActualizarActivity.this,MainActivity.class));
        Toast.makeText(this,"La actualización se ha realizado correctamente..."
                ,Toast.LENGTH_LONG).show();
    }
}
