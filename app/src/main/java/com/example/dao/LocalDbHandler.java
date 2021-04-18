package com.example.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class LocalDbHandler extends SQLiteOpenHelper {
    private String onCreateScript = "";
    private String onUpdateScript = "";
    public LocalDbHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        try {
           InputStream in =  context.getAssets().open("Sqlite/OnCreateDb.sql");
           byte[] b = new byte[100];
           while (in.read(b)>0){
                String s = new String(b);
                onCreateScript += s;
           }
           in.close();

            in =  context.getAssets().open("Sqlite/OnUpdateDb.sql");
            b = new byte[100];
            while (in.read(b)>0){
                String s = new String(b);
                onUpdateScript +=s;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(LocalDbHandler.class.getName(),"Query : " + onCreateScript);
        db.execSQL(onCreateScript);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(LocalDbHandler.class.getName(),"Query : " + onUpdateScript);
        db.execSQL(onUpdateScript);
    }
}
