package com.example.servigox1;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import android.widget.AdapterView;

import android.widget.ArrayAdapter;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import android.widget.Toast;

import com.example.servigox1.HelperClasses.AddVehicleHelper;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Random;


public class SelectCarCompany extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {



    Spinner vehicle_model;
    Spinner vehicle_company;

    Button Add_Vehicle;

    String [] Car={"Honda","Hyundai","Tata"};







    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_select_car_company);


        Add_Vehicle=(Button) findViewById(R.id.bt1);
        vehicle_model=(Spinner) findViewById(R.id.spinnervehiclemodel);

        vehicle_company=(Spinner) findViewById(R.id.spinnervehiclecompany);

        ArrayAdapter ad=new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,Car);

        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        vehicle_company.setAdapter(ad);

        vehicle_company.setOnItemSelectedListener(this);



    }



    @Override

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {



        if(Car[position].equals("Honda"))

        {
            String[] Honda={"Civic","City","Jazz","Brv"};


            ArrayAdapter ad1=new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,Honda);

            ad1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            vehicle_model.setAdapter(ad1);

            vehicle_model.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override

                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    Add_Vehicle.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {


                            Intent intent1 = getIntent();
                            String vehicleType = intent1.getStringExtra("VehicleType");
                            String vehicleCompany = vehicle_company.getSelectedItem().toString();
                            String vehicleModel = vehicle_model.getSelectedItem().toString();

                            Intent intent=new Intent(SelectCarCompany.this,Dashboard.class);

                            SessionManager sessionManager = new SessionManager(SelectCarCompany.this, SessionManager.SESSION_USERSESSION);
                            HashMap<String, String> userDetailsFromSession = sessionManager.getUserDetailsFromSession();
                            String contact = userDetailsFromSession.get(SessionManager.KEY_CONTACT);

                            Random random = new Random();
                            int randValue = random.nextInt(999999999);
                            String randKey = String.valueOf(randValue);

                            AddVehicleHelper addVehicleHelper = new AddVehicleHelper(vehicleType, vehicleCompany, vehicleModel);

                            assert contact != null;
                            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Vehicles").child(contact);
                            reference.child(randKey).setValue(addVehicleHelper);

                            Toast.makeText(getApplicationContext(), "Vehicle Added Successfully", Toast.LENGTH_SHORT).show();

//                            intent.putExtra("VehicleType",vehicleType);
//                            intent.putExtra("VehicleName",vehicle_company.getSelectedItem().toString());
//                            intent.putExtra("VehicleModel",vehicle_model.getSelectedItem().toString());
                            startActivity(intent);


                        }
                    });




                }



                @Override

                public void onNothingSelected(AdapterView<?> parent) {



                }

            });








        }

        else if(Car[position].equals("Hyundai"))

        {

            String[] Hyundai={"Verna","Creta","I20"};

            ArrayAdapter ad4=new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,Hyundai);

            ad4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            vehicle_model.setAdapter(ad4);



        }

        else if(Car[position].equals("Tata"))

        {
            String[] Tata={"Nexon","Indica","Sumo"};

            ArrayAdapter ad9=new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,Tata);

            ad9.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            vehicle_model.setAdapter(ad9);



        }






    }



    @Override

    public void onNothingSelected(AdapterView<?> parent) {



    }





}