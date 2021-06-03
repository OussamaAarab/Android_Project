package com.GLA_12.androidproject.Handlers;


import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;


import com.GLA_12.androidproject.HomeAdapter.MovieSearchAdapter;

import com.GLA_12.beans.Movie;

import java.util.ArrayList;
import java.util.HashMap;

public class MovieSearchHandler extends Handler {
    public static final int MSG_SEARCH = 1,MSG_POPULAR=3;

    @Override
    public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
        {
            if (msg.arg1 == MSG_SEARCH) {
                HashMap<String, Object> objMovie = (HashMap<String, Object>) msg.obj;
                MovieSearchAdapter adapter = (MovieSearchAdapter) objMovie.get("MovieSearchAdapter");
                ArrayList<Movie> movies = (ArrayList<Movie>) objMovie.get("MovieSearch");
                String requestedMovie = (String) objMovie.get("requestedMovie");
                adapter.SetData(movies);
                adapter.setRequestedMovie(requestedMovie);
            }
            if (msg.arg1 == MSG_POPULAR) {
                HashMap<String, Object> objMovie = (HashMap<String, Object>) msg.obj;

                ArrayList<Movie> movies = (ArrayList<Movie>) objMovie.get("movies");
                MovieSearchAdapter adapterMovies = (MovieSearchAdapter) objMovie.get("adapterPopular");
                adapterMovies.SetData(movies);



            }


        }
    }
}
