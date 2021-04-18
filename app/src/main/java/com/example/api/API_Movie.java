package com.example.api;

import android.util.Log;

import androidx.annotation.Nullable;

import com.example.beans.Movie;
import com.example.beans.Review;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import kotlin.Pair;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.http2.Header;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


public class API_Movie {
    private API_Factory factory;
    private static final String Search = "https://api.themoviedb.org/3/search/movie";
    private static final String Trending_Movies = "https://api.themoviedb.org/3/trending/movie/";
    private static final String  Movie_Details = "https://api.themoviedb.org/3/movie/";
    private static final String  Latest_Movies = "https://api.themoviedb.org/3/movie/latest";

    public API_Movie(API_Factory factory){ this.factory = factory;}

    public ArrayList<Movie> Search_Movie(String name,int page) throws Exception {
        Log.d(API_Movie.class.getName(),"Search Language : " + API_Factory.getLang());
        ArrayList<Movie> movies = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder builder = HttpUrl.parse(Search).newBuilder();
        builder.addQueryParameter("api_key",factory.getAPI_KEY());
        builder.addQueryParameter("query",name);
        builder.addQueryParameter("language",API_Factory.getLang());
        builder.addQueryParameter("page",""+page);
        builder.addQueryParameter("include_adult","false");
        String url = builder.build().toString();

        Request request = new Request.Builder().url(url).build();


        Response response = client.newCall(request).execute();

        Log.d(API_Movie.class.getName(),"API LIMIT REMAINING : " + response.header("access-control-expose-headers") );

/*
        for (Pair<? extends String, ? extends String> h : response.headers()){

            Log.d("Response values  : ",h.component1() + " : " + h.component2());

        }

 */
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
    public ArrayList<Movie> findTrendingMovies(String time_window,int page) throws IOException {
        ArrayList<Movie> movies = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder builder = HttpUrl.parse(Trending_Movies+time_window).newBuilder();
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
            Movie m = new Movie(o.getAsJsonObject());
            movies.add(m);
        }
        return movies;

    }
    public JsonObject findTrendingMoviesJson(String time_window,int page) throws IOException {
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder builder = HttpUrl.parse(Trending_Movies+time_window).newBuilder();
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
    public Movie findMovie(int movie_id, @Nullable String append_to_response) throws IOException {
        Movie movie = null;

        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder builder = HttpUrl.parse(Movie_Details+movie_id).newBuilder();
        builder.addQueryParameter("api_key",factory.getAPI_KEY());
        builder.addQueryParameter("language",API_Factory.getLang());
        if(append_to_response!=null){
            builder.addQueryParameter("append_to_response",append_to_response);
        }
        String url = builder.build().toString();

        Request request = new Request.Builder().url(url).build();

        Response response = client.newCall(request).execute();
        Log.d(API_Movie.class.getName(),"API LIMIT REMAINING : " + response.headers().get("X-RateLimit-Remaining") );
        String resp =response.body().string();
        System.out.println(resp);
        resp = resp.trim();
        Gson gson = new Gson();
        JsonObject entity = gson.fromJson(resp, JsonObject.class);
        movie = new Movie(entity);
        return movie;
    }

    public ArrayList<Movie> findLatestMovies(String time_window) throws IOException {
        ArrayList<Movie> movies = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder builder = HttpUrl.parse(Latest_Movies).newBuilder();
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
            Movie m = new Movie(o.getAsJsonObject());
            movies.add(m);
        }
        return movies;

    }
    public ArrayList<Movie> findSimilarMovies(int id, String lang, @Nullable String append_to_response) throws IOException {
        ArrayList<Movie> movies = new ArrayList<>();

        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder builder = HttpUrl.parse(Movie_Details+id+"similar").newBuilder();
        builder.addQueryParameter("api_key",factory.getAPI_KEY());
        builder.addQueryParameter("language",lang);
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
            Movie m = new Movie(o.getAsJsonObject());
            movies.add(m);
        }
        return movies;
    }

    public ArrayList<Movie> findRecommendationsMovie(int id, String lang, @Nullable String append_to_response) throws IOException {
        ArrayList<Movie> movies = new ArrayList<>();

        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder builder = HttpUrl.parse(Movie_Details+id+"recommendations").newBuilder();
        builder.addQueryParameter("api_key",factory.getAPI_KEY());
        builder.addQueryParameter("language",lang);
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
            Movie m = new Movie(o.getAsJsonObject());
            movies.add(m);
        }
        return movies;
    }

    public ArrayList<Review> GetMovieReviews(int id, String lang, @Nullable String append_to_response) throws IOException {

        ArrayList<Review> reviews = new ArrayList<>();

        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder builder = HttpUrl.parse(Movie_Details+id+"reviews").newBuilder();
        builder.addQueryParameter("api_key",factory.getAPI_KEY());
        builder.addQueryParameter("language",lang);
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

}
