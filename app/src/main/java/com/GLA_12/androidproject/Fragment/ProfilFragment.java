package com.GLA_12.androidproject.Fragment;

import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.GLA_12.androidproject.Handlers.MovieHandler;
import com.GLA_12.androidproject.HomeAdapter.MovieSearchAdapter;
import com.GLA_12.androidproject.R;
import com.GLA_12.beans.Movie;
import com.GLA_12.dao.DaoMovie;

import java.util.ArrayList;
import java.util.HashMap;

public class ProfilFragment extends Fragment {


    View v;
    RecyclerView lastVisited_rv;
    MovieSearchAdapter adapterMovies;
    ArrayList<Movie> listMovies = new ArrayList<>();
    MovieHandler handlerMovies = new MovieHandler(getActivity());
    RelativeLayout no_item_layout;
    public static final int MSG_Last_Visited = 100;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v =  inflater.inflate(R.layout.fragment_profil, container, false);
        lastVisited_rv = v.findViewById(R.id.recycler_last_visited);
        adapterMovies = new MovieSearchAdapter(getContext(), listMovies,1);
        no_item_layout = v.findViewById(R.id.no_item_layout);
        no_item_layout.setVisibility(View.INVISIBLE);
        recyclerCards(lastVisited_rv,adapterMovies);

        return v;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Message message = new Message();
                    message.arg1 = MSG_Last_Visited;
                    DaoMovie daoMovie = new DaoMovie(getContext());
                    listMovies =  daoMovie.findVisited();
                    HashMap<String, Object> objects = new HashMap<>();
                    objects.put("MoviesList", listMovies);
                    objects.put("AdapterMovies", adapterMovies);
                    objects.put("fragment", ProfilFragment.this);
                    message.obj = objects;
                    handlerMovies.sendMessage(message);
                }
            }).start();
        }

    }



    private MovieSearchAdapter recyclerCards(RecyclerView recycler , MovieSearchAdapter adapters) {

        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        recycler.setAdapter(adapters);
        return  adapters;
    }

    public void showMessage(){
        no_item_layout.setVisibility(View.VISIBLE);
    }
    public  void hideMessage(){
        no_item_layout.setVisibility(View.GONE);

    }
}
