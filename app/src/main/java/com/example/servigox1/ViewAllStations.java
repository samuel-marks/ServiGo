package com.example.servigox1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.servigox1.HelperClasses.ViewAllAdapter;
import com.example.servigox1.HelperClasses.ViewAllHelper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewAllStations extends AppCompatActivity {
    RecyclerView viewAll_recycler;
    RecyclerView.Adapter adapter;
    DatabaseReference reference;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_stations);
//        progressBar = findViewById(R.id.pgbar);
//        progressBar.setVisibility(View.VISIBLE);
        viewAll_recycler = findViewById(R.id.viewAll_recycler);
        viewAll_recycler();
    }

    private void viewAll_recycler() {
        viewAll_recycler.setHasFixedSize(true);
        viewAll_recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        ArrayList<ViewAllHelper> viewAllHelperArrayList = new ArrayList<>();

//        viewAllHelperArrayList.add(new ViewAllHelper(R.drawable.g4,"Dorge Honda","MIT pasun Wadia paryant aplich hawa","*This service station is suitable for your selected vehicle"));
//        viewAllHelperArrayList.add(new ViewAllHelper(R.drawable.g4,"Sam's Jeep","MIT pasun Wadia paryant aplich hawa","*This service station is suitable for your selected vehicle"));
//        viewAllHelperArrayList.add(new ViewAllHelper(R.drawable.g4,"Saraf Hyundai","MIT pasun Wadia paryant aplich hawa","*This service station is suitable for your selected vehicle"));
//        viewAllHelperArrayList.add(new ViewAllHelper(R.drawable.g4,"Varma Gaadiwale","MIT pasun Wadia paryant aplich hawa","*This service station is suitable for your selected vehicle"));
//        viewAllHelperArrayList.add(new ViewAllHelper(R.drawable.g4,"Aai Cars","MIT pasun Wadia paryant aplich hawa","*This service station is suitable for your selected vehicle"));
//        viewAllHelperArrayList.add(new ViewAllHelper(R.drawable.g4,"Dangare Cars","MIT pasun Wadia paryant aplich hawa","*This service station is suitable for your selected vehicle"));
//        viewAllHelperArrayList.add(new ViewAllHelper(R.drawable.g4,"Pradhan Toyota","MIT pasun Wadia paryant aplich hawa","*This service station is suitable for your selected vehicle"));
//        viewAllHelperArrayList.add(new ViewAllHelper(R.drawable.g4,"Patole Tata","MIT pasun Wadia paryant aplich hawa","*This service station is suitable for your selected vehicle"));
//        viewAllHelperArrayList.add(new ViewAllHelper(R.drawable.g4,"Bikaner Garage","MIT pasun Wadia paryant aplich hawa","*This service station is suitable for your selected vehicle"));
//        viewAllHelperArrayList.add(new ViewAllHelper(R.drawable.g4,"Jack's Bike Service Point","MIT pasun Wadia paryant aplich hawa","*This service station is suitable for your selected vehicle"));
//        viewAllHelperArrayList.add(new ViewAllHelper(R.drawable.g4,"Joseph Car Services","MIT pasun Wadia paryant aplich hawa","*This service station is suitable for your selected vehicle"));



        reference = FirebaseDatabase.getInstance().getReference("Stations");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    ViewAllHelper viewAllHelper = dataSnapshot.getValue(ViewAllHelper.class);
                    viewAllHelperArrayList.add(viewAllHelper);

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//
        adapter = new ViewAllAdapter(this,viewAllHelperArrayList);
//        progressBar = findViewById(R.id.pgbar);
//        progressBar.setVisibility(View.GONE);
        viewAll_recycler.setAdapter(adapter);
    }

    public void gotoHome(View view) {
    }
}