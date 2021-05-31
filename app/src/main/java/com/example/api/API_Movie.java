package com.example.api;

import android.util.Log;

import androidx.annotation.Nullable;

import com.example.beans.Movie;
import com.example.beans.Review;
import com.example.beans.Video;
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
    private static final String Popular_Movies = "https://api.themoviedb.org/3/movie/popular";
    private static final String  Movie_Details = "https://api.themoviedb.org/3/movie/";
    private static final String  Latest_Movies = "https://api.themoviedb.org/3/movie/latest";
    private static final String  TopRated_Movies = "https://api.themoviedb.org/3/movie/top_rated";
    private static final String  UpComing_Movies = "https://api.themoviedb.org/3/movie/upcoming";
    private static final String  Genre_Movies = "https://api.themoviedb.org/3/discover/movie";
    //https://api.themoviedb.org/3/discover/movie?api_key=cdd42548fd8b23411054cc617a1211de&with_genres=27

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
        Log.d(this.getClass().getName(),url);
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

<<<<<<< HEAD
    public ArrayList<Movie> findPopularMovies( ) throws IOException {
=======
    public ArrayList<Movie> findPopularMovies(int page) throws IOException {
>>>>>>> 0ebc4fb34fc06bed9ca33b8fdf799dff5df7cea7
        ArrayList<Movie> movies = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder builder = HttpUrl.parse(Popular_Movies).newBuilder();
        builder.addQueryParameter("api_key",factory.getAPI_KEY());
<<<<<<< HEAD
       // builder.addQueryParameter("page",page+"");
=======
        builder.addQueryParameter("page",page+"");
>>>>>>> 0ebc4fb34fc06bed9ca33b8fdf799dff5df7cea7
        builder.addQueryParameter("language",API_Factory.getLang());
        String url = builder.build().toString();
        Log.d(this.getClass().getName(),url);
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

    public ArrayList<Movie> findGenreMovies(int id, int page) throws IOException {
        ArrayList<Movie> movies = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder builder = HttpUrl.parse(Genre_Movies).newBuilder();
        builder.addQueryParameter("api_key",factory.getAPI_KEY());
        builder.addQueryParameter("page",page+"");
        builder.addQueryParameter("with_genres",id+"");
        String url = builder.build().toString();
        Log.d(this.getClass().getName(),url);
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
        if(append_to_response=="videos"){
            JsonObject obj = entity.get("videos").getAsJsonObject();
            JsonArray videos = obj.get("results").getAsJsonArray();
            ArrayList<Video> vids = new ArrayList<>();
            for (JsonElement elm : videos){
                JsonObject vid_Json = elm.getAsJsonObject();
                Video video = new Video();
                video.setId(vid_Json.get("id").getAsString());
                video.setIso_639_1(vid_Json.get("iso_639_1").getAsString());
                video.setIso_3166_1(vid_Json.get("iso_3166_1").getAsString());
                video.setKey(vid_Json.get("key").getAsString());
                video.setName(vid_Json.get("name").getAsString());
                video.setSite(vid_Json.get("site").getAsString());
                video.setSize(vid_Json.get("size").getAsInt());
                video.setType(vid_Json.get("type").getAsString());
                vids.add(video);
            }
            movie.setVideos(vids);
        }

        return movie;
    }


    @Deprecated
    public ArrayList<Movie> findLatestMovies() throws IOException {
        ArrayList<Movie> movies = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder builder = HttpUrl.parse(Latest_Movies).newBuilder();
        builder.addQueryParameter("api_key",factory.getAPI_KEY());
        builder.addQueryParameter("lang",API_Factory.getLang());
        String url = builder.build().toString();
        Log.d(getClass().getName(),url);

        Request request = new Request.Builder().url(url).build();

        Response response = client.newCall(request).execute();
        String resp =response.body().string();
        System.out.println(resp);
        resp = resp.trim();
        Log.d(getClass().getName(),resp);
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
        HttpUrl.Builder builder = HttpUrl.parse(Movie_Details+id+"/similar").newBuilder();
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

    public String getTrailerKey(int id ) throws IOException {
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder builder = HttpUrl.parse(Movie_Details+id+"/videos").newBuilder();
        builder.addQueryParameter("api_key",factory.getAPI_KEY());

        String url = builder.build().toString();
        Log.d(getClass().getName(),url);

        Request request = new Request.Builder().url(url).build();

        Response response = client.newCall(request).execute();
        String resp =response.body().string();
        System.out.println(resp);
        resp = resp.trim();
        Gson gson = new Gson();
        JsonObject entity = gson.fromJson(resp, JsonObject.class);

        JsonArray array = entity.getAsJsonArray("results");
        for (JsonElement res : array){
            JsonObject res_ = res.getAsJsonObject();
            if(res_.get("type").getAsString().equals("Trailer")){
                return res_.get("key").getAsString();
            }
        }
        return "";


    }

    public ArrayList<Movie> findTopRatedMovies(int page) throws IOException {
        ArrayList<Movie> movies = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder builder = HttpUrl.parse(TopRated_Movies).newBuilder();
        builder.addQueryParameter("api_key",factory.getAPI_KEY());
        builder.addQueryParameter("lang",API_Factory.getLang());
        builder.addQueryParameter("lpage",""+page);
        String url = builder.build().toString();
        Log.d(getClass().getName(),url);

        Request request = new Request.Builder().url(url).build();

        Response response = client.newCall(request).execute();
        String resp =response.body().string();
        System.out.println(resp);
        resp = resp.trim();
        Log.d(getClass().getName(),resp);
        Gson gson = new Gson();
        JsonObject entity = gson.fromJson(resp, JsonObject.class);

        JsonArray array = entity.getAsJsonArray("results");
        for(JsonElement o : array ){
            Movie m = new Movie(o.getAsJsonObject());
            movies.add(m);
        }
        return movies;

    }

    public ArrayList<Movie> findUpcomingMovies(int page) throws IOException {
        ArrayList<Movie> movies = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder builder = HttpUrl.parse(UpComing_Movies).newBuilder();
        builder.addQueryParameter("api_key",factory.getAPI_KEY());
        builder.addQueryParameter("lang",API_Factory.getLang());
        builder.addQueryParameter("lpage",""+page);
        String url = builder.build().toString();
        Log.d(getClass().getName(),url);

        Request request = new Request.Builder().url(url).build();

        Response response = client.newCall(request).execute();
        String resp =response.body().string();
        System.out.println(resp);
        resp = resp.trim();
        Log.d(getClass().getName(),resp);
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
