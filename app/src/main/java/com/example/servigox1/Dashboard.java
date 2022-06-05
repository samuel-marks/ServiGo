package com.example.servigox1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.servigox1.HelperClasses.FeaturedAdapter;
import com.example.servigox1.HelperClasses.FeaturedHelper;
import com.example.servigox1.HelperClasses.GetVehicleHelper;
import com.example.servigox1.HelperClasses.ViewAllHelper;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView featured_recycler;
    RecyclerView.Adapter adapter;

    //Drawer Menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    DatabaseReference reference;

    ArrayAdapter<GetVehicleHelper> getVehicleHelperArrayAdapter;

    TextView textView;
    Spinner spinner;
    ImageView menuIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);

        //hooks

        featured_recycler = findViewById(R.id.featured_recycler);
        Featured_recycler();
        menuIcon = findViewById(R.id.menu_icon);

        //Menu hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);


        navigationDrawer();

        textView = findViewById(R.id.welcome_id);
        spinner = findViewById(R.id.dashboard_spinner_city);

        SessionManager sessionManager = new SessionManager(Dashboard.this, SessionManager.SESSION_USERSESSION);
        HashMap<String, String> userDetailsFromSession = sessionManager.getUserDetailsFromSession();

//        String firstname = userDetailsFromSession.get(SessionManager.KEY_FULLNAME);

        String firstname = "Vedanti Saraf";
        assert firstname != null;
        firstname = firstname.trim();
        String[] newfirstname = firstname.split(" ");


        if (sessionManager.checkLogin()) {

            textView.setText(new StringBuilder().append("Welcome, ").append(newfirstname[0]).append(" "));

        }

//        String contact = userDetailsFromSession.get(SessionManager.KEY_CONTACT);
        String contact = "8888625075";
        assert contact != null;
//        reference = FirebaseDatabase.getInstance().getReference("Vehicles").child(contact);
//
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
//
//                    GetVehicleHelper getVehicleHelper = dataSnapshot.getValue(GetVehicleHelper.class);
//                    getVehicleHelperArrayAdapter.add(getVehicleHelper);
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//        getVehicleHelperArrayAdapter = new ArrayAdapter(this,GetVehicleHelper.class);
//

        Spinner dropdown = spinner;
        String[] items = new String[]{"Mumbai",
                "Pune",
                "Bangalore",
                "Hyderabad",
                "Nashik"};

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(spinnerAdapter);

        spinner.setSelection(spinnerAdapter.getPosition(userDetailsFromSession.get(SessionManager.KEY_CITY)));





        spinner.setSelection(spinnerAdapter.getPosition(userDetailsFromSession.get(SessionManager.KEY_CITY)));



        Button button = findViewById(R.id.viewAll_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this,ViewAllStations.class);
                startActivity(intent);
            }
        });

    }

    private void navigationDrawer() {
        //Navigation drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        menuIcon.setOnClickListener(v -> {
            if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);

        } else {
            super.onBackPressed();
        }
    }

    private void Featured_recycler() {
        featured_recycler.setHasFixedSize(true);
        featured_recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<FeaturedHelper> featuredLocations = new ArrayList<>();

//        featuredLocations.add(new FeaturedHelper(R.drawable.garage1, "Saraf Hundai", "Sangamner pasun Pune paryant aplich hawa"));
//        featuredLocations.add(new FeaturedHelper(R.drawable.garage3, "Varma Gaadiwale", "Sangamner pasun Pune paryant aplich hawa"));
//        featuredLocations.add(new FeaturedHelper(R.drawable.g4, "Aai Cars", "Sangamner pasun Pune paryant aplich hawa"));


        reference = FirebaseDatabase.getInstance().getReference("Stations");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    FeaturedHelper featuredHelper = dataSnapshot.getValue(FeaturedHelper.class);
                    featuredLocations.add(featuredHelper);

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        adapter = new FeaturedAdapter(featuredLocations);
        featured_recycler.setAdapter(adapter);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.nav_home:
                break;
            case R.id.nav_login:
                Intent intent = new Intent(Dashboard.this, LoginX.class);
                startActivity(intent);
                finish();
                break;
            case R.id.nav_register:
                Intent intent2 = new Intent(Dashboard.this, RegisterX.class);
                startActivity(intent2);
                finish();
                break;
            case R.id.nav_profile:
                Intent intent3 = new Intent(Dashboard.this, UserProfile.class);
                startActivity(intent3);
                finish();
                break;
            case R.id.nav_add_vehicle:
                Intent intent4 = new Intent(Dashboard.this, SelectVehicle.class);
                startActivity(intent4);
                finish();
                break;
            case R.id.nav_get_bookings:
                Intent intent5 = new Intent(Dashboard.this, UserBookings.class);
                startActivity(intent5);
                finish();
                break;
            case R.id.nav_logout:
                Intent intent6 = new Intent(Dashboard.this, Testing.class);
                startActivity(intent6);
                finish();
                break;
        }

        return true;
    }

    public void addPlace(View view) {

        Intent intent = new Intent(Dashboard.this, AddPlace.class);
        startActivity(intent);

    }

    public void goToStation(View view) {
        Intent intent = new Intent(Dashboard.this, AddPlace.class);
        startActivity(intent);
    }

    public void bikeService(View view) {
        Intent intent = new Intent(Dashboard.this, AddPlace.class);
        startActivity(intent);
    }

    public void carService(View view) {
        Intent intent = new Intent(Dashboard.this, AddPlace.class);
        startActivity(intent);
    }

    public void getServiceStations(View view) {
        Intent intent = new Intent(Dashboard.this, AddPlace.class);
        startActivity(intent);
    }

    public void getGarages(View view) {
        Intent intent = new Intent(Dashboard.this, AddPlace.class);
        startActivity(intent);
    }

    public void getHelp(View view) {
        Intent intent = new Intent(Dashboard.this, GetHelp.class);
        startActivity(intent);
    }
}