package com.splitter.deezerplaylistfinder.model;

import java.util.ArrayList;

public class TracksData {

    private ArrayList<Track> data;

    public TracksData() {
    }

    public TracksData(ArrayList<Track> data) {
        this.data = data;
    }

    public ArrayList<Track> getData() {
        return data;
    }

    public void setData(ArrayList<Track> data) {
        this.data = data;
    }
}
