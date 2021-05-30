package com.example.androidproject.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
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
import com.example.androidproject.HomeAdapter.*;

import com.example.androidproject.R;
import com.example.api.API_Factory;
import com.example.api.API_Movie;
import com.example.beans.Genre;
import com.example.beans.Movie;
import com.example.beans.Video;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;


public class MovieDetails extends Fragment {
    int id;
    View view;
    TextView title,desc,release,vote_nb,languageSpoken,simlarMovie;
    ImageView image;
    RatingBar ratingBar;

    Handler handlerMovie = new MovieDetailsHandler();

    RecyclerView recyclerViewSimilar;
    RecyclerView video_recyclerView;
    AdapterMovies adapterMovies;
    ArrayList<Movie> movies=new ArrayList<>();

    RecyclerView recyclerViewGenre;
    GenreAdapter genreAdapter;
    RecyclerView.LayoutManager manager;
    FragmentManager fragmentManager;
    public static final int MSG_DETAILS = 1,MSG_SIMILAR=2;


    public MovieDetails() {

    }
    public MovieDetails(int id) {

        this.id=id;

    }


    public static MovieDetails newInstance(String param1, String param2) {
        MovieDetails fragment = new MovieDetails();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_movie_details, container, false);
        this.view=view;
        image=view.findViewById(R.id.imageView2);
        ratingBar=view.findViewById(R.id.movie_rate);
        title =view.findViewById(R.id.mv_dt_title);
        desc=view.findViewById(R.id.mv_dt_desc);
        release=view.findViewById(R.id.mv_dt_release);
        video_recyclerView = view.findViewById(R.id.video_rv);
        vote_nb=view.findViewById(R.id.mv_dt_nb);
        languageSpoken=view.findViewById(R.id.mv_dt_language);
        simlarMovie=view.findViewById(R.id.mv_dt_sm);
        recyclerViewSimilar=view.findViewById(R.id.movies_similar);
        recyclerViewGenre =view.findViewById(R.id.mv_dt_gn);

        desc.setText("");
        title.setText("");
        languageSpoken.setText("");
        release.setText("");
        vote_nb.setText("");


        recyclerViewSimilar.setHasFixedSize(true);
        recyclerViewSimilar.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        adapterMovies=new AdapterMovies(this.getActivity(),(movies));
        recyclerViewSimilar.setAdapter(adapterMovies);
        ArrayList<Video> videos = new ArrayList<>();
        VideoAdapter adapter = new VideoAdapter(videos);

        video_recyclerView = view.findViewById(R.id.video_rv);
        video_recyclerView.setHasFixedSize(true);
        video_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        video_recyclerView.setAdapter(adapter);
        Log.d(getClass().getName(),"Recycler view shown : " + video_recyclerView.isShown());

        recyclerViewSimilar.setHasFixedSize(true);
        manager=new LinearLayoutManager(view.getContext());
        recyclerViewGenre.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));

        MovieDetails movieDetails=this;
        new Thread(new Runnable() {
            @Override
            public void run() {
                API_Factory factory = null;
                try {
                    factory = API_Factory.getInstance(view.getContext());
                    Message message = new Message();
                    message.arg1 = MSG_DETAILS;
                    API_Movie api_movie = factory.getAPI_Movie();
                    Movie movie = api_movie.findMovie(id, "videos");
                    if(!(movie == null)) {
                        HashMap<String,Object> objects = new HashMap<>();
                        objects.put("movie",movie);
                        objects.put("movieDetails",movieDetails);
                        objects.put("adapterMovies",adapterMovies);
                        objects.put("video_adapter",adapter);
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
                    message.arg1 = MSG_SIMILAR;
                    API_Movie api_movie = factory.getAPI_Movie();

                    movies=api_movie.findSimilarMovies(id,"","");

                    HashMap<String,Object> objects = new HashMap<>();
                    objects.put("movieDetails",movieDetails);
                    objects.put("movies",movies);
                    objects.put("adapterMovies",adapterMovies);
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

            ArrayList<Genre> genres=new ArrayList<>();
            for (int i=0;i<movie.getGenres().length;i++)
            {
                genres.add(movie.getGenres()[i]);
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

            linkImage = API_Factory.getInstance(view.getContext()).getImage_source() + "w500" + movie.getPoster_path();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Picasso.get().load(linkImage).into(image);

        ratingBar.setRating(movie.getVote_average()*0.5f);
        title.setText(movie.getTitle());
        vote_nb.setText(""+movie.getVote_count());
        desc.setText(movie.getOverview());
        genreAdapter=new GenreAdapter(this.getActivity(),genres);
        recyclerViewGenre.setAdapter(genreAdapter);

        release.setText(movie.getRelease_date());
        languageSpoken.setText(s);

    }

   public  void similarMovie(Boolean  aBoolean)
    {         if( aBoolean==false){
            simlarMovie.setVisibility(View.INVISIBLE);
            recyclerViewSimilar.setVisibility(View.INVISIBLE);
             }

    }
}