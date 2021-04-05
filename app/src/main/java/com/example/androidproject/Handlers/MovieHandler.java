package com.example.androidproject.Handlers;


import android.os.Handler;
import android.os.Message;
import android.widget.Adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproject.HomeAdapter.AdapterMovies;
import com.example.androidproject.HomeFragment;
import com.example.beans.Movie;

import java.util.ArrayList;
import java.util.HashMap;

public class MovieHandler extends Handler {

    @Override
    public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
        if (msg.arg1 == HomeFragment.MSG_START) {
            HashMap<String, Object> objMovie = (HashMap<String, Object>) msg.obj;
            ArrayList<Movie> movies = (ArrayList<Movie>) objMovie.get("PopularMovies");
            AdapterMovies adapter = (AdapterMovies) objMovie.get("AdapterMovies");
            adapter.SetData(movies);
        }
        if (msg.arg1 == HomeFragment.MSG_LOAD) {
            // TODO : Traitement du chargement

        }
    }
}
