package com.example.androidproject;

import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.androidproject.Handlers.MovieHandler;
import com.example.androidproject.HomeAdapter.AdapterMovies;
import com.example.androidproject.HomeAdapter.Slide;
import com.example.androidproject.HomeAdapter.SlideAdapter;
import com.example.api.API_Factory;
import com.example.api.API_Movie;
import com.example.beans.Movie;
import com.example.beans.Serie;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {
    View v ;

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

    private List<Slide> liste_Slide = new ArrayList<>();
    private ViewPager sliderpager;
    private TabLayout indicator;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_home, container, false);

        recyclerPopularMovies = v.findViewById(R.id.recycler_popular_movies_week);
        recyclerPopularSeries = v.findViewById(R.id.recycler_popular_series);

        adapterPopularMovies = recyclerCards(recyclerPopularMovies,adapterPopularMovies,movies);
        adapterPopularSeries = recyclerCards(recyclerPopularSeries,adapterPopularSeries,series);

        handlerMovie = new MovieHandler();
        handlerSeries = new MovieHandler();

        sliderpager = v.findViewById(R.id.auto_slide);
        indicator = v.findViewById(R.id.indicator);
        liste_Slide.add(new Slide(R.drawable.backdrop1,"Mission Impossible"));
        liste_Slide.add(new Slide(R.drawable.backdrop2,"Final Destination"));
        liste_Slide.add(new Slide(R.drawable.backdrop3,"Harry Potter"));
        liste_Slide.add(new Slide(R.drawable.backdrop4,"Avengers"));
        liste_Slide.add(new Slide(R.drawable.backdrop5,"Rush Hour"));
        SlideAdapter slideAdapter = new SlideAdapter(getActivity(), liste_Slide);
        sliderpager.setAdapter(slideAdapter);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new HomeFragment.SliderTimer(), 4000, 6000);
        indicator.setupWithViewPager(sliderpager,true);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Message message = new Message();
                    message.arg1 = MSG_LOAD;
                    API_Factory factory = API_Factory.getInstance(v.getContext());
                    API_Movie movie = factory.getAPI_Movie();
                    movies = movie.findTrendingMovies("day",1);
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
                    series = movie.Search_Movie("One",1);
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

        ScrollView scrollView = v.findViewById(R.id.scrollView_Home);
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(ScrollView.FOCUS_UP);
            }
        });


        return v;
    }

    private AdapterMovies recyclerCards(RecyclerView recycler ,AdapterMovies adapters, ArrayList<Movie> movies) {

        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        adapters = new AdapterMovies(movies);
        recycler.setAdapter(adapters);
        return  adapters;
    }



    class SliderTimer extends TimerTask{

        @Override
        public void run() {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(sliderpager.getCurrentItem() < liste_Slide.size()-1)
                    {
                        sliderpager.setCurrentItem(sliderpager.getCurrentItem()+1);
                    }
                    else
                    {
                        sliderpager.setCurrentItem(0);
                    }
                }
            });
        }
    }
}