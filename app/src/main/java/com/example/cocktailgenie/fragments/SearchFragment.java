package com.example.cocktailgenie.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
import java.util.List;


//search fragment allows for searching in multiple ways
public class SearchFragment extends Fragment implements AdapterView.OnItemSelectedListener{

//variables
    private static final String URL_DRINK_NAME =
            "https://www.thecocktaildb.com/api/json/v2/9973533/search.php?s=";
    private static final String URL_ALCOHOL_TYPE =
            "https://www.thecocktaildb.com/api/json/v2/9973533/filter.php?i=";
    private static final String URL_LETTER_SEARCH =
            "https://www.thecocktaildb.com/api/json/v2/9973533/search.php?f=";
    private String URL = null;
    String URLstring = null;

    private ArrayList<Drink> drinkList = new ArrayList<>();
    private RecyclerView recyclerView;
    private DrinkAdapter drinkAdapter;
    private Spinner spinner;
    private EditText searchText;
    private Button button;



    public SearchFragment() {
        // Required Empty Constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //initialize view
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //This inflates the layout
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        recyclerView = view.findViewById(R.id.search_recycler_view);
        spinner = view.findViewById(R.id.drink_choice_spinner);
        button = view.findViewById(R.id.search_button);
        searchText = view.findViewById(R.id.search_text);



        drinkList = new ArrayList<>();
        drinkAdapter = new DrinkAdapter(getActivity(), drinkList);

        recyclerView.setAdapter(drinkAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        List<String> searchCat = new ArrayList<>();
        searchCat.add(0, "Search By");
        searchCat.add("Drink Name");
        searchCat.add("Alcohol Type");
        searchCat.add("First Letter");

        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter(this.getActivity(),
                android.R.layout.simple_spinner_item, searchCat);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(this);

        //on click for search button
        button.setOnClickListener(v -> {
            searchText.onEditorAction(EditorInfo.IME_ACTION_DONE);
            String searchTerm = searchText.getText().toString();
            URLstring = URL+searchTerm;
            fetchSearchItems();

        });
        return view;
    }

    //this allows for selections on the spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        String selected = parent.getItemAtPosition(pos).toString();
        Toast.makeText(this.getActivity(),selected,Toast.LENGTH_SHORT).show();

        if (selected.equals("Drink Name")) {
            URL = URL_DRINK_NAME;
        }else if(selected.equals("Alcohol Type")){
            URL = URL_ALCOHOL_TYPE;
        }else if(selected.equals("First Letter")){
            URL = URL_LETTER_SEARCH;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //main search function
    private void fetchSearchItems() {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URLstring, null,
                response -> {

                    if (response == null) {

                        Toast.makeText(getActivity(), "Couldn't fetch random results",
                                Toast.LENGTH_LONG).show();
                        return;

                    }
                    drinkList.clear();
                    drinkAdapter.notifyDataSetChanged();
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