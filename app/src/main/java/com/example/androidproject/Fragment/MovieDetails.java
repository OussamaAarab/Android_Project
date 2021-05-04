package com.example.androidproject.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.androidproject.Handlers.MovieDetailsHandler;
import com.example.androidproject.HomeAdapter.AdapterMovies;
import com.example.androidproject.R;
import com.example.api.API_Factory;
import com.example.api.API_Movie;
import com.example.beans.Genre;
import com.example.beans.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;


public class MovieDetails extends Fragment {
    int id;
    View view;
    TextView title,desc,release,vote_nb,languageSpoken;
    TextView [] genre=new TextView[4];
    ImageView imageView;
    RatingBar ratingBar;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Handler handlerMovie = new MovieDetailsHandler();

    RecyclerView recyclerView;
    AdapterMovies adapterMovies;
    ArrayList<Movie> movies=new ArrayList<>();



    private String mParam1;
    private String mParam2;
    public MovieDetails() {

    }
    public MovieDetails(int id) {

        this.id=id;

    }


    public static MovieDetails newInstance(String param1, String param2) {
        MovieDetails fragment = new MovieDetails();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_movie_details, container, false);
        this.view=view;
        imageView = view.findViewById(R.id.imageView);
        ratingBar=view.findViewById(R.id.movie_rate);
        title =view.findViewById(R.id.mv_dt_title);
        desc=view.findViewById(R.id.mv_dt_desc);
        release=view.findViewById(R.id.mv_dt_release);
        vote_nb=view.findViewById(R.id.mv_dt_nb);
        languageSpoken=view.findViewById(R.id.mv_dt_language);
        genre[0] = view.findViewById(R.id.mv_dt_gen1);
        genre[1] = view.findViewById(R.id.mv_dt_gen2);
        genre[2] = view.findViewById(R.id.mv_dt_gen3);
        genre[3] = view.findViewById(R.id.mv_dt_gen4);
        recyclerView=view.findViewById(R.id.movies_similar);
        desc.setText("");
        title.setText("");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        adapterMovies=new AdapterMovies((movies));
        recyclerView.setAdapter(adapterMovies);

        MovieDetails movieDetails=this;
        new Thread(new Runnable() {
            @Override
            public void run() {
                API_Factory factory = null;
                try {
                    factory = API_Factory.getInstance(view.getContext());
                    Message message = new Message();
                    message.arg1 = 1;
                    API_Movie api_movie = factory.getAPI_Movie();
                    Movie movie = api_movie.findMovie(id, null);
                    if(!(movie == null)) {


                        HashMap<String,Object> objects = new HashMap<>();
                        objects.put("movie",movie);
                        objects.put("movieDetails",movieDetails);
                        objects.put("adapter",adapterMovies);
                        message.obj = objects;
                        handlerMovie.sendMessage(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                API_Factory factory = null;
                try {
                    factory = API_Factory.getInstance(view.getContext());
                    Message message = new Message();
                    message.arg1 = 2;
                    API_Movie api_movie = factory.getAPI_Movie();

                    movies=api_movie.findSimilarMovies(id,"","");

                    HashMap<String,Object> objects = new HashMap<>();
                    objects.put("movies",movies);
                    objects.put("adapter",adapterMovies);
                    message.obj = objects;
                    handlerMovie.sendMessage(message);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        return view;
    }

    public void setData(Movie movie)
    {
        for(int i=0;i<movie.getGenres().length;i++)
        {
            genre[i].setText(movie.getGenres()[i].getName());
            genre[i].setVisibility(View.VISIBLE);
        }
        String s="";
        for(int i=0;i<movie.getSpoken_languages().length;i++)
        {
            s=s+movie.getSpoken_languages()[i].getEnglish_name()+", ";
        }
        if(!s.isEmpty())
        s=s.substring(0,s.lastIndexOf(','));
        String linkImage = "";
        try {

            linkImage = API_Factory.getInstance(view.getContext()).getImage_source() + "w500" + movie.getBackdrop_path();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Picasso.get().load(linkImage).into(imageView);
        ratingBar.setRating(movie.getVote_average()*0.5f);
        title.setText(movie.getTitle());
        vote_nb.setText(""+movie.getVote_count());
        desc.setText(movie.getOverview());
        release.setText(movie.getRelease_date());
        languageSpoken.setText(s);

    }
}