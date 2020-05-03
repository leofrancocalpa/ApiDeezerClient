package com.splitter.deezerplaylistfinder.controllers;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.splitter.deezerplaylistfinder.R;
import com.splitter.deezerplaylistfinder.adapters.PlaylistAdapter;
import com.splitter.deezerplaylistfinder.model.Playlist;
import com.splitter.deezerplaylistfinder.model.PlaylistData;
import com.splitter.deezerplaylistfinder.util.Constants;
import com.splitter.deezerplaylistfinder.util.HTTPSWebUtilDomi;
import com.splitter.deezerplaylistfinder.view.MainActivity;
import com.splitter.deezerplaylistfinder.view.TrackListActivity;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivityController implements View.OnClickListener , HTTPSWebUtilDomi.OnResponseListener{

    private MainActivity mainActivity;
    private HTTPSWebUtilDomi handlerHTTPS;
    private PlaylistAdapter playlistAdapter;
    private ArrayList<Playlist> playlist;

    public MainActivityController(final MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        mainActivity.getSearchButton().setOnClickListener(this);
        handlerHTTPS = new HTTPSWebUtilDomi();
        handlerHTTPS.setListener(this);

        mainActivity.getRvPlayList().setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mainActivity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mainActivity.getRvPlayList().setLayoutManager(linearLayoutManager);

        playlist = new ArrayList<Playlist>();//Playlist[25];
        playlistAdapter = new PlaylistAdapter(playlist);
        mainActivity.getRvPlayList().setAdapter(playlistAdapter);
        mainActivity.getRvPlayList().addItemDecoration(new DividerItemDecoration(mainActivity.getRvPlayList().getContext(),
                DividerItemDecoration.VERTICAL));

        playlistAdapter.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position =mainActivity.getRvPlayList().getChildAdapterPosition(v);
                long playlistId  = ((Playlist)(playlistAdapter.getItem(position))).getId();
                Intent i = new Intent(mainActivity, TrackListActivity.class);
                i.putExtra("playlistId", playlistId);
                mainActivity.startActivity(i);

            }
        });

        onStart();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.searchButton:
                final String playlistName = mainActivity.getTvPlayListName().getText().toString();
                if(playlistName.equals("")){

                }else{
                    String url="https://api.deezer.com/search/playlist/?q="+playlistName+"";
                    new Thread(
                            () -> {
                                handlerHTTPS.GETrequest(Constants.PLAYLIST_SEARCH_CALLBACK,url);
                            }
                    ).start();
                }
                break;
        }
    }

    @Override
    public void onResponse(int callbackID, String response) throws IOException {
        switch (callbackID) {
            case Constants.PLAYLIST_SEARCH_CALLBACK:
                Gson gson = new Gson();
                PlaylistData playlistResponse = gson.fromJson(response, PlaylistData.class);
                playlist = playlistResponse.getData();
                mainActivity.runOnUiThread(
                        ()->
                        {
                            playlistAdapter.setData(playlist);
                            playlistAdapter.notifyDataSetChanged();
                        }
                );
            break;
        }
    }

    public void onStart(){
        String url="https://api.deezer.com/search/playlist/?q="+"deezer";
        new Thread(
                () -> {
                    handlerHTTPS.GETrequest(Constants.PLAYLIST_SEARCH_CALLBACK,url);
                }
        ).start();
    }
}
