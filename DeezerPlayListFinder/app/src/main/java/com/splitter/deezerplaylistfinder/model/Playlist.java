package com.splitter.deezerplaylistfinder.model;

public class Playlist {

    private long id;
    private String title;
    private int nb_tracks;
    private String picture;
    private String description;
    private TracksData tracks;
    private User user;
    private int fans;

    public Playlist() {
    }

    public Playlist(long id, String title, int nb_tracks, String picture, String description, TracksData tracks, User user, int fans) {
        this.id = id;
        this.title = title;
        this.nb_tracks = nb_tracks;
        this.picture = picture;
        this.description = description;
        this.tracks = tracks;
        this.user = user;
        this.fans = fans;
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

    public int getNb_tracks() {
        return nb_tracks;
    }

    public void setNb_tracks(int nb_tracks) {
        this.nb_tracks = nb_tracks;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TracksData getTracks() {
        return tracks;
    }

    public void setTracks(TracksData tracks) {
        this.tracks = tracks;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getFans() {
        return fans;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }
}
