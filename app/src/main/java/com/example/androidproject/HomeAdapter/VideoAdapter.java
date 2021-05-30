package com.example.androidproject.HomeAdapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproject.Fragment.VideoPlayerActivity;
import com.example.androidproject.R;
import com.example.beans.Video;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.FeaturedViewHolder> {
    ArrayList<Video> videos;
    Context context;


    public VideoAdapter(ArrayList<Video> videos){this.videos = videos;}

    @NonNull
    @Override
    public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_card, parent, false);
        FeaturedViewHolder featuredViewHolder = new FeaturedViewHolder(view);
        Log.d(getClass().getName(),"Recycler View Created "+videos.size());
        return featuredViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedViewHolder holder, int position) {
        holder.title.setText(videos.get(position).getName());
        Picasso.get().load("https://img.youtube.com/vi/"+videos.get(position).getKey()+"/hqdefault.jpg").into(holder.image);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener(v.getContext(),position);
            }
        });
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener(v.getContext(),position);
            }
        });
    }
    public void setData(ArrayList<Video> video){

        this.videos.addAll(video);
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public static class FeaturedViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title;
        FloatingActionButton btn;


        public FeaturedViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.video_img);
            title = itemView.findViewById(R.id.video_title);

            btn = itemView.findViewById(R.id.VideofloatingActionButton);

        }
    }
    void onClickListener(Context context, int position){
        String key = videos.get(position).getKey();
        Intent intent = new Intent(context, VideoPlayerActivity.class);
        intent.putExtra("key",key);
        context.startActivity(intent);

    }
}
