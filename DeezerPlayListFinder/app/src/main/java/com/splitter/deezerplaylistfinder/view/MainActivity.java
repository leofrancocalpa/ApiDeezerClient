package com.splitter.deezerplaylistfinder.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import com.splitter.deezerplaylistfinder.R;
import com.splitter.deezerplaylistfinder.controllers.MainActivityController;

public class MainActivity extends AppCompatActivity {

    private final int REQUEST_CODE_PERMISSION=111;

    private EditText tvPlayListName;
    private ImageButton searchButton;
    private RecyclerView rvPlayList;
    private MainActivityController mainActivityController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvPlayListName = findViewById(R.id.tv_playlistNameInput);
        searchButton = findViewById(R.id.searchButton);
        rvPlayList = findViewById(R.id.rv_playList);

        allowPermissions();

        mainActivityController = new MainActivityController(this);
    }

    private void allowPermissions(){
        int internetPermission = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.INTERNET);
        if(internetPermission!= PackageManager.PERMISSION_GRANTED){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.INTERNET}, REQUEST_CODE_PERMISSION);
            }
        }
    }

    public EditText getTvPlayListName() {
        return tvPlayListName;
    }

    public ImageButton getSearchButton() {
        return searchButton;
    }

    public RecyclerView getRvPlayList() {
        return rvPlayList;
    }
}
