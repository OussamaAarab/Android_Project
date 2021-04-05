package com.example.androidproject.HomeAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproject.R;
import com.example.api.API_Factory;
import com.example.beans.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterMovies extends RecyclerView.Adapter<AdapterMovies.FeaturedViewHolder> {

    ArrayList<Movie> movies;

    public AdapterMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public void SetData(ArrayList<Movie> movies) {
        this.movies = movies;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_design, parent, false);
        FeaturedViewHolder featuredViewHolder = new FeaturedViewHolder(view);
        return featuredViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedViewHolder holder, int position) {

        Movie movie = movies.get(position);
        String linkImage = "";
        try {
            linkImage = API_Factory.getInstance(holder.image.getContext()).getImage_source() + "w500" + movie.getPoster_path();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Picasso.get().load(linkImage).into(holder.image);
        holder.title.setText(movie.getTitle());
        holder.desc.setText(movie.getOverview());

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class FeaturedViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title,desc;

        public FeaturedViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.card_image);
            title = itemView.findViewById(R.id.card_title);
            desc = itemView.findViewById(R.id.card_desc);
        }
    }

}