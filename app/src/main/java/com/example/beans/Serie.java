package com.example.beans;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Serie {

    private JsonObject obj;
    private String backdrop_path;
    private Person[] created_by;
    private int[] episode_run_time;
    private String first_air_date;
    private Genre[] genres;
    private String homepage;
    private int id;
    private boolean in_production;
    private Language[] languages;
    private String last_air_date;
    private Episode last_episode_to_air; //Put this into constructor
    private String name;
    private String next_episode_to_air;
    private Network[] networks;
    private int number_of_episodes;
    private int number_of_seasons;
    private String[] origin_country;
    private Language original_language;
    private String original_name;
    private String overview;
    private int popularity;
    private String poster_path;
    private Season seasons;
    private String status;
    private String tagline;
    private String type;
    private float vote_average;
    private int vote_count;

    public Serie(JsonObject o){
        this.obj = o;
        int size = 0;
        int i=0;

        try {
            this.backdrop_path = o.get("backdrop_path").getAsString();
        }catch (NullPointerException|UnsupportedOperationException e){
            backdrop_path="";
        }

        try {
            this.name = o.get("name").getAsString();
        }catch (NullPointerException|UnsupportedOperationException e){
        }

        try {
            this.number_of_seasons = o.get("number_of_seasons").getAsInt();
        }catch (NullPointerException|UnsupportedOperationException e){
        }

        try {
            this.number_of_episodes = o.get("number_of_episodes").getAsInt();
        }catch (NullPointerException|UnsupportedOperationException e){
        }

        try {
            this.popularity = o.get("popularity").getAsInt();
        }catch (NullPointerException|UnsupportedOperationException e){
        }

        try {
            this.original_name = o.get("original_name").getAsString();
        }catch (NullPointerException|UnsupportedOperationException e){
        }

        try {
            this.type = o.get("type").getAsString();
        }catch (NullPointerException|UnsupportedOperationException e){
        }

        try {
            this.poster_path = o.get("poster_path").getAsString();
        }catch (NullPointerException|UnsupportedOperationException e){
        }

        try {
            this.overview = o.get("overview").getAsString();
        }catch (NullPointerException|UnsupportedOperationException e){
        }

        try {
            this.next_episode_to_air = o.get("next_episode_to_air").getAsString();
        }catch (NullPointerException|UnsupportedOperationException e){
        }

        try {

            JsonArray cre = o.get("created_by").getAsJsonArray();
            size = cre.size();
            this.created_by = new Person[size];
            i=0;
            for(JsonElement created_by_elm : cre){
                JsonObject obj = created_by_elm.getAsJsonObject();
                Person p = new Person();
                p.setId(obj.getAsInt());
                p.setAdult(obj.getAsBoolean());
                p.setHomepage(obj.getAsString());
                p.setProfile_path(obj.getAsString());
                p.setBiography(obj.getAsString());
                p.setGender(obj.getAsString());
                p.setImdb_d(obj.getAsString());
                p.setName(obj.getAsString());
                this.created_by[i] = p;
                i++;
            }
        }catch (NullPointerException|UnsupportedOperationException ignore){

        }
        try {
            this.id = o.get("id").getAsInt();
        }catch (NullPointerException|UnsupportedOperationException ignore){ }

        try {
            this.in_production = o.get("in_production").getAsBoolean();
        }catch (NullPointerException|UnsupportedOperationException e){
        }

        try {

            JsonArray eprt = o.get("episode_run_time").getAsJsonArray();
            size = eprt.size();
            this.episode_run_time = new int[size];
            i=0;
            for(JsonElement episode_run_time_elm : eprt){
                JsonObject obj = episode_run_time_elm.getAsJsonObject();
                int j;
                j = obj.getAsInt();
                this.episode_run_time[i] = j;
                i++;
            }
        }catch (NullPointerException|UnsupportedOperationException ignore){
        }

        try {

            JsonArray oc = o.get("origin_country").getAsJsonArray();
            size = oc.size();
            this.origin_country = new String[size];
            i=0;
            for(JsonElement origin_country_elm : oc){
                JsonObject obj = origin_country_elm.getAsJsonObject();
                String j;
                j = obj.getAsString();
                this.origin_country[i] = j;
                i++;
            }
        }catch (NullPointerException|UnsupportedOperationException ignore){
        }

        try {
            this.first_air_date = o.get("first_air_date").getAsString();
        }catch (NullPointerException|UnsupportedOperationException e){

        }
        try {

            JsonArray grs = o.get("genres").getAsJsonArray();
            size = grs.size();
            this.genres = new Genre[size];
            i=0;
            for(JsonElement genre_elm : grs){
                JsonObject obj = genre_elm.getAsJsonObject();
                Genre g = new Genre();
                g.setId(obj.getAsInt());
                g.setName(obj.getAsString());
                this.genres[i] = g;
                i++;
            }
        }catch (NullPointerException|UnsupportedOperationException ignore){

        }

        try {

            JsonArray nt = o.get("networks").getAsJsonArray();
            size = nt.size();
            this.networks = new Network[size];
            i=0;
            for(JsonElement network_elm : nt){
                JsonObject obj = network_elm.getAsJsonObject();
                Network g = new Network();
                g.setId(obj.getAsInt());
                g.setName(obj.getAsString());
                g.setLogo_path(obj.getAsString());
                g.setOrigin_country(obj.getAsString());
                this.networks[i] = g;
                i++;
            }
        }catch (NullPointerException|UnsupportedOperationException ignore){

        }
        try {
            this.homepage = o.get("homepage").getAsString();
        }catch (NullPointerException|UnsupportedOperationException ignore){

        }
        try {

            JsonArray lg = o.get("languages").getAsJsonArray();
            size = lg.size();
            this.languages = new Language[size];
            i=0;
            for(JsonElement language_elm : lg){
                JsonObject obj = language_elm.getAsJsonObject();
                Language l = new Language();
                l.setEnglish_name(obj.getAsString());
                l.setName(obj.getAsString());
                l.setIso_639_1(obj.getAsString());
                this.languages[i] = l;
                i++;
            }
        }catch (NullPointerException|UnsupportedOperationException ignore){

        }

        try {
            this.last_air_date = o.get("last_air_date").getAsString();
        }catch (NullPointerException|UnsupportedOperationException e){
        }












        try {
            this.status = o.get("status").getAsString();
        }catch (NullPointerException|UnsupportedOperationException ignore){ }
        try {
            this.tagline = o.get("tagline").getAsString();
        }catch (NullPointerException|UnsupportedOperationException ignore){ }

        try {
            this.vote_average = o.get("vote_average").getAsFloat();
        }catch (NullPointerException|UnsupportedOperationException ignore){ }
        try {
            this.vote_count = o.get("vote_count").getAsInt();
        }catch (NullPointerException|UnsupportedOperationException ignore){ }















    }


}