package com.GLA_12.androidproject.Handlers;


import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.GLA_12.androidproject.Fragment.MovieDetails;


import com.GLA_12.androidproject.HomeAdapter.VideoAdapter;

import com.GLA_12.androidproject.HomeAdapter.AdapterMovies;

import com.GLA_12.beans.Movie;


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
