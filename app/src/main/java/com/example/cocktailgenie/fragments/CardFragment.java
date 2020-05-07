package com.example.cocktailgenie.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.cocktailgenie.R;

public class CardFragment extends Fragment {

    //Card Fragment to provide popup drink recipe card

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //This inflates the layout
        View view = inflater.inflate(R.layout.drink_pop, container, false);

        return view;
    }

}
