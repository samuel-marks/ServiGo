package com.example.servigox1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.servigox1.HelperClasses.GetBookingHelper;
import com.example.servigox1.HelperClasses.GetBookingsAdapter;
import com.example.servigox1.HelperClasses.ViewAllHelper;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class UserBookings extends AppCompatActivity {

    RecyclerView get_bookings_recycler;
    RecyclerView.Adapter adapter;
    DatabaseReference reference;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_bookings);

        progressBar = findViewById(R.id.pgbar);
        get_bookings_recycler = findViewById(R.id.bookings_recyclerView);
        setGet_bookings_recycler();
    }

    private void setGet_bookings_recycler(){
        get_bookings_recycler.setHasFixedSize(true);
        get_bookings_recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        ArrayList<GetBookingHelper> arrayList = new ArrayList<>();

//        arrayList.add(new GetBookingHelper("30-01-2022","Brake Pads Change \nCoolant Change \nChain Lube / Chain clean \nBrake oil Change \nAddition work to be done: ","9153483562","Sai Service","9370783982","Sujata Patole"));
//        arrayList.add(new GetBookingHelper("30-01-2022","Brake Pads Change \nCoolant Change \nChain Lube / Chain clean \nBrake oil Change \nAddition work to be done: ","9153483562","Sai Service","9370783982","Sujata Patole"));
//        arrayList.add(new GetBookingHelper("30-01-2022","Brake Pads Change \nCoolant Change \nChain Lube / Chain clean \nBrake oil Change \nAddition work to be done: ","9153483562","Sai Service","9370783982","Sujata Patole"));
//        arrayList.add(new GetBookingHelper("30-01-2022","Brake Pads Change \nCoolant Change \nChain Lube / Chain clean \nBrake oil Change \nAddition work to be done: ","9153483562","Sai Service","9370783982","Sujata Patole"));
//        arrayList.add(new GetBookingHelper("30-01-2022","Brake Pads Change \nCoolant Change \nChain Lube / Chain clean \nBrake oil Change \nAddition work to be done: ","9153483562","Sai Service","9370783982","Sujata Patole"));
//        arrayList.add(new GetBookingHelper("30-01-2022","Brake Pads Change \nCoolant Change \nChain Lube / Chain clean \nBrake oil Change \nAddition work to be done: ","9153483562","Sai Service","9370783982","Sujata Patole"));
//        arrayList.add(new GetBookingHelper("30-01-2022","Brake Pads Change \nCoolant Change \nChain Lube / Chain clean \nBrake oil Change \nAddition work to be done: ","9153483562","Sai Service","9370783982","Sujata Patole"));
//        arrayList.add(new GetBookingHelper("30-01-2022","Brake Pads Change \nCoolant Change \nChain Lube / Chain clean \nBrake oil Change \nAddition work to be done: ","9153483562","Sai Service","9370783982","Sujata Patole"));
//
//

        SessionManager sessionManager = new SessionManager(this, SessionManager.SESSION_USERSESSION);
        HashMap<String ,String> userDetails = sessionManager.getUserDetailsFromSession();

        String contact = userDetails.get(SessionManager.KEY_CONTACT);

        assert contact != null;
        reference = FirebaseDatabase.getInstance().getReference("Bookings").child("forUsers").child(contact);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    GetBookingHelper getBookingHelper = dataSnapshot.getValue(GetBookingHelper.class);
                    arrayList.add(getBookingHelper);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        adapter = new GetBookingsAdapter(this,arrayList);
        get_bookings_recycler.setAdapter(adapter);
        progressBar.setVisibility(View.GONE);

    }

    public void gotoHome(View view) {
        Intent intent = new Intent(UserBookings.this, Dashboard.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(UserBookings.this,Dashboard.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}