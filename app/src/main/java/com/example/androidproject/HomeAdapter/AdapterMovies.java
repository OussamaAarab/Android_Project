package com.example.androidproject.HomeAdapter;


import android.content.Context;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproject.Fragment.SecondActivity;
import com.example.androidproject.Handlers.MovieHandler;
import com.example.androidproject.R;
import com.example.api.API_Factory;
import com.example.api.API_Movie;
import com.example.beans.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.androidproject.HomeAdapter.MovieSearchAdapter.ItemClicked;
public class AdapterMovies extends RecyclerView.Adapter<AdapterMovies.FeaturedViewHolder> {

    ArrayList<Movie> movies;
    private int page = 1;
    private String RequestedMovie;
    Context context;
    ItemClicked movieClicked;
    int adapterType;
    public final static int MSG_PAGE = 15;
    MovieHandler handlerMovies = new MovieHandler();
    boolean loading;




    public AdapterMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public AdapterMovies(@NonNull Context context, ArrayList<Movie> movies, int adapterType) {
        this.movies=movies;
        this.context=context;
        movieClicked=(ItemClicked)context;
        this.adapterType = adapterType;
    }

    public void SetData(ArrayList<Movie> movies) {
        this.movies = movies;
        this.notifyDataSetChanged();
    }

    public void AddData(ArrayList<Movie> movies) {
        this.movies.addAll(movies);
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_design, parent, false);
        FeaturedViewHolder featuredViewHolder = new FeaturedViewHolder(view);

        return featuredViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedViewHolder holder, int position) {

        Movie movie = movies.get(position);
        holder.itemView.setTag(movies.get(position));

        String linkImage = "";
        try {
            linkImage = API_Factory.getInstance(holder.image.getContext()).getImage_source() + "w500" + movie.getPoster_path();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Picasso.get().load(linkImage).into(holder.image);
        holder.title.setText(movie.getTitle());
        holder.desc.setText(movie.getOverview());
        holder.vote.setText(movie.getVote_count()+"");
        holder.rate.setRating(movie.getVote_average()*0.5f);
        holder.date.setText(movie.getRelease_date());
        
        if(position >= movies.size() -4 && adapterType == 1 && !loading){
            page++;
            NextPage(page);

        }

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class FeaturedViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title,desc,vote,date;
        RatingBar rate;
        public FeaturedViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.card_image);
            title = itemView.findViewById(R.id.card_title);
            desc = itemView.findViewById(R.id.card_desc);
            vote = itemView.findViewById(R.id.card_vote);
            rate = itemView.findViewById(R.id.card_rate);
            date = itemView.findViewById(R.id.card_date);
            movieClicked=(ItemClicked)context;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int index = movies.indexOf((Movie) v.getTag());
                    movieClicked.onItemClicked( movies.get(index).getId());
                }
            });
        }
    }

    public ArrayList<Movie> LoadCards(int genre, int page) throws Exception {
        ArrayList<Movie> list;
        API_Factory factory = API_Factory.getInstance(context);
        API_Movie movie = factory.getAPI_Movie();
        switch (genre)
        {
            case SecondActivity.TRENDING:
                list = movie.findTrendingMovies("week", page);
                return list;
            case SecondActivity.POPULAR:
                list = movie.findPopularMovies(page);
                return list;
            case SecondActivity.HORROR:
                list = movie.findGenreMovies(27, page);
                return list;
            case SecondActivity.ACTION:
                list = movie.findGenreMovies(28, page);
                return list;
            case SecondActivity.ADVENTURE:
                list = movie.findGenreMovies(12, page);
                return list;
            case SecondActivity.COMEDY:
                list = movie.findGenreMovies(35, page);
                return list;
            case SecondActivity.DRAMA:
                list = movie.findGenreMovies(18, page);
                return list;
            case SecondActivity.ROMANCE:
                list = movie.findGenreMovies(10749, page);
                return list;
            case SecondActivity.WAR:
                list = movie.findGenreMovies(10752, page);
                return list;
            case SecondActivity.DOCUMENTARY:
                list = movie.findGenreMovies(99, page);
                return list;
        }
        return null;
    }

    public void NextPage(int page)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    loading = true;
                    ArrayList<Movie> listMovies;
                    Message message = new Message();
                    listMovies = LoadCards(SecondActivity.gender, page);
                    message.arg1 = MSG_PAGE;
                    HashMap<String, Object> objects = new HashMap<>();
                    objects.put("MoviesList", listMovies);
                    objects.put("AdapterMovies", AdapterMovies.this);
                    message.obj = objects;
                    handlerMovies.sendMessage(message);
                    loading = false;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void setRequestedMovie(String requestedMovie) {
        RequestedMovie = requestedMovie;
    }
}