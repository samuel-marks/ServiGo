package com.example.servigox1;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import android.widget.AdapterView;

import android.widget.ArrayAdapter;

import android.widget.Button;

import android.widget.Spinner;

import android.widget.Toast;

import com.example.servigox1.HelperClasses.AddVehicleHelper;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Random;


public class SelectBikeCompany extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {



    Spinner vehicle_company;
    Spinner vehicle_model;
    Button Add_Vehicle;

    String []Bike={"Honda","Suzuki","Yamaha"};







    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_select_bike_company);


        Add_Vehicle=(Button) findViewById(R.id.bt1);


        vehicle_company=(Spinner) findViewById(R.id.spinnervehiclecompany);
        vehicle_model=(Spinner) findViewById(R.id.spinnervehiclemodel);

        ArrayAdapter ad=new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,Bike);

        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        vehicle_company.setAdapter(ad);

        vehicle_company.setOnItemSelectedListener(this);



    }



    @Override

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {



        if(Bike[position].equals("Honda"))

        {
            String[] Honda={"Activa","Dio","Unicorn","Cbr","Hornet"};


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


                            Intent intent=new Intent(SelectBikeCompany.this,UserProfile.class);

//                            intent.putExtra("VehicleType",vehicleType);
//                            intent.putExtra("VehicleName",vehicle_company.getSelectedItem().toString());
//                            intent.putExtra("VehicleModel",vehicle_model.getSelectedItem().toString());

                            SessionManager sessionManager = new SessionManager(SelectBikeCompany.this, SessionManager.SESSION_USERSESSION);
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
                            startActivity(intent);


                        }
                    });




                }



                @Override

                public void onNothingSelected(AdapterView<?> parent) {



                }

            });}

        else if(Bike[position].equals("Suzuki"))

        {
            String[] Suzuki={"Access","Swish","Hayabusa"};


            ArrayAdapter ad2=new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,Suzuki);

            ad2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            vehicle_model.setAdapter(ad2);








        }
        else if(Bike[position].equals("Yamaha"))

        {
            String[] Yamaha={"R15","Fazer","Fascino","R1"};


            ArrayAdapter ad3=new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,Yamaha);

            ad3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            vehicle_model.setAdapter(ad3);

        }










    }



    @Override

    public void onNothingSelected(AdapterView<?> parent) {



    }





}