package com.example.beans;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Review {
    private int id;
    private String author;
    private Person[] author_details;
    private String content;
    private String created_at;
    private String media_id;
    private String media_title;
    private String media_type;
    private String url;
    private JsonObject obj;

    public Review(JsonObject o) {
        this.obj = o;
        int size = 0;
        int i = 0;

        try {
            this.author = o.get("author").getAsString();
        } catch (NullPointerException | UnsupportedOperationException e) {
            author = "";
        }

        try {
            this.content = o.get("content").getAsString();
        } catch (NullPointerException | UnsupportedOperationException e) {
        }
        try {
            this.created_at = o.get("created_at").getAsString();
        } catch (NullPointerException | UnsupportedOperationException e) {
        }
        try {
            this.media_id = o.get("media_id").getAsString();
        } catch (NullPointerException | UnsupportedOperationException e) {
        }
        try {
            this.media_title = o.get("media_title").getAsString();
        } catch (NullPointerException | UnsupportedOperationException e) {
        }
        try {
            this.media_type = o.get("media_type").getAsString();
        } catch (NullPointerException | UnsupportedOperationException e) {
        }
        try {
            this.url = o.get("url").getAsString();
        } catch (NullPointerException | UnsupportedOperationException e) {
        }
        try {

            JsonArray ad = o.get("author_details").getAsJsonArray();
            size = ad.size();
            this.author_details = new Person[size];
            i=0;
            for(JsonElement author_details_elm : ad){
                JsonObject obj = author_details_elm.getAsJsonObject();
                Person p = new Person();
                p.setId(obj.getAsInt());
                p.setAdult(obj.getAsBoolean());
                p.setHomepage(obj.getAsString());
                p.setProfile_path(obj.getAsString());
                p.setBiography(obj.getAsString());
                p.setGender(obj.getAsString());
                p.setImdb_d(obj.getAsString());
                p.setName(obj.getAsString());
                this.author_details[i] = p;
                i++;
            }
        }catch (NullPointerException|UnsupportedOperationException ignore){
        }

        try {
            this.id = o.get("id").getAsInt();
        } catch (NullPointerException | UnsupportedOperationException e) {
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Person[] getAuthor_details() {
        return author_details;
    }

    public void setAuthor_details(Person[] author_details) {
        this.author_details = author_details;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    public String getMedia_title() {
        return media_title;
    }

    public void setMedia_title(String media_title) {
        this.media_title = media_title;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public JsonObject getObj() {
        return obj;
    }

    public void setObj(JsonObject obj) {
        this.obj = obj;
    }

}