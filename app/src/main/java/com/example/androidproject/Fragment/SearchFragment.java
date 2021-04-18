package com.example.androidproject.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproject.Handlers.MovieSearchHandler;
import com.example.androidproject.HomeAdapter.MovieSearchAdapter;
import com.example.androidproject.R;
import com.example.api.API_Factory;
import com.example.api.API_Movie;
import com.example.beans.Movie;

import java.util.ArrayList;
import java.util.HashMap;


public class SearchFragment extends Fragment {
    View view;
    RecyclerView recyclerView;
    MovieSearchAdapter adapter;
    RecyclerView.LayoutManager manager;
    SearchView searchView;
    FragmentManager fragmentManager;
    ArrayList<Movie> movies = new ArrayList<>();
    public static final int MSG_LOAD = -1;
    public static final int MSG_START = 1;
    Handler handlerMovie;


    public SearchFragment(){
    }
    public SearchFragment(ArrayList<Movie> movies)  {
        this.movies = movies;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        this.view=view;
        recyclerView = view.findViewById(R.id.list_movies);
        searchView = view.findViewById(R.id.search_view);

        recyclerView.setHasFixedSize(true);
        manager=new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        adapter=new MovieSearchAdapter(this.getActivity(),movies);
        recyclerView.setAdapter(adapter);

        handlerMovie = new MovieSearchHandler();

     searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
         @Override
         public boolean onQueryTextSubmit(String query) {
             new Thread(new Runnable() {
                 @Override
                 public void run() {
                     try {

                         Message message = new Message();
                         message.arg1 = MSG_LOAD;

                         try {
                             API_Factory factory = API_Factory.getInstance(view.getContext());
                             API_Movie api_movie = factory.getAPI_Movie();
                             movies = api_movie.Search_Movie(query,1);
                             message = new Message();
                             message.arg1 = MSG_START;
                             HashMap<String,Object> objects = new HashMap<>();
                             objects.put("MovieSearch",movies);
                             objects.put("MovieSearchAdapter",adapter);
                             objects.put("requestedMovie",query);
                             message.obj = objects;
                             handlerMovie.sendMessage(message);
                         } catch (Exception e) {
                             e.printStackTrace();
                         }
                     } catch (Exception e) {
                         e.printStackTrace();
                     }
                 }
             }).start();
             return false;
         }

         @Override
         public boolean onQueryTextChange(String newText) {
             new Thread(new Runnable() {
                 @Override
                 public void run() {
                     try {
                         Message message = new Message();
                         message.arg1 = MSG_LOAD;
                         try {
                             API_Factory factory = API_Factory.getInstance(view.getContext());
                             API_Movie api_movie = factory.getAPI_Movie();
                             movies = api_movie.Search_Movie(newText,1);
                             message = new Message();
                             message.arg1 = MSG_START;
                             HashMap<String,Object> objects = new HashMap<>();
                             objects.put("MovieSearch",movies);
                             objects.put("MovieSearchAdapter",adapter);
                             objects.put("requestedMovie",newText);
                             message.obj = objects;
                             handlerMovie.sendMessage(message);
                         } catch (Exception e) {
                             e.printStackTrace();
                         }
                     } catch (Exception e) {
                         e.printStackTrace();
                     }
                 }
             }).start();

             return false;
         }
     });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            Message message = new Message();
                            message.arg1 = MSG_LOAD;

                            try {
                                API_Factory factory = API_Factory.getInstance(view.getContext());
                                API_Movie api_movie = factory.getAPI_Movie();
                                movies = api_movie.Search_Movie(query,1);
                                message = new Message();
                                message.arg1 = MSG_START;
                                HashMap<String,Object> objects = new HashMap<>();
                                objects.put("MovieSearch",movies);
                                objects.put("MovieSearchAdapter",adapter);
                                objects.put("requestedMovie",query);
                                message.obj = objects;
                                handlerMovie.sendMessage(message);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Message message = new Message();
                            message.arg1 = MSG_LOAD;
                            try {
                                API_Factory factory = API_Factory.getInstance(view.getContext());
                                API_Movie api_movie = factory.getAPI_Movie();
                                movies = api_movie.Search_Movie(newText,1);
                                message = new Message();
                                message.arg1 = MSG_START;
                                HashMap<String,Object> objects = new HashMap<>();
                                objects.put("MovieSearch",movies);
                                objects.put("MovieSearchAdapter",adapter);
                                objects.put("requestedMovie",newText);
                                message.obj = objects;
                                handlerMovie.sendMessage(message);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

                return false;
            }
        });

        return  view;
    }



}


