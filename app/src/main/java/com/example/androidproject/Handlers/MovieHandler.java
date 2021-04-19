package com.example.androidproject.Handlers;


import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.example.androidproject.Fragment.VideoPlayerActivity;
import com.example.androidproject.HomeAdapter.AdapterMovies;
import com.example.androidproject.Fragment.HomeFragment;
import com.example.androidproject.HomeAdapter.SlideAdapter;
import com.example.beans.Movie;

import java.util.ArrayList;
import java.util.HashMap;

public class MovieHandler extends Handler {
    private Context context;
    public MovieHandler(){

    }
    public MovieHandler(Context context){
        this.context = context;
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
        if (msg.arg1 == HomeFragment.MSG_START) {
            HashMap<String, Object> objMovie = (HashMap<String, Object>) msg.obj;
            ArrayList<Movie> movies = (ArrayList<Movie>) objMovie.get("MoviesList");
            AdapterMovies adapter = (AdapterMovies) objMovie.get("AdapterMovies");
            adapter.SetData(movies);
        }
        if (msg.arg1 == HomeFragment.MSG_SLIDE) {
            HashMap<String, Object> objMovie = (HashMap<String, Object>) msg.obj;
            ArrayList<Movie> movies = (ArrayList<Movie>) objMovie.get("SlideMovie");
            SlideAdapter adapter = (SlideAdapter) objMovie.get("SlideAdapter");
            adapter.setData(movies);
        }
        if (msg.arg1 == HomeFragment.MSG_LOAD) {
            // TODO : Traitement du chargement

        }
        if(msg.arg1 == SlideAdapter.MSG_SLIDE_CLICK){
            String key = (String) msg.obj;
            Intent i = new Intent(context, VideoPlayerActivity.class);
            i.putExtra("key",key);
            context.startActivity(i);
        }
    }
}
