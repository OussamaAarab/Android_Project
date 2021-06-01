package com.example.androidproject.HomeAdapter;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproject.R;

import java.util.ArrayList;

import com.example.api.API_Factory;
import com.example.api.API_Movie;
import com.example.beans.*;
import com.squareup.picasso.Picasso;

public class MovieSearchAdapter extends RecyclerView.Adapter<MovieSearchAdapter.ViewHolder>{
    ArrayList<Movie> movies = new ArrayList<>();
    private boolean loading = false;
    private int page = 1;
    private String RequestedMovie;
    private String lang = "fr";//Todo change language to users's preferences
    Context context;
    ItemClicked movieClicked;
    int adapter_type;

    public interface ItemClicked
    {
        public  void onItemClicked(int id);
    }


    public MovieSearchAdapter(ArrayList<Movie> list) {
        movies=list;

    }

    public MovieSearchAdapter(@NonNull Context context, ArrayList<Movie> list,int adapter_type) {
        movies=list;
        this.context=context;
        movieClicked=(ItemClicked)context;
        this.adapter_type = adapter_type;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imMovie;
        TextView title, description,date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imMovie=itemView.findViewById(R.id.movie_search_image);
            title=itemView.findViewById(R.id.movie_search_title);
            description=itemView.findViewById(R.id.movie_search_desc);
            date=itemView.findViewById(R.id.movie_search_date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int index=movies.indexOf((Movie) v.getTag());
                    movieClicked.onItemClicked( movies.get(index).getId());
                }
            });


        }


    }

    @NonNull
    @Override
    public MovieSearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_movie_search,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieSearchAdapter.ViewHolder holder, int position) {

        holder.itemView.setTag(movies.get(position));

        holder.title.setText(movies.get(position).getTitle());

        Movie movie = movies.get(position);

        holder.title.setText(movie.getTitle());
        holder.description.setText(movie.getOverview());
        holder.date.setText(movie.getRelease_date());
        String linkImage = "";
        try {
            linkImage = API_Factory.getInstance(holder.imMovie.getContext()).getImage_source() + "w500" + movie.getPoster_path();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Picasso.get().load(linkImage).into(holder.imMovie);
        holder.title.setText(movie.getTitle());
        if((position >= movies.size() -3) && (adapter_type ==0)){

            LoadNextPage();

        }

    }

    @Override
    public int getItemCount()
    {
        return movies.size();
    }

    public void SetData(ArrayList<Movie> movies) {
        this.movies = movies;
        this.notifyDataSetChanged();

    }
    private  void LoadNextPage(){
        if(!loading){
            page++;
            new AsyncTask<Void, Void, Void>() {

                @Override
                protected Void doInBackground(Void... voids) {
                    try {
                        MovieSearchAdapter.this.loading = true;
                        API_Movie api_movie = API_Factory.getInstance(MovieSearchAdapter.this.context).getAPI_Movie();
                        ArrayList<Movie> mvs = api_movie.Search_Movie(RequestedMovie,MovieSearchAdapter.this.page);
                        MovieSearchAdapter.this.movies.addAll(mvs);
                        Log.d(MovieSearchAdapter.class.getName(),"Loaded Page " + MovieSearchAdapter.this.page);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    MovieSearchAdapter.this.notifyDataSetChanged();
                    MovieSearchAdapter.this.loading = false;
                }
            }.execute();

        }



    }

    public void setRequestedMovie(String requestedMovie) {
        RequestedMovie = requestedMovie;


    }

}
