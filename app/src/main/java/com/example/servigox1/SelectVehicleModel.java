package com.example.servigox1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;

public class SelectVehicleModel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_vehicle_model);

        TextView t1, t2, t3, t4;

        t1 = findViewById(R.id.vModel);
        t2 = findViewById(R.id.vCompany);
        t3 = findViewById(R.id.vType);
        t4 = findViewById(R.id.uContact);

        Intent intent = getIntent();
        String type = intent.getStringExtra("VehicleType");
        String company = intent.getStringExtra("VehicleName");
        String model = intent.getStringExtra("VehicleModel");

        SessionManager sessionManager = new SessionManager(this, SessionManager.SESSION_USERSESSION);
        HashMap<String, String> userDetailsFromSession = sessionManager.getUserDetailsFromSession();
        String contact = userDetailsFromSession.get(SessionManager.KEY_CONTACT);

        t1.setText(type);
        t2.setText(company);
        t3.setText(model);
        t4.setText(contact);
    }
}