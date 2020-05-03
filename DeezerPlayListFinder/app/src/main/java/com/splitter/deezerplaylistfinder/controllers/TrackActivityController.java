package com.splitter.deezerplaylistfinder.controllers;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import com.splitter.deezerplaylistfinder.R;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.splitter.deezerplaylistfinder.model.Track;
import com.splitter.deezerplaylistfinder.util.Constants;
import com.splitter.deezerplaylistfinder.util.HTTPSWebUtilDomi;
import com.splitter.deezerplaylistfinder.view.TrackActivity;

import java.io.IOException;

public class TrackActivityController implements View.OnClickListener, HTTPSWebUtilDomi.OnResponseListener {

    private TrackActivity activity;
    private HTTPSWebUtilDomi handlerHTTPS;
    private Track track;

    public TrackActivityController(TrackActivity activity) {
        this.activity = activity;
        handlerHTTPS = new HTTPSWebUtilDomi();
        handlerHTTPS.setListener(this);
        loadTrack();
        activity.getBtnListenOnDeezer().setOnClickListener(this);
        activity.getBtnBackTrackView().setOnClickListener(this);
    }

    private void loadTrack() {
        long trackId = (long)activity.getIntent().getExtras().get("trackId");
        String url="https://api.deezer.com/track/"+trackId;
        new Thread(
                () -> {
                    handlerHTTPS.GETrequest(Constants.TRACKDETAILS_CALLBACK,url);
                }
        ).start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_listenOnDeezer:
            {
                Intent i = this.activity.getPackageManager().getLaunchIntentForPackage("deezer.android.app");
                Uri link = Uri.parse(track.getLink());
                if(i != null)
                {
                    i.setData(link);
                    activity.startActivity(i);
                }else
                {
                    Intent intent=new Intent(Intent.ACTION_VIEW);
                    intent.setData(link);
                    activity.startActivity(intent);
                }
                break;
            }
            case R.id.btn_backTrackAcitivy:
            {
                this.activity.onBackPressed();
                break;
            }
        }

    }

    @Override
    public void onResponse(int callbackID, String response) throws IOException {
        switch (callbackID) {
            case Constants.TRACKDETAILS_CALLBACK: {
                Gson g = new Gson();
                track = g.fromJson(response, Track.class);
                int dur = track.getDuration();
                int minutes = dur / 60;
                int seconds = dur - (minutes * 60);
                String timeString = String.format("%02d:%02d", minutes, seconds);
                activity.runOnUiThread(
                        () -> {
                            activity.getTvAlbumTrack().setText("Album: " + track.getAlbum().getTitle());
                            activity.getTvNameTrack().setText(track.getTitle());
                            activity.getTvArtistTrack().setText("Artist: " + track.getArtist().getName());

                            activity.getTvTrackDuration().setText(timeString);
                            Glide.with(activity).load(track.getAlbum().getCover()).centerCrop().into(activity.getIvImageTrack());
                        }

                );
            }
        }
    }

}
