package com.splitter.deezerplaylistfinder.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.splitter.deezerplaylistfinder.R;
import com.splitter.deezerplaylistfinder.controllers.TrackListActivityController;

public class TrackListActivity extends AppCompatActivity {


    private ImageButton btnBackTrackList;
    private ImageView ivPlayList;
    private TextView tvPlayListName;
    private TextView tvPlaylistDescription;
    private TextView tvNumTracks;
    private RecyclerView rvTrackList;

    private TrackListActivityController trackListActivityController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_list);
        btnBackTrackList = findViewById(R.id.btn_backTrackList);
        ivPlayList = findViewById(R.id.iv_playlist);
        tvPlayListName = findViewById(R.id.tv_playlistName);
        tvPlaylistDescription = findViewById(R.id.tv_playlistDescription);
        tvNumTracks = findViewById(R.id.tv_playlistNumTracks);
        rvTrackList = findViewById(R.id.rv_trackList);

        trackListActivityController = new TrackListActivityController(this);

    }

    public ImageButton getBtnBackTrackList() {
        return btnBackTrackList;
    }

    public ImageView getIvPlayList() {
        return ivPlayList;
    }

    public TextView getTvPlayListName() {
        return tvPlayListName;
    }

    public TextView getTvPlaylistDescription() {
        return tvPlaylistDescription;
    }

    public TextView getTvNumTracks() {
        return tvNumTracks;
    }

    public RecyclerView getRvTrackList() {
        return rvTrackList;
    }

    public TrackListActivityController getTrackListActivityController() {
        return trackListActivityController;
    }
}
