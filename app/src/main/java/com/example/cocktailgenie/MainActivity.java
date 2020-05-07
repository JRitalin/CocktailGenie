package com.example.cocktailgenie;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.cocktailgenie.fragments.FavoritesFragment;
import com.example.cocktailgenie.fragments.HomeFragment;
import com.example.cocktailgenie.fragments.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private ActionBar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = getSupportActionBar();
        toolbar.setTitle("Cocktail Genie");

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frag_holder,
                    new HomeFragment()).commit();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment frag;
            switch (item.getItemId()) {
                case R.id.bottomNavigationHomeId:
                    toolbar.setTitle("Home");
                    frag = new HomeFragment();
                    loadFrag(frag);
                    return true;
                case R.id.bottomNavigationFavoritesId:
                    toolbar.setTitle("Favorites");
                    frag = new FavoritesFragment();
                    loadFrag(frag);
                    return true;
                case R.id.bottomNavigationSearchId:
                    toolbar.setTitle("Search");
                    frag = new SearchFragment();
                    loadFrag(frag);
                    return true;
            }
            return false;
        }
    };

    private void loadFrag(Fragment frag) {
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        trans.replace(R.id.frag_holder, frag);
        trans.addToBackStack(null);
        trans.commit();
    }


}
