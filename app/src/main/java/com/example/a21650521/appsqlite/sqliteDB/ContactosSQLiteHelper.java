package com.example.a21650521.appsqlite.sqliteDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 21650521 on 12/02/2018.
 */

public class ContactosSQLiteHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "ContactosDB";
    static final int DATABASE_VERSION = 1;

    static final String CREATE_TABLE_CONTACTOS =
            "CREATE TABLE " + ContactosDBContract.ContactoEntry.TABLE_NAME + "( " +
                    ContactosDBContract.ContactoEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                    ContactosDBContract.ContactoEntry.COLUMN_NAME + " TEXT NOT NULL," +
                    ContactosDBContract.ContactoEntry.COLUMN_MAIL + " TEXT NOT NULL);";

    public ContactosSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CONTACTOS );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +
                ContactosDBContract.ContactoEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}