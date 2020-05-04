package com.example.cocktailgenie;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {
    private ActionBar toolbar;

    RequestQueue mQueue;
    TextView textView3;

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
            switch (item.getItemId()){
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
                    toolbar.setTitle("Settings");
                    frag= new SearchFragment();
                    loadFrag(frag);
                    return true;
            }
            return false;
        }
    };

    private void loadFrag(Fragment frag){
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        trans.replace(R.id.frag_holder,frag);
        trans.addToBackStack(null);
        trans.commit();
    }


    public void parseJson() {
        //EditText editText = findViewById(R.id.editText);
        //String zipCode = editText.getText().toString();
        String url = "https://www.thecocktaildb.com/api/json/v1/1/search.php?s=blue+margarita";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONArray drinks = response.getJSONArray("drinks");
                        for (int i = 0; i < drinks.length(); i++) {
                            JSONObject drink = drinks.getJSONObject(i);
                            String drinkID = drink.getString("idDrink");
                            String drinkName = drink.getString("strDrink");
                            String catType = drink.getString("strCategory");
                            String alcoholic = drink.getString("strAlcoholic");
                            String glassType = drink.getString("strGlass");
                            String imageURL = drink.getString("strDrinkThumb");
                            String inst = drink.getString("strInstructions");
                            String ing1 = drink.getString("strIngredient1");
                            String ing2 = drink.getString("strIngredient2");
                            String ing3 = drink.getString("strIngredient3");
                            String ing4 = drink.getString("strIngredient4");
                            String ing5 = drink.getString("strIngredient5");
                            String ing6 = drink.getString("strIngredient6");
                            String ing7 = drink.getString("strIngredient7");
                            String ing8 = drink.getString("strIngredient8");
                            String ing9 = drink.getString("strIngredient9");
                            String ing10 = drink.getString("strIngredient10");
                            String ing11 = drink.getString("strIngredient11");
                            String ing12 = drink.getString("strIngredient12");
                            String ing13 = drink.getString("strIngredient13");
                            String ing14 = drink.getString("strIngredient14");
                            String ing15 = drink.getString("strIngredient15");
                            String meas1 = drink.getString("strMeasure1");
                            String meas2 = drink.getString("strMeasure2");
                            String meas3 = drink.getString("strMeasure3");
                            String meas4 = drink.getString("strMeasure4");
                            String meas5 = drink.getString("strMeasure5");
                            String meas6 = drink.getString("strMeasure6");
                            String meas7 = drink.getString("strMeasure7");
                            String meas8 = drink.getString("strMeasure8");
                            String meas9 = drink.getString("strMeasure9");
                            String meas10 = drink.getString("strMeasure10");
                            String meas11 = drink.getString("strMeasure11");
                            String meas12 = drink.getString("strMeasure12");
                            String meas13 = drink.getString("strMeasure13");
                            String meas14 = drink.getString("strMeasure14");
                            String meas15 = drink.getString("strMeasure15");


                            textView3.append(drinkName +
                                    "\nCategory:  " + catType +
                                    "\nGlass Type:  " + glassType +
                                    "\nIngredients:\n" +
                                    meas1 + "\t" + ing1 + "\n" +
                                    meas2 + "\t" + ing2 + "\n" +
                                    meas3 + "\t" + ing3 + "\n" +
                                    meas4 + "\t" + ing4 + "\n" +
                                    meas5 + "\t" + ing5 + "\n" +
                                    meas6 + "\t" + ing6 + "\n" +
                                    meas7 + "\t" + ing7 + "\n" +
                                    meas8 + "\t" + ing8 + "\n" +
                                    meas9 + "\t" + ing9 + "\n" +
                                    meas10 + "\t" + ing10 + "\n" +
                                    meas11 + "\t" + ing11 + "\n" +
                                    meas12 + "\t" + ing12 + "\n" +
                                    meas13 + "\t" + ing13 + "\n" +
                                    meas14 + "\t" + ing14 + "\n" +
                                    meas15 + "\t" + ing15 + "\n" +
                                    inst);
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> error.printStackTrace());

        mQueue.add(request);


    }


}
