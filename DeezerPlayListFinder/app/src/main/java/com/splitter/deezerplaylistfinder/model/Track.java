package com.splitter.deezerplaylistfinder.model;

import java.util.Date;

public class Track {

    private long id;
    private String title;
    private Artist artist;
    private Date release_date;
    private Album album;
    private int duration;
    private String link;

    public Track() {
    }

    public Track(long id, String title, Artist artist, Date release_date, Album album, int duration, String link) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.release_date = release_date;
        this.album = album;
        this.duration = duration;
        this.link = link;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
