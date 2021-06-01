package com.example.androidproject.Fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;


import com.example.androidproject.HomeAdapter.AdapterMovies;
import com.example.androidproject.HomeAdapter.VideoAdapter;
import com.example.androidproject.R;
import com.example.androidproject.HomeAdapter.MovieSearchAdapter;

import com.example.beans.Video;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Home extends AppCompatActivity implements MovieSearchAdapter.ItemClicked {

    BottomNavigationView bottomNav;

    final Fragment homeFragment = new HomeFragment();
    final Fragment searchFragment = new SearchFragment();
    final Fragment videoFragment = new VideoFragment();
    final Fragment profilFragment = new ProfilFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment activeFragment = homeFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        bottomNav = (BottomNavigationView) findViewById(R.id.bottom_menu);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        fm.beginTransaction().add(R.id.fragment_container, profilFragment, "4").hide(profilFragment).commit();
        fm.beginTransaction().add(R.id.fragment_container, videoFragment, "3").hide(videoFragment).commit();
        fm.beginTransaction().add(R.id.fragment_container, searchFragment, "2").hide(searchFragment).commit();
        fm.beginTransaction().add(R.id.fragment_container,homeFragment, "1").commit();


    }
    private  BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            //selectedFragment = new HomeFragment();
                            //break;
                            fm.beginTransaction().hide(activeFragment).show(homeFragment).commit();
                            activeFragment = homeFragment;
                            return true;
                        case R.id.nav_search:
                            fm.beginTransaction().hide(activeFragment).show(searchFragment).commit();
                            activeFragment = searchFragment;
                            return true;
                        case R.id.nav_video:
                            fm.beginTransaction().hide(activeFragment).show(videoFragment).commit();
                            activeFragment = videoFragment;
                            return true;
                        case R.id.nav_recently:
                            fm.beginTransaction().hide(activeFragment).show(profilFragment).commit();
                            activeFragment = profilFragment;
                            return true;
                    }



                    return false;
                }
            };


    @Override
    public void onItemClicked(int id) {


        Fragment fragment = new com.example.androidproject.Fragment.MovieDetails(id);



        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, fragment, "detail")
                .hide(activeFragment)
                .addToBackStack(null)
                .commit();
        activeFragment = fragment;


    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        Fragment f = getSupportFragmentManager().findFragmentByTag("detail");
        if(f == null) activeFragment = homeFragment;

        else activeFragment = f;


    }
}

