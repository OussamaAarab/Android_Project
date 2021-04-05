/*package com.example.androidproject;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

public class MovieAdapter  extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{
    ArrayList<Movie> movies = new ArrayList<>();
    Context context;
    MovieClicked movieClicked;

    interface MovieClicked {
        public void onMovieClicked(int index);
    }

    public MovieAdapter(@NonNull Context context,ArrayList<Movie> list) {
        movies=list;
        this.context=context;
        movieClicked=(MovieClicked)context;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imMovie;
        TextView title, description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imMovie=itemView.findViewById(R.id.imMovie);
            title=itemView.findViewById(R.id.title);
            description=itemView.findViewById(R.id.description);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    movieClicked.onMovieClicked(movies.indexOf((Movie) v.getTag()));
                }
            });
        }
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_movie,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, int position) {
        String s=movies.get(position).getYear()+" "+movies.get(position).getType();

        holder.itemView.setTag(movies.get(position));
        Glide.with(context)
                .load(movies.get(position).getUrlImage())
                .into(holder.imMovie);
        holder.title.setText(movies.get(position).getTitle());
        holder.description.setText(s);
    }

    @Override
    public int getItemCount()
    {
        return movies.size();
    }

}*/
