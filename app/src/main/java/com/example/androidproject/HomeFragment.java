package com.example.androidproject;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.api.API_Factory;
import com.example.api.API_Movie;
import com.example.beans.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    View v ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v =  inflater.inflate(R.layout.fragment_home, container, false);
        
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    /*@Override
    public void onStart() {
        super.onStart();
        Log.d("Aabane","On resume");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    API_Movie api_movie = API_Factory.getInstance(v.getContext()).getAPI_Movie();
                    Movie m = api_movie.findMovie(500,"fr",null);
                    Log.d(getClass().getName(),m.getOverview());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }*/

    @Override
    public void onStart() {
        super.onStart();
        Log.d("Asmaa","On resume");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    API_Movie api_movie = API_Factory.getInstance(v.getContext()).getAPI_Movie();
                    Movie m = api_movie.findLatestMovies();
                    Log.d(getClass().getName(),m.getOverview());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


}
