package com.example.servigox1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SelectVehicle extends AppCompatActivity {

    CardView car, bike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_vehicle);

        car = findViewById(R.id.carCard);
        bike = findViewById(R.id.bikeCard);

        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectVehicle.this, SelectCarCompany.class);
                intent.putExtra("VehicleType","Car");
                startActivity(intent);
                finish();
            }
        });

        bike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectVehicle.this, SelectBikeCompany.class);
                intent.putExtra("VehicleType","Bike");
                startActivity(intent);
                finish();
            }
        });
    }

    public void gotoHome(View view) {
        Intent intent = new Intent(SelectVehicle.this, Dashboard.class);
        startActivity(intent);
        finish();
    }
}