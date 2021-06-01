package com.example.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DaoFactory {
    public static final int DB_VERSION = 7;
    protected final static String DB_NAME = "Tickets_Local_Db";

    protected SQLiteDatabase db = null;
    private LocalDbHandler handler = null;
    public DaoFactory(Context context){
        handler = new LocalDbHandler(context,DB_NAME,null,DB_VERSION);
    }
    public SQLiteDatabase open(){
        if(db == null){
            db = handler.getWritableDatabase();
        }
        return db;
    }
    public void close(){
        db.close();
        db=null;
    }

    public LocalDbHandler getHandler() {
        return handler;
    }
}
