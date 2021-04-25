package com.intan.sqlite;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "biodatadiri.db"; //membuat database
    private static final int DATABASE_VERSION = 1;
    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //digunakan untuk membuat tabel pada database
        String sql = "create table biodata (no integer primary key, nama text null, tgl text null, jk text null, alamat text null);";
        Log.d("Data", "onCreate: " +sql);
        db.execSQL(sql);
        //digunakan untuk membuat data pada tabel biodata
        sql = "INSERT INTO biodata (no, nama, tgl, jk, alamat) VALUES ('1', 'Intan', '2001-08-08', 'Perempuan', 'Jember');";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
    }
}
