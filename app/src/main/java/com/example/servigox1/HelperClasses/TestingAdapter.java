package com.example.servigox1.HelperClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.servigox1.R;

import java.util.ArrayList;

public class TestingAdapter extends RecyclerView.Adapter<TestingAdapter.TestingViewHolder> {

    ArrayList<TestingHelper> testingHelperArrayList;
    Context context;

    public TestingAdapter(ArrayList<TestingHelper> testingHelperArrayList, Context context) {
        this.testingHelperArrayList = testingHelperArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public TestingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.testing_card,parent,false);
        TestingViewHolder testingViewHolder = new TestingViewHolder(view);

        return testingViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TestingViewHolder holder, int position) {

        TestingHelper testingHelper = testingHelperArrayList.get(position);
        holder.movieName.setText(testingHelper.getMovieName());
        holder.releaseYear.setText(testingHelper.getReleaseYear());
        holder.genre.setText(testingHelper.getGenre());
    }

    @Override
    public int getItemCount() {
        return testingHelperArrayList.size();
    }

    public static class TestingViewHolder extends RecyclerView.ViewHolder {

        TextView movieName, genre, releaseYear;

        public TestingViewHolder(@NonNull View itemView) {
            super(itemView);

            movieName = itemView.findViewById(R.id.moviename);
            genre = itemView.findViewById(R.id.genre);
            releaseYear = itemView.findViewById(R.id.releaseyear);
        }
    }
}
