package com.example.beans;

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

    public Review(int id, String author, Person[] author_details, String content, String created_at, String media_id, String media_title, String media_type, String url) {
        this.id = id;
        this.author = author;
        this.author_details = author_details;
        this.content = content;
        this.created_at = created_at;
        this.media_id = media_id;
        this.media_title = media_title;
        this.media_type = media_type;
        this.url = url;
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
}