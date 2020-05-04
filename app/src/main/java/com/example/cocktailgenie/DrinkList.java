package com.example.cocktailgenie;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.squareup.picasso.Picasso;

public class DrinkList extends RecyclerView.Adapter<DrinkList.ViewHolder> {

    private List<Drink> drinksList;
    private Context context;

    /**
     * No args constructor for use in serialization
     */

    public DrinkList(List<Drink> drinksList,Context context){
        this.drinksList = drinksList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_home,parent,
                false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Drink drinksList = drinksLists.get(position);
        holder.drink.setText(drinksList.getStrDrink());

        Picasso.with(context)
                .load(drinksList.getStrDrinkThumb())
                .into(holder.drinkPic_url);
        holder.linearLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Drink drinksList1 = drinksLists.get(position);
                Intent skipIntent = new Intent(v.getContext(),)
            }
        });

    }

    @Override
    public int getItemCount() {
        return drinksList.size();
    }

    /**
     * @param drinks
     */
    public DrinkList(List<Drink> drinks) {
        super();
        this.drinksList = new ArrayList<>();
        this.drinksList = drinks;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}