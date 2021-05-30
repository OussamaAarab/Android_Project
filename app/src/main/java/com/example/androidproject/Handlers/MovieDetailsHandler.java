package com.example.androidproject.Handlers;


import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.androidproject.Fragment.MovieDetails;


import com.example.androidproject.HomeAdapter.VideoAdapter;

import com.example.androidproject.HomeAdapter.AdapterMovies;

import com.example.beans.Movie;


import java.util.ArrayList;
import java.util.HashMap;


public class MovieDetailsHandler extends Handler {
    public static final int MSG_DETAILS = 1,MSG_SIMILAR=2;

    @Override
    public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
        if(msg.arg1==MSG_DETAILS) {
            HashMap<String, Object> objMovie = (HashMap<String, Object>) msg.obj;
            MovieDetails movieDetails = (MovieDetails) objMovie.get("movieDetails");
            Movie movie = (Movie) objMovie.get("movie");
            VideoAdapter adapter = (VideoAdapter) objMovie.get("video_adapter");
            adapter.setData(movie.getVideos());

            movieDetails.setData(movie);
        }
        if (msg.arg1==MSG_SIMILAR)
        {
            HashMap<String, Object> objMovie = (HashMap<String, Object>) msg.obj;
            ArrayList<Movie> movies=(ArrayList<Movie> )objMovie.get("movies");
            MovieDetails movieDetails = (MovieDetails) objMovie.get("movieDetails");
            AdapterMovies adapterMovies=(AdapterMovies) objMovie.get("adapterMovies");
            adapterMovies.SetData(movies);
            if(movies.isEmpty())
            movieDetails.similarMovie(false);



        }




    }
}
