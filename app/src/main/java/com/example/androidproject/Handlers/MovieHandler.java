package com.example.androidproject.Handlers;


import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.example.androidproject.HomeAdapter.AdapterMovies;
import com.example.androidproject.Fragment.HomeFragment;
import com.example.beans.Movie;

import java.util.ArrayList;
import java.util.HashMap;

public class MovieHandler extends Handler {

    @Override
    public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
        if (msg.arg1 == HomeFragment.MSG_START) {
            HashMap<String, Object> objMovie = (HashMap<String, Object>) msg.obj;
            ArrayList<Movie> movies = (ArrayList<Movie>) objMovie.get("MoviesList");
            AdapterMovies adapter = (AdapterMovies) objMovie.get("AdapterMovies");
            adapter.SetData(movies);
        }
        if (msg.arg1 == HomeFragment.MSG_LOAD) {
            // TODO : Traitement du chargement

        }
    }
}
