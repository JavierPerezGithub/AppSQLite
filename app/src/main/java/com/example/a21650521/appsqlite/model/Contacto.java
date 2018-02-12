package com.example.a21650521.appsqlite.model;

import java.io.Serializable;

/**
 * Created by 21650521 on 12/02/2018.
 */

public class Contacto implements Serializable {

    private long id;
    private String nombre;
    private String email;

    public Contacto(String nombre, String email) {
        this.id = -1;
        this.nombre = nombre;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
