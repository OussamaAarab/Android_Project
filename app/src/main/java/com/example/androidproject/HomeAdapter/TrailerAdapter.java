package com.example.androidproject.HomeAdapter;

import android.content.Context;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproject.Handlers.MovieHandler;
import com.example.androidproject.R;
import com.example.api.API_Factory;
import com.example.api.API_Movie;
import com.example.beans.Movie;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.FeaturedViewHolder> {

    List<Movie> movies;
    Context context;
    MovieHandler handler;

    public  TrailerAdapter(Context context,List<Movie> movies){
        this.context = context;
        this.movies = movies;
        handler = new MovieHandler(context);
    }

    @NonNull
    @Override
    public TrailerAdapter.FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_card, parent, false);
        TrailerAdapter.FeaturedViewHolder featuredViewHolder = new TrailerAdapter.FeaturedViewHolder(view);

        Log.d(getClass().getName(),"Recycler View Created ");
        return featuredViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedViewHolder holder, int position) {

        String linkImage = "";
        try {
            linkImage = API_Factory.getInstance(holder.image.getContext()).getImage_source() + "w780" + movies.get(position).getBackdrop_path();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Picasso.get().load(linkImage).into(holder.image);
        holder.title.setText(movies.get(position).getTitle());

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClicked(position);
            }
        });

        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClicked(position);
            }
        });
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClicked(position);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClicked(position);
            }
        });


    }


    @Override
    public int getItemCount() {
        return movies.size();
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
    public void setData(List<Movie> movies){
        this.movies.clear();
        this.movies = movies;
        this.notifyDataSetChanged();
        Log.d(getClass().getName(),"DataSet updated !!! ");
    }

    public void onItemClicked(int position){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String key = API_Factory.getInstance(context).getAPI_Movie().getTrailerKey(movies.get(position).getId());
                    Message message = new Message();
                    message.arg1 = SlideAdapter.MSG_SLIDE_CLICK;
                    message.obj = key;
                    handler.sendMessage(message);


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
