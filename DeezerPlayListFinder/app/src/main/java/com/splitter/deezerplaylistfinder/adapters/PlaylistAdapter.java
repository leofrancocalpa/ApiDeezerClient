package com.splitter.deezerplaylistfinder.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.splitter.deezerplaylistfinder.R;
import com.splitter.deezerplaylistfinder.model.Playlist;

import java.util.ArrayList;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder> {

    private ArrayList<Playlist> playlists;
    private View.OnClickListener mClickListener;


    public PlaylistAdapter(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }

    public void setClickListener(View.OnClickListener callback) {
        mClickListener = (View.OnClickListener) callback;
    }


    public void setData(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_playlist, parent, false);
        ViewHolder holder = new ViewHolder(v);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onClick(view);
            }
        });
        return holder;
    }

    public Playlist getItem(int position) {
        return playlists.get(position);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.fillData(playlists.get(position));
    }

    @Override
    public int getItemCount() {

        return playlists.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvPlaylistName, tvPlaylistUser, tvPlaylistNbTracks;
        private ImageView ivPlaylistItem;
        private View view;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            tvPlaylistName = view.findViewById(R.id.tv_playlistItemName);
            tvPlaylistUser = view.findViewById(R.id.tv_userNamePlaylistItem);
            tvPlaylistNbTracks = view.findViewById(R.id.tv_playlistItemNumTracks);
            ivPlaylistItem = view.findViewById(R.id.iv_playlistItem);
            view.setTag(this);

        }


        public void fillData(Playlist playlist) {
            tvPlaylistName.setText(playlist.getTitle());
            tvPlaylistUser.setText(playlist.getUser().getName());
            tvPlaylistNbTracks.setText(playlist.getNb_tracks() + "");
            Glide.with(view).load(playlist.getPicture()).centerCrop().into(ivPlaylistItem);

        }

    }

}