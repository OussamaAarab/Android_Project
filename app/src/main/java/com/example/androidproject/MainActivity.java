package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SyncAdapterType;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.beans.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageView logo;
    TextView slogan,text_bottom;
    Animation logo_anim,slogan_anim;
    private static int SPLASH_SCREEN = 10500;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        // Animation
        logo_anim = AnimationUtils.loadAnimation(this,R.anim.logo_animation);
        slogan_anim = AnimationUtils.loadAnimation(this,R.anim.slogan_animation);

        logo = (ImageView) findViewById(R.id.logo);
        slogan = (TextView) findViewById(R.id.slogan);
        text_bottom = (TextView) findViewById(R.id.tickets_app);

        logo.setAnimation(logo_anim);
        slogan.setAnimation(slogan_anim);
        text_bottom.setAnimation(slogan_anim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent home = new Intent(MainActivity.this,Home.class);
                startActivity(home);
                finish();
            }
        },SPLASH_SCREEN);
    }
}