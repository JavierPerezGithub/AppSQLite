package com.example.a21650521.appsqlite.sqliteDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.a21650521.appsqlite.model.Contacto;

import java.util.ArrayList;

/**
 * Created by 21650521 on 12/02/2018.
 */

public class ContactosDatasource {
    private Context miContext;
    private ContactosSQLiteHelper miSQLiteHelper;

    public ContactosDatasource(Context context) {
        miContext = context;
        miSQLiteHelper = new ContactosSQLiteHelper(miContext);
    }

    public SQLiteDatabase openReadable() {
        return miSQLiteHelper.getReadableDatabase();
    }

    public SQLiteDatabase openWriteable() {
        return miSQLiteHelper.getWritableDatabase();
    }

    public void close(SQLiteDatabase database) {
        database.close();
    }

    public long insertarContacto(Contacto contacto) {

        SQLiteDatabase database = openWriteable();
        database.beginTransaction();
        //clave, valor
        ContentValues cv = new ContentValues();
        cv.put(ContactosDBContract.ContactoEntry.COLUMN_NAME, contacto.getNombre());
        cv.put(ContactosDBContract.ContactoEntry.COLUMN_MAIL, contacto.getEmail());

        long idContacto = database.insert(ContactosDBContract.ContactoEntry.TABLE_NAME, null, cv);
        if (idContacto != -1) {
            database.setTransactionSuccessful();
        }
        database.endTransaction();
        close(database);

        return idContacto;
    }

    public void actualizarContacto(Contacto contacto) {
        SQLiteDatabase database = openWriteable();
        database.beginTransaction();

        ContentValues cv = new ContentValues();
        cv.put(ContactosDBContract.ContactoEntry.COLUMN_NAME, contacto.getNombre());
        cv.put(ContactosDBContract.ContactoEntry.COLUMN_MAIL, contacto.getEmail());


        String where = ContactosDBContract.ContactoEntry.COLUMN_ID + " = " + contacto.getId();
        database.update(ContactosDBContract.ContactoEntry.TABLE_NAME, cv, where, null);
        //Otras formas de especificar las sentencias where
        /*String where2 = ContactosDBContract.ContactoEntry.COLUMN_ID+ " = ?";
        String [] args = {String.valueOf(contacto.getId())};
        database.update(ContactosDBContract.ContactoEntry.TABLE_NAME,cv,where2,args);

        String where3 = "%s = %d";
        database.update(ContactosDBContract.ContactoEntry.TABLE_NAME,cv,
          String.format(where3,
                  ContactosDBContract.ContactoEntry.COLUMN_ID,contacto.getId()),null);

        database.update(ContactosDBContract.ContactoEntry.TABLE_NAME,cv,
                String.format("%s=%d",
                  ContactosDBContract.ContactoEntry.COLUMN_ID,contacto.getId()),null);*/

        database.setTransactionSuccessful();
        database.endTransaction();
        close(database);
    }
    public void borrarContacto(long idContacto) {

        SQLiteDatabase database = openWriteable();
        database.beginTransaction();

        String [] args = {String.valueOf(idContacto)};
        database.delete(ContactosDBContract.ContactoEntry.TABLE_NAME,
                ContactosDBContract.ContactoEntry.COLUMN_ID + "=?", args);

        /*database.delete(ContactosDBContract.ContactoEntry.TABLE_NAME,
                ContactosDBContract.ContactoEntry.COLUMN_ID
                        + " = " + idContacto,null);*/

        database.setTransactionSuccessful();
        database.endTransaction();
        close(database);

    }

    public Contacto leerContacto(long idContacto) {
        SQLiteDatabase database = openReadable();

        Contacto contacto = null;
        String sentencia = "SELECT"
                + ContactosDBContract.ContactoEntry.COLUMN_ID + ", "
                + ContactosDBContract.ContactoEntry.COLUMN_NAME + ", "
                + ContactosDBContract.ContactoEntry.COLUMN_MAIL
                + "FROM contactos WHERE "
                + ContactosDBContract.ContactoEntry.COLUMN_ID + " = "
                + idContacto;

        Cursor miCursor = database.rawQuery(sentencia, null);

        String nombre = "";
        String email = "";
        if (miCursor.moveToFirst()) {
               idContacto = miCursor.getLong(miCursor.getColumnIndex(ContactosDBContract.ContactoEntry.COLUMN_ID));
               nombre = miCursor.getString(miCursor.getColumnIndex(ContactosDBContract.ContactoEntry.COLUMN_NAME));
               email = miCursor.getString(miCursor.getColumnIndex(ContactosDBContract.ContactoEntry.COLUMN_MAIL));

               contacto = new Contacto(nombre, email);
               contacto.setId(idContacto);
        }

        miCursor.close();
        database.close();
        return contacto;
    }

    public ArrayList<Contacto> leerContactos() {
        ArrayList<Contacto> contactos = new ArrayList<Contacto>();

        SQLiteDatabase database = openReadable();
        Contacto contacto = null;
        String [] columnas = {ContactosDBContract.ContactoEntry.COLUMN_ID,
                        ContactosDBContract.ContactoEntry.COLUMN_NAME,
                        ContactosDBContract.ContactoEntry.COLUMN_MAIL};
        Cursor miCursor = database.query(ContactosDBContract.ContactoEntry.TABLE_NAME, columnas,
                null, null, null, null, null);
        long idContacto;
        String nombre, email;

        if (miCursor.moveToFirst()) {
            do {
                idContacto = miCursor.getLong(
                        miCursor.getColumnIndex(
                                ContactosDBContract.ContactoEntry.COLUMN_ID));
                nombre = miCursor.getString(
                            miCursor.getColumnIndex(
                                    ContactosDBContract.ContactoEntry.COLUMN_NAME));
                email = miCursor.getString(
                            miCursor.getColumnIndex(
                                    ContactosDBContract.ContactoEntry. COLUMN_MAIL));

                contacto = new Contacto(nombre,email);
                contacto.setId(idContacto);
                contactos.add(contacto);

            } while (miCursor.moveToNext());
        }
        miCursor.close();
        database.close();
        return contactos;
    }
}