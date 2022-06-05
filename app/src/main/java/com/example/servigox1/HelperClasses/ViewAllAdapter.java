package com.example.servigox1.HelperClasses;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.servigox1.AddPlace;
import com.example.servigox1.R;
import com.example.servigox1.StationDetails2;
import com.example.servigox1.StationDetailsFragment;

import java.util.ArrayList;

public class ViewAllAdapter extends RecyclerView.Adapter<ViewAllAdapter.ViewAllViewHolder>{
    private Context context;
    ArrayList<ViewAllHelper> viewAllAdapterArrayList;

    public ViewAllAdapter(Context context) {
        this.context = context;
    }

    public ViewAllAdapter(Context context,ArrayList<ViewAllHelper> viewAllAdapterArrayList) {
        this.viewAllAdapterArrayList = viewAllAdapterArrayList;
    }

    @NonNull
    @Override
    public ViewAllViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_all_card_design,parent,false);
        ViewAllViewHolder viewAllViewHolder = new ViewAllViewHolder(view);

        return viewAllViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAllViewHolder holder, int position) {
        ViewAllHelper viewAllHelper = viewAllAdapterArrayList.get(position);

        holder.image.setImageResource(R.drawable.garage1);
        holder.title.setText(viewAllHelper.getName());
        holder.description.setText(viewAllHelper.getAddress());
        holder.isSuitable.setText(viewAllHelper.getProvideServiceFor());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity appCompatActivity = (AppCompatActivity)v.getContext();
                Fragment fragment = new StationDetails2(R.drawable.garage1,viewAllHelper.getName(),viewAllHelper.getAddress(), viewAllHelper.getContact());
                appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.viewAll_Frame,fragment).addToBackStack(null).commit();
//                Intent intent = new Intent(String.valueOf(AddPlace.class));
//                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return viewAllAdapterArrayList.size();
    }

    public static class ViewAllViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title, description, isSuitable;
        CardView cardView;

        public ViewAllViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.viewAll_image);
            title = itemView.findViewById(R.id.viewAll_title);
            description = itemView.findViewById(R.id.viewAll_desc);
            isSuitable = itemView.findViewById(R.id.viewAll_is_suitable);
            cardView = itemView.findViewById(R.id.viewAll_card);
        }
    }
}

