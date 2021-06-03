package com.GLA_12.androidproject.HomeAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.GLA_12.androidproject.R;

import com.GLA_12.beans.Movie;

import java.util.ArrayList;

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
            imMovie=itemView.findViewById(R.id.movie_search_image);
            title=itemView.findViewById(R.id.title);
            description=itemView.findViewById(R.id.movie_search_desc);
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
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_movie_search,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, int position) {
        /*
        String s=movies.get(position).getYear()+" "+movies.get(position).getType();
        holder.itemView.setTag(movies.get(position));
        Glide.with(context)
                .load(movies.get(position).getUrlImage())
                .into(holder.imMovie);
        holder.title.setText(movies.get(position).getTitle());
        holder.description.setText(s);
        */

    }

    @Override
    public int getItemCount()
    {
        return movies.size();
    }

}