package com.GLA_12.androidproject.Fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Message;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.GLA_12.androidproject.Handlers.MovieHandler;
import com.GLA_12.androidproject.HomeAdapter.AdapterMovies;
import com.GLA_12.androidproject.HomeAdapter.MovieSearchAdapter;
import com.GLA_12.androidproject.R;
import com.GLA_12.beans.Movie;

import java.util.ArrayList;
import java.util.HashMap;

public class SecondActivity extends AppCompatActivity implements MovieSearchAdapter.ItemClicked {

    FrameLayout frame;

    RecyclerView recyclerMovies;

    AdapterMovies adapterMovies;

    ArrayList<Movie> listMovies = new ArrayList<>();

    TextView title;

    MovieHandler handlerMovies;

    public static final int MSG_START = 14;

    public static final int TRENDING = 1;
    public static final int POPULAR = 2;
    public static final int HORROR = 3;
    public static final int ACTION = 4;
    public static final int ADVENTURE = 5;
    public static final int COMEDY = 6;
    public static final int DRAMA = 7;
    public static final int ROMANCE = 8;
    public static final int WAR = 9;
    public static final int DOCUMENTARY = 10;

    public static int gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_second);

        gender = getIntent().getExtras().getInt("gender");

        frame = (FrameLayout) findViewById(R.id.fragment_container);

        recyclerMovies = (RecyclerView) findViewById(R.id.recycler_more_movies);

        title = (TextView) findViewById(R.id.title_gender);


        adapterMovies = new AdapterMovies(this, listMovies, 1);
        recyclerCards(recyclerMovies,adapterMovies);

        handlerMovies = new MovieHandler();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    title.setText(adapterMovies.LoadTitle(gender));
                    Message message = new Message();
                    listMovies = adapterMovies.LoadCards(gender, 1);
                    message.arg1 = MSG_START;
                    HashMap<String, Object> objects = new HashMap<>();
                    objects.put("MoviesList", listMovies);
                    objects.put("AdapterMovies", adapterMovies);
                    message.obj = objects;
                    handlerMovies.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private AdapterMovies recyclerCards(RecyclerView recycler ,AdapterMovies adapters) {

        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new GridLayoutManager(this, 2));
        recycler.setAdapter(adapters);
        return  adapters;
    }


    @Override
    public void onItemClicked(int id) {
        Fragment fragment = new com.GLA_12.androidproject.Fragment.MovieDetails(id);

        Fragment f = getSupportFragmentManager().findFragmentByTag("detail");


        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment, "detail")
                .addToBackStack(null)
                .commit();

    }
}