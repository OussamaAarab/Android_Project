package com.example.api;

import com.example.beans.Movie;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.ArrayList;

public class API_Movie {
    private API_Factory factory;
    private static final String Search = "https://api.themoviedb.org/3/search/movie";

    public API_Movie(API_Factory factory){ this.factory = factory;}

    public ArrayList<Movie> Search_Movie(String name,String lang,int page) throws Exception {
        ArrayList<Movie> movies = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder builder = HttpUrl.parse(Search).newBuilder();
        builder.addQueryParameter("api_key",factory.getAPI_KEY());
        builder.addQueryParameter("query",name);
        builder.addQueryParameter("language",lang);
        builder.addQueryParameter("page",""+page);
        builder.addQueryParameter("include_adult","false");
        String url = builder.build().toString();

        Request request = new Request.Builder().url(url).build();


        Response response = client.newCall(request).execute();
        String resp =response.body().string();
        System.out.println(resp);
        resp = resp.trim();
        Gson gson = new Gson();
        JsonObject entity = gson.fromJson(resp, JsonObject.class);

        JsonArray array = entity.getAsJsonArray("results");
        for(JsonElement o : array ){
            Movie m = new Movie(o.getAsJsonObject());
            movies.add(m);
        }
        return movies;
    }
}
