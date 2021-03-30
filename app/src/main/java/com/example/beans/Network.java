package com.example.beans;

import com.google.gson.JsonObject;

public class Network {

    private String headquarters;
    private int id;
    private String homepage;
    private String logo_path;
    private String name;
    private String origin_country;

    public Network(JsonObject o){
        try {
            this.homepage = o.get("homepage").getAsString();
        }catch (NullPointerException|UnsupportedOperationException e){
            homepage="";
        }
        try {
            this.id = o.get("id").getAsInt();
        }catch (NullPointerException|UnsupportedOperationException ignore){ }
        try {
            this.logo_path = o.get("logo_path").getAsString();
        }catch (NullPointerException ignore){ }
        try {
            this.name = o.get("name").getAsString();
        }catch (NullPointerException|UnsupportedOperationException ignore){ }

        try {
            this.origin_country = o.get("origin_country").getAsString();
        }catch (NullPointerException|UnsupportedOperationException ignore){ }

        try {
            this.headquarters = o.get("headquarter").getAsString();
        }catch (NullPointerException|UnsupportedOperationException ignore){ }

    }

    public String getHeadquarters() {
        return headquarters;
    }

    public int getId() {
        return id;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getLogo_path() {
        return logo_path;
    }

    public String getName() {
        return name;
    }

    public String getOrigin_country() {
        return origin_country;
    }

    public void setHeadquarters(String headquarters) {
        this.headquarters = headquarters;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public void setLogo_path(String logo_path) {
        this.logo_path = logo_path;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrigin_country(String origin_country) {
        this.origin_country = origin_country;
    }

}

