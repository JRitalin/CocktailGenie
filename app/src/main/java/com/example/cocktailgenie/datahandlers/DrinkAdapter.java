package com.example.cocktailgenie.datahandlers;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cocktailgenie.R;
import com.example.cocktailgenie.fragments.CardFragment;

import java.util.ArrayList;


//Adapter for Recycler View to allow recycler view
public class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.ViewHolder> {

//variables
    private Context ctx;
    private ArrayList<Drink> drinks;


//constructor
    public DrinkAdapter(Context context, ArrayList<Drink> drinks) {
        this.ctx = context;
        this.drinks = drinks;
    }

    //viewholder creation
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drink_card,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinkAdapter.ViewHolder holder, int position) {

        Drink drinks1 = drinks.get(position);

        holder.drinkName.setText(drinks1.getStrDrink());
        String drinkURL = drinks1.getStrDrinkThumb();
        Glide.with(ctx).load(drinkURL).thumbnail(.5f).into(holder.drinkImg);

        //onclick listener for cards in viewholder
        holder.drinkCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ctx, CardFragment.class);
                ctx.startActivity(intent);

            }
        });


    }


    //count of items in current viewholder
    @Override
    public int getItemCount() {
        return drinks.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        //ViewHolder for recycler view
        TextView drinkName;
        ImageView drinkImg;
        LinearLayout drinkCard;


        ViewHolder(@NonNull View itemView) {
            super(itemView);

            drinkName = itemView.findViewById(R.id.drink_name);
            drinkImg = itemView.findViewById(R.id.thumbnail);
            drinkCard = itemView.findViewById(R.id.drink_card);
        }
    }

    static class DrinkPopViewHolder extends RecyclerView.ViewHolder {
        //ViewHolder for card popup
        TextView drinkName;
        ImageView drinkImg;


        public DrinkPopViewHolder (View itemView){
            super(itemView);

        }

    }

}
