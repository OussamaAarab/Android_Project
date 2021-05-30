package com.example.androidproject.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproject.Handlers.MovieHandler;
import com.example.androidproject.HomeAdapter.AdapterMovies;
import com.example.androidproject.HomeAdapter.TrailerAdapter;
import com.example.androidproject.HomeAdapter.VideoAdapter;
import com.example.androidproject.R;
import com.example.api.API_Factory;
import com.example.api.API_Movie;
import com.example.beans.Movie;
import com.example.beans.Video;

import java.util.ArrayList;
import java.util.HashMap;

public class VideoFragment extends Fragment {

    public static final int MSG_START_TRENDING_TRAILER = 10;

    RecyclerView week_trending_rv;
    TrailerAdapter week_trending_adapter;
    MovieHandler handler = new MovieHandler();
    ArrayList trending_Movies_w = new ArrayList();
    View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_video, container, false);

        week_trending_rv = v.findViewById(R.id.recycler_popular_movies_week_trailers);
        week_trending_adapter = recyclerCards(week_trending_rv,week_trending_adapter,trending_Movies_w);
        LoadData();

        return v;
    }

    private TrailerAdapter recyclerCards(RecyclerView recycler , TrailerAdapter adapters, ArrayList<Movie> movies) {

        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        adapters = new TrailerAdapter(getActivity(),movies);
        recycler.setAdapter(adapters);
        return  adapters;
    }
    private void LoadData(){
        // Thread that loads popular movies
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Message message = new Message();
                    API_Factory factory = API_Factory.getInstance(v.getContext());
                    API_Movie movie = factory.getAPI_Movie();
                    trending_Movies_w = movie.findTrendingMovies("week",1);
                    message.arg1 = MSG_START_TRENDING_TRAILER;
                    HashMap<String,Object> objects = new HashMap<>();
                    objects.put("MoviesList",trending_Movies_w);
                    objects.put("AdapterMovies",week_trending_adapter);
                    message.obj = objects;
                    handler.sendMessage(message);


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
