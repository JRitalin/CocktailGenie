package com.example.cocktailgenie;

import android.os.Bundle;
import android.view.textclassifier.TextLinks;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Drink> drinksLists;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        drinksLists = new ArrayList<>();


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_favorites, R.id.navigation_search)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }


    private void jsonParse() {
        String url = "https://www.thecocktaildb.com/api/json/v1/1/search.php?s=blue+margarita";
        StringRequest request = new StringRequest(Request.Method.GET,url,
                response -> {
                        try {

                           JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("drinks");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject dr = jsonArray.getJSONObject(i);
                                Drink drinks = new Drink(dr.getString("strDrink"), dr.getString(
                                        "strCategory"), dr.getString("strGlass"),
                                        dr.getString("strInstructions"),
                                        dr.getString("strDrinkThumb"));
                                drinksLists.add(drinks);
                            }
                            adapter = new DrinkList(drinksLists, getApplicationContext());
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                }, error -> Toast.makeText(MainActivity.this, "Error" + error.toString(),
                        Toast.LENGTH_SHORT).show());
        RequestQueue mQueue = Volley.newRequestQueue(this);
        mQueue.add(request);
    }
}
