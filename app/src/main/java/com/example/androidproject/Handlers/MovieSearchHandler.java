package com.example.androidproject.Handlers;


import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;


import com.example.androidproject.Fragment.MovieDetails;
import com.example.androidproject.Fragment.SearchFragment;


import com.example.androidproject.HomeAdapter.AdapterMovies;
import com.example.androidproject.HomeAdapter.MovieSearchAdapter;

import com.example.beans.Movie;

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
