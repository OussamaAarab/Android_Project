package com.example.androidproject;

import android.content.Context;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.api.API_Factory;
import com.example.api.API_Movie;
import com.example.api.API_SERIE;
import com.example.beans.Movie;
import com.example.beans.Serie;
import com.example.dao.DaoFactory;
import com.example.dao.DaoMovie;
import com.google.gson.JsonObject;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.example.androidproject", appContext.getPackageName());
        //APIMOVIE testing
        /*
        API_Movie movie = API_Factory.getInstance(appContext).getAPI_Movie();

        ArrayList<Movie> movies = movie.findTrendingMovies("day",1);
        assertFalse(movies.isEmpty());

        for(Movie m : movies){
            Log.d(ExampleInstrumentedTest.class.getName(),"Testing movie "+ m.getTitle());
            assertNotNull(m.getTitle());
            assertNotNull(m.getOverview());
            assertNotNull(m.getPoster_path());
        }



        Movie mv = movie.findMovie(399566,null);
        assertNotNull(mv);
        assertNotNull(mv.getImdb_id());
        assertNotNull(mv.getId());
        assertNotNull(mv.getGenres());
        assertNotNull(mv.getBudget());
        assertNotNull(mv.getBackdrop_path());




        DaoMovie daoMovie = new DaoMovie(appContext);

        Log.d(ExampleInstrumentedTest.class.getName(),mv.getOverview());

        //daoMovie.add_to_visited(mv);
        movies = daoMovie.findVisited();
        assertNotNull(movies);
        assertNotEquals(movies.size(),0);
        assertEquals(movies.get(0).getOverview(),mv.getOverview());
        Log.d(ExampleInstrumentedTest.class.getName(),movies.get(0).getOverview());

         */


       // Log.d(ExampleInstrumentedTest.class.getName(),new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date()));

        API_SERIE api_serie = API_Factory.getInstance(appContext).getAPI_Serie();
        JsonObject obj = api_serie.findTrendingSeriesJson("day",1);
        Log.d(this.getClass().getName(),obj.toString());

    }

}