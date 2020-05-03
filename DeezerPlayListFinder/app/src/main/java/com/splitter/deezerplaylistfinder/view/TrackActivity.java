package com.splitter.deezerplaylistfinder.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.splitter.deezerplaylistfinder.R;
import com.splitter.deezerplaylistfinder.controllers.TrackActivityController;

public class TrackActivity extends AppCompatActivity {

    private ImageView ivImageTrack;
    private TextView tvNameTrack;
    private TextView tvArtistTrack;
    private TextView tvAlbumTrack;
    private TextView  tvTrackDuration;
    private Button btnListenOnDeezer;
    private ImageButton btnBackTrackView;

    private TrackActivityController trackActivityController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);
        ivImageTrack = findViewById(R.id.iv_imageTrack);
        tvNameTrack = findViewById(R.id.tv_nameTrack);
        tvArtistTrack = findViewById(R.id.tv_artistTrack);
        tvAlbumTrack = findViewById(R.id.tv_albumTrack);
        tvTrackDuration = findViewById(R.id.tv_trackDuration);
        btnListenOnDeezer = findViewById(R.id.btn_listenOnDeezer);
        btnBackTrackView = findViewById(R.id.btn_backTrackAcitivy);

        trackActivityController = new TrackActivityController(this);
    }

    public ImageView getIvImageTrack() {
        return ivImageTrack;
    }

    public TextView getTvNameTrack() {
        return tvNameTrack;
    }

    public TextView getTvArtistTrack() {
        return tvArtistTrack;
    }

    public TextView getTvAlbumTrack() {
        return tvAlbumTrack;
    }

    public TextView getTvTrackDuration() {
        return tvTrackDuration;
    }

    public Button getBtnListenOnDeezer() {
        return btnListenOnDeezer;
    }

    public TrackActivityController getTrackActivityController() {
        return trackActivityController;
    }

    public ImageButton getBtnBackTrackView() {
        return btnBackTrackView;
    }
}
