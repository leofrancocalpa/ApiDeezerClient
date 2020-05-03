package com.splitter.deezerplaylistfinder.model;

public class Artist {

    private long id;
    private String name;
    private  String picture;

    public Artist() {
    }

    public Artist(long id, String name, String picture) {
        this.id = id;
        this.name = name;
        this.picture = picture;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
