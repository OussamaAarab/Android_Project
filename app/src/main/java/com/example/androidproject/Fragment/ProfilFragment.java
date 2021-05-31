package com.example.androidproject.Fragment;

import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproject.Handlers.MovieHandler;
import com.example.androidproject.HomeAdapter.AdapterMovies;
import com.example.androidproject.R;
import com.example.beans.Movie;
import com.example.dao.DaoMovie;

import java.util.ArrayList;
import java.util.HashMap;

public class ProfilFragment extends Fragment {


    View v;
    RecyclerView lastVisited_rv;
    AdapterMovies adapterMovies;
    ArrayList<Movie> listMovies = new ArrayList<>();
    MovieHandler handlerMovies = new MovieHandler(getContext());
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v =  inflater.inflate(R.layout.fragment_profil, container, false);
        lastVisited_rv = v.findViewById(R.id.recycler_last_visited);
        adapterMovies = new AdapterMovies(getContext(), listMovies, 0);
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
                    message.arg1 = SecondActivity.MSG_START;
                    DaoMovie daoMovie = new DaoMovie(getContext());
                    listMovies =  daoMovie.findVisited();
                    HashMap<String, Object> objects = new HashMap<>();
                    objects.put("MoviesList", listMovies);
                    objects.put("AdapterMovies", adapterMovies);
                    message.obj = objects;
                    handlerMovies.sendMessage(message);
                }
            }).start();
        }

    }



    private AdapterMovies recyclerCards(RecyclerView recycler , AdapterMovies adapters) {

        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recycler.setAdapter(adapters);
        return  adapters;
    }
}
