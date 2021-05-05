package com.example.androidproject.HomeAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproject.R;
import com.example.beans.Genre;
import com.example.beans.Movie;

import java.util.ArrayList;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.ViewHolder>{
    ArrayList<Genre> genres = new ArrayList<>();
    Context context;
    MovieClicked movieClicked;

    interface MovieClicked {
        public void onMovieClicked(int index);
    }

    public GenreAdapter(@NonNull Context context, ArrayList<Genre> list) {
        genres=list;
        this.context=context;

    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title=itemView.findViewById(R.id.mv_dt_gen);


        }
    }

    @NonNull
    @Override
    public GenreAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_genre,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GenreAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(genres.get(position));
        holder.title.setText(genres.get(position).getName());

    }

    @Override
    public int getItemCount()
    {
        return genres.size();
    }

}