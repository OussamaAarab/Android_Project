package com.example.api;


import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;

public class API_Factory {
    private String API_KEY;
    private String image_source;
    private static API_Factory factory;
    private static String lang;
    private API_Factory(String API_KEY,String image_source){
        this.API_KEY = API_KEY;
        this.image_source = image_source;
        lang = Locale.getDefault().getLanguage();
    }
    public static API_Factory getInstance(Context context) throws Exception {
        if(factory==null){
            AssetManager manager =  context.getAssets();
            InputStream in = manager.open("config/Api_config.properties") ;
            if(in==null) throw new Exception("Properties file not found");

            Properties properties = new Properties();

            try {
                properties.load(in);
                String api_key = properties.getProperty("API_key");
                String image_source = properties.getProperty("images_source");
                factory = new API_Factory(api_key,image_source);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return factory;


    }

    public String getAPI_KEY() {
        return API_KEY;
    }

    public static String getLang() {
        return lang;
    }

    public String getImage_source() {
        return image_source;
    }
    public API_Movie getAPI_Movie(){
        return new API_Movie(this);
    }
    public API_SERIE getAPI_SERIE(){
        return new API_SERIE(this);
    }
}
