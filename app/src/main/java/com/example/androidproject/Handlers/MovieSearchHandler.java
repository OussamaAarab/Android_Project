package com.example.androidproject.Handlers;


import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;


import com.example.androidproject.Fragment.SearchFragment;


import com.example.androidproject.HomeAdapter.MovieSearchAdapter;

import com.example.beans.Movie;

import java.util.ArrayList;
import java.util.HashMap;

public class MovieSearchHandler extends Handler {

    @Override
    public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
        if (msg.arg1 == SearchFragment.MSG_START) {
            HashMap<String, Object> objMovie = (HashMap<String, Object>) msg.obj;
            MovieSearchAdapter adapter = (MovieSearchAdapter) objMovie.get("MovieSearchAdapter");
            ArrayList<Movie> movies = (ArrayList<Movie>) objMovie.get("MovieSearch");
            String requestedMovie = (String) objMovie.get("requestedMovie");
            adapter.SetData(movies);
            adapter.setRequestedMovie(requestedMovie);

        }


    }
}
