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

        String key = API_Factory.getInstance(appContext).getAPI_Movie().getTrailerKey(399566);

        Log.d(getClass().getName(),key);


    }

}