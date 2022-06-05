package com.example.servigox1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class AddNewVehicle extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_vehicle);


    }

    public void gotoHome(View view) {
        Intent intent = new Intent(AddNewVehicle.this, Dashboard.class);
        startActivity(intent);
        finish();
    }
}