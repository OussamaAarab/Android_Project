package com.GLA_12.beans;

public class Company {
    private int id;
    private String logo_path;
    private String name;
    private String origin_country;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getLogo_path() {
        return logo_path;
    }

    public String getOrigin_country() {
        return origin_country;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogo_path(String logo_path) {
        this.logo_path = logo_path;
    }

    public void setOrigin_country(String origin_country) {
        this.origin_country = origin_country;
    }
}
