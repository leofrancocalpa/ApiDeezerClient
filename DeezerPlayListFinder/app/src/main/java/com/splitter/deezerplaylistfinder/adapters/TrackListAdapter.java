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
import com.splitter.deezerplaylistfinder.model.Track;

import java.util.ArrayList;

public class TrackListAdapter extends RecyclerView.Adapter<TrackListAdapter.ViewHolder>  {

    private ArrayList<Track> trackList;
    private View.OnClickListener mClickListener;

    public TrackListAdapter(ArrayList<Track> trackList) {
        this.trackList = trackList;
    }

    public void setClickListener(View.OnClickListener callback) {
        mClickListener = (View.OnClickListener) callback;
    }

    public void setData(ArrayList<Track> trackList) {
        this.trackList = trackList;
        notifyDataSetChanged();
    }

    public void addData(Track track){
        this.trackList.add(track);
        notifyDataSetChanged();
    }

    public Track getItem(int position){
        return trackList.get(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tracklist, parent, false);
        ViewHolder holder = new ViewHolder(v);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onClick(view);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.fillData(trackList.get(position));
    }

    @Override
    public int getItemCount() {
        return trackList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvTrackName, tvArtistTrack, tvTrackReleaseDate;
        private ImageView ivTrackImg;
        private View itemView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvTrackName = itemView.findViewById(R.id.tv_trackNameItem);
            tvArtistTrack = itemView.findViewById(R.id.tv_artisTracksNameItem);
            tvTrackReleaseDate = itemView.findViewById(R.id.tv_trackReleaseDateItem);
            ivTrackImg = itemView.findViewById(R.id.iv_trackItem);
            itemView.setTag(this);
        }

        public void fillData(Track track) {
            tvTrackName.setText(track.getTitle());
            tvArtistTrack.setText(track.getArtist().getName());
            tvTrackReleaseDate.setText(track.getRelease_date().toString().split(" ")[5]);
            Glide.with(itemView).load(track.getAlbum().getCover()).centerCrop().into(ivTrackImg);
        }
    }


}
