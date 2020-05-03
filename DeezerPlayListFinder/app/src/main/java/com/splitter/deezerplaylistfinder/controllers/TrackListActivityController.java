package com.splitter.deezerplaylistfinder.controllers;

import android.content.Intent;
import android.view.View;
import com.splitter.deezerplaylistfinder.R;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.splitter.deezerplaylistfinder.adapters.TrackListAdapter;
import com.splitter.deezerplaylistfinder.model.Playlist;
import com.splitter.deezerplaylistfinder.model.Track;
import com.splitter.deezerplaylistfinder.util.Constants;
import com.splitter.deezerplaylistfinder.util.HTTPSWebUtilDomi;
import com.splitter.deezerplaylistfinder.view.TrackActivity;
import com.splitter.deezerplaylistfinder.view.TrackListActivity;

import java.io.IOException;
import java.util.ArrayList;

public class TrackListActivityController implements View.OnClickListener , HTTPSWebUtilDomi.OnResponseListener{

    private TrackListActivity activity;
    private HTTPSWebUtilDomi handlerHTTPS;
    private ArrayList<Track> trackList;
    private TrackListAdapter trackListAdapter;
    private Playlist playlist;

    public TrackListActivityController(TrackListActivity activity) {
        this.activity = activity;

        handlerHTTPS = new HTTPSWebUtilDomi();
        handlerHTTPS.setListener(this);

        activity.getRvTrackList().setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        activity.getRvTrackList().setLayoutManager(linearLayoutManager);

        activity.getBtnBackTrackList().setOnClickListener(this);

        trackList = new ArrayList<Track>();
        trackListAdapter = new TrackListAdapter(trackList);
        activity.getRvTrackList().setAdapter(trackListAdapter);
        activity.getRvTrackList().addItemDecoration(new DividerItemDecoration(activity.getRvTrackList().getContext(),
                DividerItemDecoration.VERTICAL));

        trackListAdapter.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = activity.getRvTrackList().getChildAdapterPosition(v);
                long trackId = ((Track)(trackListAdapter.getItem(position))).getId();
                Intent i = new Intent(activity, TrackActivity.class);
                i.putExtra("trackId", trackId);
                activity.startActivity(i);

            }
        });

        loadPlaylistData();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_backTrackList:
                activity.onBackPressed();
                break;
        }

    }

    public void loadPlaylistData()
    {
        long playlistId=(long)activity.getIntent().getExtras().get("playlistId");
        String url="https://api.deezer.com/playlist/"+playlistId;
        new Thread(
                () -> {
                    handlerHTTPS.GETrequest(Constants.SELECTED_PLAYLIST_SEARCH_CALLBACK,url);
                }
        ).start();
    }

    @Override
    public void onResponse(int callbackID, String response) throws IOException {
        switch(callbackID)
        {
            case Constants.SELECTED_PLAYLIST_SEARCH_CALLBACK:
            {
                Gson g = new Gson();

                playlist = g.fromJson(response, Playlist.class);
                activity.runOnUiThread(()->{
                    activity.getTvPlayListName().setText(playlist.getTitle());
                    activity.getTvPlaylistDescription().setText(playlist.getDescription());
                    if(playlist.getDescription().equals(("")))
                    {
                        activity.getTvPlaylistDescription().setText("There is not description in this playlist");
                    }
                    activity.getTvNumTracks().setText("Songs: "+playlist.getNb_tracks()+"   Fans: "+playlist.getFans());
                    Glide.with(activity).load(playlist.getPicture()).centerCrop().into(activity.getIvPlayList());
                });
                loadTracks();
                break;
            }

            case Constants.TRACKLIST_SEARCH_CALLBACK:
                Gson g = new Gson();
                Track track = g.fromJson(response, Track.class);
                activity.runOnUiThread(
                        () ->
                        {
                            trackListAdapter.addData(track);
                        }
                );
                break;

            default:
                throw new IllegalStateException("Unexpected error: " + callbackID);
        }
    }

    public void loadTracks(){
        ArrayList<Track> tracks = playlist.getTracks().getData();
        String url="https://api.deezer.com/track/";
        for(Track currentTrack : tracks){
            handlerHTTPS.GETrequest( Constants.TRACKLIST_SEARCH_CALLBACK,url+""+currentTrack.getId());
        }
    }
}
