package com.example.servigox1.HelperClasses;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.servigox1.AddPlace;
import com.example.servigox1.Dashboard;
import com.example.servigox1.R;
import com.example.servigox1.StationDetails2;
import com.example.servigox1.StationDetailsFragment;

import java.util.ArrayList;

public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedAdapter.FeaturedViewHolder> {

    ArrayList<FeaturedHelper> featuredLocations;

    public FeaturedAdapter(ArrayList<FeaturedHelper> featuredLocations){
        this.featuredLocations = featuredLocations;
    }

    @NonNull
    @Override
    public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_card_design,parent,false);
        FeaturedViewHolder featuredViewHolder = new FeaturedViewHolder(view);

        return featuredViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedViewHolder holder, int position) {
        FeaturedHelper featuredHelper = featuredLocations.get(position);

        holder.image.setImageResource(R.drawable.garage1);
        holder.title.setText(featuredHelper.getName());
        holder.description.setText(featuredHelper.getAddress());


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity)v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.drawer_layout, new StationDetails2(R.drawable.garage1,featuredHelper.getName(),featuredHelper.getAddress(), featuredHelper.getContact())).addToBackStack(null).commit();
            }
        });

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(String.valueOf(AddPlace.class));
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return featuredLocations.size();
    }

    public static class FeaturedViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title, description, isSuitable;
        CardView cardView;
        public FeaturedViewHolder(View itemView){
            super(itemView);

            //Hooks
            image = itemView.findViewById(R.id.featured_image);
            title = itemView.findViewById(R.id.featured_title);
            description = itemView.findViewById(R.id.featured_desc);
            cardView = itemView.findViewById(R.id.featured_card);

        }
    }

}
