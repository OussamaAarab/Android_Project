package com.example.androidproject.Fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;


import com.example.androidproject.HomeAdapter.AdapterMovies;
import com.example.androidproject.R;
import com.example.androidproject.HomeAdapter.MovieSearchAdapter;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity implements MovieSearchAdapter.ItemClicked, AdapterMovies.ItemClicked {

    BottomNavigationView bottomNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        bottomNav = (BottomNavigationView) findViewById(R.id.bottom_menu);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
    }
    private  BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_search:
                            try {
                                selectedFragment = new SearchFragment();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            Log.i("Search","Clique sur le boutton search");
                            break;
                        case R.id.nav_video:
                            selectedFragment = new VideoFragment();
                            break;
                        case R.id.nav_profil:
                            selectedFragment = new ProfilFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

                    return true;
                }
            };
    @Override
    public void onItemClicked(int id) {

        Fragment fragment = new com.example.androidproject.Fragment.MovieDetails(id);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment, null)
                .addToBackStack(null)
                .commit();

    }
}