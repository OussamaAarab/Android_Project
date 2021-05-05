package com.example.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.beans.Movie;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DaoMovie extends DaoFactory {

    public DaoMovie(Context context) {
        super(context);
    }

    public void add_to_visited(Movie movie){
        ContentValues values = new ContentValues();
        values.put("id",movie.getId());
        values.put("overview",movie.getOverview());
        values.put("poster_path",movie.getPoster_path());
        values.put("release_date",movie.getRelease_date());
        values.put("vote_average",movie.getVote_average());
        open();
        super.db.insert("movie",null,values);
        values.clear();
        values.put("movie_id",movie.getId());
        values.put("visit_date", new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date()));
        super.db.insert("visited",null,values);
        close();
    }
    public ArrayList<Movie> findVisited(){
        ArrayList<Movie> movies = new ArrayList<>();
        open();
        Cursor cursor = super.db.rawQuery("SELECT * FROM movie INNER JOIN visited ON movie.id = visited.movie_id;",new String[]{});
        cursor.moveToNext();
        while (!cursor.isAfterLast()){
            Movie movie = new Movie();

            movie.setId(cursor.getInt(cursor.getColumnIndex("id")));
            movie.setOverview(cursor.getString(cursor.getColumnIndex("overview")));
            movie.setPoster_path(cursor.getString(cursor.getColumnIndex("poster_path")));
            movie.setRelease_date(cursor.getString(cursor.getColumnIndex("release_date")));
            movie.setVote_average(cursor.getFloat(cursor.getColumnIndex("vote_average")));
            movies.add(movie);
            cursor.moveToNext();

        }

        cursor.close();
        close();
        return movies;
    }
}
