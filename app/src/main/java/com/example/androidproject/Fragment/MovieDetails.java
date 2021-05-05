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
import com.example.androidproject.HomeAdapter.VideoAdapter;
import com.example.androidproject.R;
import com.example.api.API_Factory;
import com.example.api.API_Movie;
import com.example.beans.Movie;
import com.example.beans.Video;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MovieDetails#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieDetails extends Fragment {
    int id;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView recyclerView;
    Handler handlerMovie = new MovieDetailsHandler();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public static final int MSG_LOAD = -1;
    public static final int MSG_START = 1;
    public MovieDetails() {
        // Required empty public constructor
    }
    public MovieDetails(int id) {
        // Required empty public constructor
        this.id=id;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MovieDetails.
     */
    // TODO: Rename and change types and number of parameters
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
        ArrayList<Video> videos = new ArrayList<>();
        VideoAdapter adapter = new VideoAdapter(videos);

        recyclerView = view.findViewById(R.id.video_rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
        Log.d(getClass().getName(),"Recycler view shown : " + recyclerView.isShown());
        Movie movie=null;
        new Thread(new Runnable() {
            @Override
            public void run() {
                API_Factory factory = null;
                try {
                    factory = API_Factory.getInstance(view.getContext());
                    Message message = new Message();
                    message.arg1 = MSG_LOAD;
                    API_Movie api_movie = factory.getAPI_Movie();

                    Movie movie = api_movie.findMovie(id, "videos");


                    if(!(movie == null)) {
                        ImageView imageView = view.findViewById(R.id.imageView);
                        RatingBar ratingBar=view.findViewById(R.id.movie_rate);
                        TextView title,desc,release,vote_nb,languageSpoken;
                        TextView [] genre=new TextView[4];

                        title =view.findViewById(R.id.mv_dt_title);
                        desc=view.findViewById(R.id.mv_dt_desc);
                        release=view.findViewById(R.id.mv_dt_release);
                        vote_nb=view.findViewById(R.id.mv_dt_nb);
                        languageSpoken=view.findViewById(R.id.mv_dt_language);
                        genre[0] = view.findViewById(R.id.mv_dt_gen1);
                        genre[1] = view.findViewById(R.id.mv_dt_gen2);
                        genre[2] = view.findViewById(R.id.mv_dt_gen3);
                        genre[3] = view.findViewById(R.id.mv_dt_gen4);
                        String linkImage = "";
                        try {

                            linkImage = API_Factory.getInstance(view.getContext()).getImage_source() + "original" + movie.getBackdrop_path();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        message = new Message();
                        message.arg1 = MSG_START;
                        HashMap<String,Object> objects = new HashMap<>();
                        objects.put("linkImage",linkImage);
                        objects.put("title",title);
                        objects.put("release",release);
                        objects.put("languageSpoken",languageSpoken);
                        objects.put("desc",desc);
                        objects.put("vote_nb",vote_nb);
                        objects.put("genre",genre);
                        objects.put("imageView",imageView);
                        objects.put("movie",movie);
                        objects.put("ratingBar",ratingBar);
                        objects.put("video_adapter",adapter);

                        message.obj = objects;
                        handlerMovie.sendMessage(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        return view;
    }
}