package com.example.androidproject.Handlers;


import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.androidproject.Fragment.MovieDetails;
import com.example.androidproject.Fragment.SearchFragment;
import com.example.beans.Movie;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class MovieDetailsHandler extends Handler {

    @Override
    public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);

            HashMap<String, Object> objMovie = (HashMap<String, Object>) msg.obj;
            MovieDetails movieDetails=(MovieDetails)objMovie.get("movieDetails") ;
            Movie movie=(Movie)objMovie.get("movie");
            movieDetails.setData(movie);



    }
}
