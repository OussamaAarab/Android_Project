package com.GLA_12.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;


public class LocalDbHandler extends SQLiteOpenHelper {
    private String onCreateScript = "";
    private String onUpdateScript = "";
    public LocalDbHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        try {
           InputStream in =  context.getAssets().open("Sqlite/OnCreateDb.sql");
            BufferedInputStream buffin = new BufferedInputStream(in);
           byte[] b = new byte[1024];
           int read;
           while ((read = buffin.read(b))>0){
                String s = new String(b,0,read);
                onCreateScript += s;
           }
           in.close();
           buffin.close();

            in =  context.getAssets().open("Sqlite/OnUpdateDb.sql");
            buffin = new BufferedInputStream(in);
            b = new byte[1024];
            while ((read = buffin.read(b))>0){
                String s = new String(b,0,read);
                onUpdateScript +=s;
            }
            in.close();
            buffin.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(LocalDbHandler.class.getName(),"Query : " + onCreateScript);

        String[] queries = onCreateScript.split(";");
        for (String q : queries){
            Log.d(LocalDbHandler.class.getName(),"Query : " + q);
            if(q.trim().length()>0){
                db.execSQL(q+";");
            }
        }






    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String[] queries = onUpdateScript.split(";");
        for (String q : queries){
            Log.d(LocalDbHandler.class.getName(),"Query : " + q);
            if(q.trim().length()>0){
                db.execSQL(q+";");
            }
        }

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db,oldVersion,newVersion);
    }
}
