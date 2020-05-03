package com.splitter.deezerplaylistfinder.model;

import java.util.ArrayList;

public class PlaylistData {

    //private Playlist[] data;
    private ArrayList<Playlist> data;
    private int total;

    public PlaylistData() {
    }

    public PlaylistData(ArrayList<Playlist> data, int total) {
        this.data = data;
        this.total = total;
    }

    public ArrayList<Playlist> getData() {
        return data;
    }

    public void setData(ArrayList<Playlist> data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
