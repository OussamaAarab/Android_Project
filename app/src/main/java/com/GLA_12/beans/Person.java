package com.GLA_12.beans;

public class Person {
    private int id;
    private String name;
    private String gender;
    private String biography;
    private String profile_path;
    private String imdb_d;
    private String homepage;
    private boolean adult;

    /*public Person(int id, String name, String gender, String biography, String profile_path, String imdb_d, String homepage, boolean adult) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.biography = biography;
        this.profile_path = profile_path;
        this.imdb_d = imdb_d;
        this.homepage = homepage;
        this.adult = adult;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }

    public String getImdb_d() {
        return imdb_d;
    }

    public void setImdb_d(String imdb_d) {
        this.imdb_d = imdb_d;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }
}