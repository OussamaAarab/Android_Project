package com.example.androidproject.searchAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproject.R;

import java.util.ArrayList;

import com.example.api.API_Factory;
import com.example.beans.*;
import com.squareup.picasso.Picasso;

public class MovieSearchAdapter extends RecyclerView.Adapter<MovieSearchAdapter.ViewHolder>{
    ArrayList<Movie> movies = new ArrayList<>();
    Context context;
    MovieClicked movieClicked;

    interface MovieClicked {
        public void onMovieClicked(int index);
    }

    public MovieSearchAdapter(ArrayList<Movie> list) {
        movies=list;

    }

    public MovieSearchAdapter(@NonNull Context context, ArrayList<Movie> list) {
        movies=list;
        this.context=context;
//        movieClicked=(MovieClicked)context;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imMovie;
        TextView title, description,date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imMovie=itemView.findViewById(R.id.movie_search_image);
            title=itemView.findViewById(R.id.movie_search_title);
            description=itemView.findViewById(R.id.movie_search_desc);
            date=itemView.findViewById(R.id.movie_search_date);
/*
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    movieClicked.onMovieClicked(movies.indexOf((Movie) v.getTag()));
                }
            });

 */
        }


    }

    @NonNull
    @Override
    public MovieSearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_movie_search,parent,false);

    return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieSearchAdapter.ViewHolder holder, int position) {

        holder.itemView.setTag(movies.get(position));

        holder.title.setText(movies.get(position).getTitle());

        Movie movie = movies.get(position);

        holder.title.setText(movie.getTitle());
        holder.description.setText(movie.getOverview());
        holder.date.setText(movie.getRelease_date());
        String linkImage = "";
        try {
            linkImage = API_Factory.getInstance(holder.imMovie.getContext()).getImage_source() + "w500" + movie.getPoster_path();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Picasso.get().load(linkImage).into(holder.imMovie);
        holder.title.setText(movie.getTitle());

    }

    @Override
    public int getItemCount()
    {
        return movies.size();
    }

    public void SetData(ArrayList<Movie> movies) {
        this.movies = movies;
        this.notifyDataSetChanged();

    }

}
