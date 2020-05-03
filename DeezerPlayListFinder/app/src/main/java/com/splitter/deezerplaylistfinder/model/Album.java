package com.splitter.deezerplaylistfinder.model;

public class Album {
    private long id;
    private String cover;
    private String title;

    public Album() {
    }

    public Album(long id, String cover, String title) {
        this.id = id;
        this.cover = cover;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
