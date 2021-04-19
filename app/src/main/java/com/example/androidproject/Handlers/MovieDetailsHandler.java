package com.example.androidproject.Handlers;


import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.androidproject.Fragment.SearchFragment;
import com.example.beans.Movie;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class MovieDetailsHandler extends Handler {

    @Override
    public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
        if (msg.arg1 == SearchFragment.MSG_START) {
            HashMap<String, Object> objMovie = (HashMap<String, Object>) msg.obj;
            TextView title,desc,release,languageSpoken,vote_nb;
            TextView[] genre=(TextView[])objMovie.get("genre");

            release=(TextView)objMovie.get("release");
            vote_nb=(TextView) objMovie.get("vote_nb");
            languageSpoken=(TextView) objMovie.get(("languageSpoken"));
            title=(TextView) objMovie.get("title");
            desc=(TextView) objMovie.get("desc");
            String linkImage = (String) objMovie.get("linkImage");
            ImageView imageView = (ImageView) objMovie.get("imageView");
            Movie movie=(Movie)objMovie.get("movie");


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
            if(!s.isEmpty()) s = s.substring(0, s.lastIndexOf(','));


            title.setText(movie.getTitle());
            vote_nb.setText(""+movie.getVote_count());
            desc.setText(movie.getOverview());
            release.setText(movie.getRelease_date());
            languageSpoken.setText(s);
            RatingBar ratingBar=(RatingBar) objMovie.get("ratingBar");
            Picasso.get().load(linkImage).into(imageView);
            ratingBar.setRating(movie.getVote_average()*0.5f);
        }


    }
}
