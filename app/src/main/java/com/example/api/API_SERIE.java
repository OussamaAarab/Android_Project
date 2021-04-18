package com.example.api;

import android.util.Log;

import androidx.annotation.Nullable;

import com.example.beans.Movie;
import com.example.beans.Review;
import com.example.beans.Serie;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class API_SERIE {

    private API_Factory factory;
    private static final String Search = "https://api.themoviedb.org/3/search/tv";
    private static final String  Serie_Details = "https://api.themoviedb.org/3/tv/";
    private static final String  Latest_Series = "https://api.themoviedb.org/3/tv/latest";
    private static final String Trending_Series = "https://api.themoviedb.org/3/trending/tv/";


    public API_SERIE(API_Factory factory){ this.factory = factory;}

    public ArrayList<Serie> Search_Serie(String name, int page) throws Exception {
        ArrayList<Serie> series = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder builder = HttpUrl.parse(Search).newBuilder();
        builder.addQueryParameter("api_key",factory.getAPI_KEY());
        builder.addQueryParameter("query",name);
        builder.addQueryParameter("language",API_Factory.getLang());
        builder.addQueryParameter("page",""+page);
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
            Serie s = new Serie(o.getAsJsonObject());
            series.add(s);
        }
        return series;
    }
    public Serie findSerie(int id, @Nullable String append_to_response) throws IOException {
        Serie serie = null;

        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder builder = HttpUrl.parse(Serie_Details+id).newBuilder();
        builder.addQueryParameter("api_key",factory.getAPI_KEY());
        builder.addQueryParameter("language",API_Factory.getLang());
        if(append_to_response!=null){
            builder.addQueryParameter("append_to_response",append_to_response);
        }
        String url = builder.build().toString();

        Request request = new Request.Builder().url(url).build();

        Response response = client.newCall(request).execute();
        String resp =response.body().string();
        System.out.println(resp);
        resp = resp.trim();
        Gson gson = new Gson();
        JsonObject entity = gson.fromJson(resp, JsonObject.class);
        serie = new Serie(entity);
        return serie;
    }

    public ArrayList<Serie> findLatestSeries(String time_window) throws IOException {
        ArrayList<Serie> series = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder builder = HttpUrl.parse(Latest_Series).newBuilder();
        builder.addQueryParameter("api_key",factory.getAPI_KEY());
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
            Serie s = new Serie(o.getAsJsonObject());
            series.add(s);
        }
        return series;

    }
    public ArrayList<Serie> findSimilarSeries(int id, @Nullable String append_to_response) throws IOException {
        ArrayList<Serie> series = new ArrayList<>();

        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder builder = HttpUrl.parse(Serie_Details+id+"similar").newBuilder();
        builder.addQueryParameter("api_key",factory.getAPI_KEY());
        builder.addQueryParameter("language",API_Factory.getLang());
        if(append_to_response!=null){
            builder.addQueryParameter("append_to_response",append_to_response);
        }
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
            Serie s = new Serie(o.getAsJsonObject());
            series.add(s);
        }
        return series;
    }

    public ArrayList<Serie> findRecommendationsSerie(int id, @Nullable String append_to_response) throws IOException {
        ArrayList<Serie> series = new ArrayList<>();

        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder builder = HttpUrl.parse(Serie_Details+id+"recommendations").newBuilder();
        builder.addQueryParameter("api_key",factory.getAPI_KEY());
        builder.addQueryParameter("language",API_Factory.getLang());
        if(append_to_response!=null){
            builder.addQueryParameter("append_to_response",append_to_response);
        }
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
            Serie s = new Serie(o.getAsJsonObject());
            series.add(s);
        }
        return series;
    }

    public ArrayList<Review> GetSerieReviews(int id, @Nullable String append_to_response) throws IOException {

        ArrayList<Review> reviews = new ArrayList<>();

        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder builder = HttpUrl.parse(Serie_Details+id+"reviews").newBuilder();
        builder.addQueryParameter("api_key",factory.getAPI_KEY());
        builder.addQueryParameter("language",API_Factory.getLang());
        if(append_to_response!=null){
            builder.addQueryParameter("append_to_response",append_to_response);
        }
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
            Review r = new Review(o.getAsJsonObject());
            reviews.add(r);
        }
        return reviews;
    }



    public ArrayList<Serie> findTrendingSeries(String time_window,int page) throws IOException {
        ArrayList<Serie> series = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder builder = HttpUrl.parse(Trending_Series+time_window).newBuilder();
        builder.addQueryParameter("api_key",factory.getAPI_KEY());
        builder.addQueryParameter("page",page+"");
        builder.addQueryParameter("language",API_Factory.getLang());
        String url = builder.build().toString();

        Request request = new Request.Builder().url(url).build();

        Response response = client.newCall(request).execute();
        Log.d(API_Movie.class.getName(),"API LIMIT REMAINING : " + response.headers().get("X-RateLimit-Remaining") );
        String resp =response.body().string();
        System.out.println(resp);
        resp = resp.trim();
        Gson gson = new Gson();
        JsonObject entity = gson.fromJson(resp, JsonObject.class);

        JsonArray array = entity.getAsJsonArray("results");
        for(JsonElement o : array ){
            Serie s = new Serie(o.getAsJsonObject());
            series.add(s);
        }
        return series;
    }
    public JsonObject findTrendingSeriesJson(String time_window,int page) throws IOException {

        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder builder = HttpUrl.parse(Trending_Series+time_window).newBuilder();
        builder.addQueryParameter("api_key",factory.getAPI_KEY());
        builder.addQueryParameter("page",page+"");
        builder.addQueryParameter("language",API_Factory.getLang());
        String url = builder.build().toString();

        Request request = new Request.Builder().url(url).build();

        Response response = client.newCall(request).execute();
        Log.d(API_Movie.class.getName(),"API LIMIT REMAINING : " + response.headers().get("X-RateLimit-Remaining") );
        String resp =response.body().string();
        System.out.println(resp);
        resp = resp.trim();
        Gson gson = new Gson();
        JsonObject entity = gson.fromJson(resp, JsonObject.class);
        return entity;
    }




}