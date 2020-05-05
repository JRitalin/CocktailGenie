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
import com.example.cocktailgenie.fragments.HomeFragment;

import java.util.ArrayList;

public class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.DrinkViewHolder> {

    private Context ctx;
    private ArrayList<Drink> drinks;


    public DrinkAdapter(Context context, ArrayList<Drink> drinks) {
        this.ctx = context;
        this.drinks = drinks;
    }

    @NonNull
    @Override
    public DrinkAdapter.DrinkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drink_card,
                parent, false);
        return new DrinkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinkAdapter.DrinkViewHolder holder, int position) {
        final Drink drinks1 = drinks.get(position);
        holder.drinkName.setText(drinks1.getStrDrink());
        String drinkURL = drinks1.getStrDrinkThumb();
        Glide.with(ctx).load(drinkURL).thumbnail(.5f).into(holder.drinkImg);

        holder.drinkCard.setOnClickListener(v -> {
            Intent intent = new Intent(ctx, HomeFragment.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ctx.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return drinks.size();
    }

    static class DrinkViewHolder extends RecyclerView.ViewHolder {

        TextView drinkName;
        ImageView drinkImg;
        LinearLayout drinkCard;


        DrinkViewHolder(@NonNull View itemView) {
            super(itemView);

            drinkName = itemView.findViewById(R.id.drink_name);
            drinkImg = itemView.findViewById(R.id.thumbnail);
            drinkCard = itemView.findViewById(R.id.drink_card);
        }
    }


}
