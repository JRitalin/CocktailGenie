package com.example.cocktailgenie.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cocktailgenie.R;
import com.example.cocktailgenie.datahandlers.Drink;
import com.example.cocktailgenie.datahandlers.DrinkAdapter;
import com.example.cocktailgenie.datahandlers.MySingleton;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {


    private static final String URL =
            "https://www.thecocktaildb.com/api/json/v2/9973533/randomselection.php";

    private ArrayList<Drink> drinkList = new ArrayList<Drink>();
    private RecyclerView recyclerView;
    DrinkAdapter drinkAdapter;

    public HomeFragment() {
        // Required Empty Constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //This inflates the layout
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.home_recycler_view);


        drinkList = new ArrayList<>();
        drinkAdapter = new DrinkAdapter(getActivity(), drinkList);

        recyclerView.setAdapter(drinkAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        fetchHomeItems();

        return view;
    }

    //provides a random list of 10 items
    private void fetchHomeItems() {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,
                response -> {

                    if (response == null) {

                        Toast.makeText(getActivity(), "Couldn't fetch random results",
                                Toast.LENGTH_LONG).show();
                        return;

                    }

                    try {
                        JSONArray jArray = response.getJSONArray("drinks");
                        Gson gson = new Gson();

                        for (int i = 0; i < jArray.length(); i++) {
                            JSONObject drinkObject = jArray.getJSONObject(i);
                            Drink drink = gson.fromJson(String.valueOf(drinkObject), Drink.class);
                            drinkList.add(drink);
                        }
                        drinkAdapter = new DrinkAdapter(getActivity(), drinkList);
                        recyclerView.setAdapter(drinkAdapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }, error -> error.printStackTrace());

        MySingleton.getInstance().addToRequestQueue(request);

    }

}
