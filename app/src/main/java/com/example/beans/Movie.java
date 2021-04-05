package com.example.beans;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Movie {
    private JsonObject obj;
    private boolean adult;
    private String backdrop_path;
    private int budget;
    private Genre[] genres;
    private String homepage;
    private int id;
    private String imdb_id;
    private String original_language;
    private String original_title;
    private String overview;
    private float popularity;
    private String poster_path;
    private Company[] production_companies;
    private String release_date;
    private int revenue;
    private int runtime;
    private Language[] spoken_languages;
    private Country[] production_countries;
    private String status;
    private String tagline;
    private String title;
    private boolean video;
    private float vote_average;
    private int vote_count;

    public Movie(JsonObject o){
        this.obj = o;
        int size = 0;
        int i=0;
        this.adult = o.get("adult").getAsBoolean();
        try {
            this.backdrop_path = o.get("backdrop_path").getAsString();
        }catch (NullPointerException|UnsupportedOperationException e){
            backdrop_path="";
        }

        try {
            this.budget = o.get("budget").getAsInt();
        }catch (NullPointerException|UnsupportedOperationException ignore){

        }
        try {

            JsonArray grs = o.get("grenres").getAsJsonArray();
            size = grs.size();
            this.genres = new Genre[size];
            i=0;
            for(JsonElement genre_elm : grs){
                JsonObject obj = genre_elm.getAsJsonObject();
                Genre g = new Genre();
                g.setId(obj.getAsInt());
                //Todo : Get the genre name
                this.genres[i] = g;
                i++;
            }
        }catch (NullPointerException|UnsupportedOperationException ignore){

        }
        try {
            this.homepage = o.get("homepage").getAsString();
        }catch (NullPointerException|UnsupportedOperationException ignore){

        }
        size = 0;
        try {
            this.id = o.get("id").getAsInt();
        }catch (NullPointerException|UnsupportedOperationException ignore){ }
        try {
            this.imdb_id = o.get("imdb_id").getAsString();
        }catch (NullPointerException ignore){ }
        try {
            this.original_language = o.get("original_language").getAsString();
        }catch (NullPointerException|UnsupportedOperationException ignore){ }

        try {
            this.original_title = o.get("original_title").getAsString();
        }catch (NullPointerException|UnsupportedOperationException ignore){ }
        try {
            this.overview = o.get("overview").getAsString();
        }catch (NullPointerException|UnsupportedOperationException ignore){ }
        try {
            this.popularity = o.get("popularity").getAsFloat();
        }catch (NullPointerException|UnsupportedOperationException ignore){ }
        try {
            this.poster_path = o.get("poster_path").getAsString();
        }catch (NullPointerException|UnsupportedOperationException ignore){ }

        try {
            JsonArray cmpns = o.get("production_companies").getAsJsonArray();
            size = cmpns.size();
            i=0;
            production_companies = new Company[size];
            for(JsonElement cmp : cmpns){
                Company company = new Company();
                JsonObject cmpobj = cmp.getAsJsonObject();
                company.setId(cmpobj.get("id").getAsInt());
                company.setLogo_path(cmpobj.get("logo_path").getAsString());
                company.setName(cmpobj.get("name").getAsString());
                company.setOrigin_country(cmpobj.get("origin_country").getAsString());
                this.production_companies[i] = company;
                i++;
            }
        }catch (NullPointerException|UnsupportedOperationException ignore){ }

        try {
            this.release_date = o.get("release_date").getAsString();
        }catch (NullPointerException|UnsupportedOperationException ignore){ }
        try {
            this.revenue = o.get("revenue").getAsInt();
        }catch (NullPointerException|UnsupportedOperationException ignore){ }
        try {
            this.runtime = o.get("runtime").getAsInt();
        }catch (NullPointerException|UnsupportedOperationException ignore){ }
        //TODO : Countrie
        try {
            JsonArray spk_lng = o.get("spoken_languages").getAsJsonArray();
            size = spk_lng.size();
            i =0;
            spoken_languages = new Language[size];
            for(JsonElement spkelm : spk_lng){
                Language lng = new Language();
                lng.setEnglish_name(spkelm.getAsJsonObject().get("english_name").getAsString());
                lng.setName(spkelm.getAsJsonObject().get("name").getAsString());
                lng.setIso_639_1(spkelm.getAsJsonObject().get("iso_639_1").getAsString());
                spoken_languages[i] = lng;
                i++;
            }
        }catch (NullPointerException|UnsupportedOperationException ignore){ }
        try {
            this.status = o.get("status").getAsString();
        }catch (NullPointerException|UnsupportedOperationException ignore){ }
        try {
            this.tagline = o.get("tagline").getAsString();
        }catch (NullPointerException|UnsupportedOperationException ignore){ }
        try {
            this.title = o.get("title").getAsString();
        }catch (NullPointerException|UnsupportedOperationException ignore){ }
        try {
            this.video = o.get("video").getAsBoolean();
        }catch (NullPointerException|UnsupportedOperationException ignore){ }
        try {
            this.vote_average = o.get("vote_average").getAsFloat();
        }catch (NullPointerException|UnsupportedOperationException ignore){ }
        try {
            this.vote_count = o.get("vote_count").getAsInt();
        }catch (NullPointerException|UnsupportedOperationException ignore){ }

    }


    //getters
    public int getId() {
        return id;
    }

    public Company[] getProduction_companies() {
        return production_companies;
    }

    public float getPopularity() {
        return popularity;
    }

    public float getVote_average() {
        return vote_average;
    }

    public Genre[] getGenres() {
        return genres;
    }

    public int getBudget() {
        return budget;
    }

    public int getRevenue() {
        return revenue;
    }

    public int getRuntime() {
        return runtime;
    }

    public int getVote_count() {
        return vote_count;
    }

    public Language[] getSpoken_languages() {
        return spoken_languages;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getStatus() {
        return status;
    }

    public String getTagline() {
        return tagline;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAdult() {
        return adult;
    }

    public boolean isVideo() {
        return video;
    }
    //setters
    public void setId(int id) {
        this.id = id;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public void setGenres(Genre[] genres) {
        this.genres = genres;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public void setProduction_companies(Company[] production_companies) {
        this.production_companies = production_companies;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public void setSpoken_languages(Language[] spoken_languages) {
        this.spoken_languages = spoken_languages;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public JsonObject getObj() {
        return obj;
    }

}
