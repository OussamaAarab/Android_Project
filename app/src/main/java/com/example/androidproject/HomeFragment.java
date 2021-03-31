package com.example.androidproject;

import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproject.Handlers.MovieHandler;
import com.example.androidproject.HomeAdapter.AdapterMovies;
import com.example.api.API_Factory;
import com.example.api.API_Movie;
import com.example.beans.Movie;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeFragment extends Fragment {

    RecyclerView recyclerPopularMovies;
    RecyclerView recyclerPopularSeries;

    AdapterMovies adapterPopularMovies;
    AdapterMovies adapterPopularSeries;

    MovieHandler handlerMovie;
    MovieHandler handlerSeries;

    public static final int MSG_LOAD = -1;
    public static final int MSG_START = 1;

    ArrayList<Movie> movies = new ArrayList<>();
    ArrayList<Movie> series = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_home, container, false);

        recyclerPopularMovies = v.findViewById(R.id.recycler_popular_movies);
        recyclerPopularSeries = v.findViewById(R.id.recycler_popular_series);

        adapterPopularMovies = recyclerCards(recyclerPopularMovies,adapterPopularMovies,movies);
        adapterPopularSeries = recyclerCards(recyclerPopularSeries,adapterPopularSeries,series);

        handlerMovie = new MovieHandler();
        handlerSeries = new MovieHandler();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Message message = new Message();
                    message.arg1 = MSG_LOAD;
                    API_Factory factory = API_Factory.getInstance(v.getContext());
                    API_Movie movie = factory.getAPI_Movie();
                    movies = movie.Search_Movie("Big mamma","en-US",1);
                    message = new Message();
                    message.arg1 = MSG_START;
                    HashMap<String,Object> objects = new HashMap<>();
                    objects.put("PopularMovies",movies);
                    objects.put("AdapterMovies",adapterPopularMovies);
                    message.obj = objects;
                    handlerMovie.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Message message = new Message();
                    message.arg1 = MSG_LOAD;
                    API_Factory factory = API_Factory.getInstance(v.getContext());
                    API_Movie movie = factory.getAPI_Movie();
                    series = movie.Search_Movie("One","en-US",1);
                    message = new Message();
                    message.arg1 = MSG_START;
                    HashMap<String,Object> objects = new HashMap<>();
                    objects.put("PopularMovies",series);
                    objects.put("AdapterMovies",adapterPopularSeries);
                    message.obj = objects;
                    handlerSeries.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();




        return v;
    }

    private AdapterMovies recyclerCards(RecyclerView recycler ,AdapterMovies adapters, ArrayList<Movie> movies) {

        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        adapters = new AdapterMovies(movies);
        recycler.setAdapter(adapters);
        return  adapters;
    }
}
