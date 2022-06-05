package com.example.servigox1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.servigox1.HelperClasses.TestingAdapter;
import com.example.servigox1.HelperClasses.TestingHelper;
import com.example.servigox1.HelperClasses.ViewAllAdapter;
import com.example.servigox1.HelperClasses.ViewAllHelper;

import java.util.ArrayList;

public class Testing extends AppCompatActivity {

    RecyclerView viewAll_recycler;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        viewAll_recycler = findViewById(R.id.jfk);
        viewAll_recycler();
    }

    private void viewAll_recycler() {
        viewAll_recycler.setHasFixedSize(true);
        viewAll_recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        ArrayList<TestingHelper> testingHelperArrayList = new ArrayList<>();

        testingHelperArrayList.add(new TestingHelper("Mad Max: Fury Road","Action & Adventure","2015"));
        testingHelperArrayList.add(new TestingHelper("Inside Out","Animation, Kids and family","2015"));
        testingHelperArrayList.add(new TestingHelper("Star Wars: Episode VII","Action","2015"));
        testingHelperArrayList.add(new TestingHelper("Fast and Furious 7","Action & Adventure","2015"));
        testingHelperArrayList.add(new TestingHelper("Mission Impossible : Rogue Nation","Action & Adventure","2015"));
        testingHelperArrayList.add(new TestingHelper("Fast and Furious 8","Action & Adventure","2019"));
        testingHelperArrayList.add(new TestingHelper("Up","Animation","2009"));
        testingHelperArrayList.add(new TestingHelper("American Pie","Censored","2008"));
        

        adapter = new TestingAdapter(testingHelperArrayList,this);
        viewAll_recycler.setAdapter(adapter);


    }
}