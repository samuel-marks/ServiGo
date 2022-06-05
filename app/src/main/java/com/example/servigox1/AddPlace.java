package com.example.servigox1;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.servigox1.HelperClasses.AddPlaceHelper;
import com.example.servigox1.HelperClasses.RegisterHelper;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class AddPlace extends AppCompatActivity {

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    TextInputLayout name;
    TextInputLayout address;
    TextInputLayout contact;
    Button submit;
    RadioGroup isAuthorizedServiceCentre, isTowingService, provideServiceFor;
    RadioButton isAS;
    RadioButton isTS;
    RadioButton pSF;
    ProgressBar progressBar;
    Spinner city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);


        city = findViewById(R.id.city);
//        RadioGroup isAuthorizedServiceCentre = findViewById(R.id.isAuthorizedServiceCentre);
//        RadioGroup isTowingService = findViewById(R.id.isTowingService);
//        RadioGroup provideServiceFor = findViewById(R.id.provideServiceFor);


        String[] items = new String[]{"Mumbai",
                "Pune",
                "Bangalore",
                "Hyderabad",
                "Nashik",
                "Sangamner"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        city.setAdapter(adapter);


    }

    public boolean validateContact() {
        contact = findViewById(R.id.phone);
        String username = Objects.requireNonNull(contact.getEditText()).getText().toString();
        if (username.isEmpty()) {
            contact.setError("This field cannot be empty");
            return false;
        } else {
            contact.setError(null);
            contact.setEnabled(true);
            return true;
        }
    }

    public boolean validateAddress() {
        address = findViewById(R.id.address);
        String username = Objects.requireNonNull(address.getEditText()).getText().toString();
        if (username.isEmpty()) {
            address.setError("This field cannot be empty");
            return false;
        } else {
            address.setError(null);
            address.setEnabled(true);
            return true;
        }
    }

    public boolean validateName() {
        name = findViewById(R.id.name);
        String username = Objects.requireNonNull(name.getEditText()).getText().toString();
        if (username.isEmpty()) {
            name.setError("This field cannot be empty");
            return false;
        } else {
            name.setError(null);
            name.setEnabled(true);
            return true;
        }
    }

    private boolean validateIsTowingService() {
        isTowingService = findViewById(R.id.isTowingService);
        isTS = findViewById(isTowingService.getCheckedRadioButtonId());
        String isTSvalue = isTS.getText().toString();
        if (isTSvalue.isEmpty()) {
            isTS = findViewById(R.id.radiotowno);
            isTS.setError("Please choose either");
            return false;
        } else {
            isTS = findViewById(R.id.radioButtonyes);
            isTS.setError(null);
            isTS.setEnabled(true);
            return true;
        }
    }

    private boolean validateProvideServiceFor() {
        provideServiceFor = findViewById(R.id.provideServiceFor);
        pSF = findViewById(provideServiceFor.getCheckedRadioButtonId());
        String pSFvalue = pSF.getText().toString();
        if (pSFvalue.isEmpty()) {
            pSF = findViewById(R.id.radioButton3);
            pSF.setError("Please choose either");
            return false;
        } else {
            pSF = findViewById(R.id.radioButton3);
            pSF.setError(null);
            pSF.setEnabled(true);
            return true;
        }
    }

    private boolean validateAuthorizedServiceCentre() {
        isAuthorizedServiceCentre = findViewById(R.id.isAuthorizedServiceCentre);
        isAS = findViewById(isAuthorizedServiceCentre.getCheckedRadioButtonId());
        String isASvalue = isAS.getText().toString();
        if (isASvalue.isEmpty()) {
            isAS = findViewById(R.id.radioButton3);
            isAS.setError("Please choose either");
            return false;
        } else {
            isAS = findViewById(R.id.radioButtonno);
            isAS.setError(null);
            isAS.setEnabled(true);
            return true;
        }
    }

    public void verifyAddPlace(View view) {
        if (!validateName() | !validateContact() | !validateAddress() | !validateAuthorizedServiceCentre() | !validateProvideServiceFor() | !validateIsTowingService()) {
            return;
        } else {
            isExistingUser();
        }
    }


    private void isExistingUser() {
        contact = findViewById(R.id.phone);
        String enteredPhoneNo = Objects.requireNonNull(contact.getEditText()).getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");

        Query checkUser = reference.orderByChild("contact").equalTo(enteredPhoneNo);


        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                contact.setError(null);
                contact.setEnabled(true);

                if (snapshot.child(enteredPhoneNo).exists()) {
                    contact.setError("User already exists");
                    contact.requestFocus();
                    return;
                }

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Stations");
                Query checkStation = reference.orderByChild("contact").equalTo(enteredPhoneNo);
                checkStation.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        contact.setError(null);
                        contact.setEnabled(true);

                        if (snapshot.child(enteredPhoneNo).exists()) {
                            contact.setError("Station already exists");
                            contact.requestFocus();

                        }
                        else {

                            submit = findViewById(R.id.submit);
                            submit.setVisibility(View.GONE);
                            progressBar = findViewById(R.id.add_place_progressBar);
                            progressBar.setVisibility(View.VISIBLE);

                            isAuthorizedServiceCentre = findViewById(R.id.isAuthorizedServiceCentre);
                            provideServiceFor = findViewById(R.id.provideServiceFor);
                            isTowingService = findViewById(R.id.isTowingService);

                            contact = findViewById(R.id.phone);
                            address = findViewById(R.id.address);
                            city = findViewById(R.id.city);
                            name = findViewById(R.id.name);

                            isAS = findViewById(isAuthorizedServiceCentre.getCheckedRadioButtonId());
                            pSF = findViewById(provideServiceFor.getCheckedRadioButtonId());
                            isTS = findViewById(isTowingService.getCheckedRadioButtonId());

                            String placeName = Objects.requireNonNull(name.getEditText()).getText().toString();
                            String placeAddress = Objects.requireNonNull(address.getEditText()).getText().toString();
                            String placeIsAuthorizedServiceCentre = isAS.getText().toString();
                            String placeContact = Objects.requireNonNull(contact.getEditText()).getText().toString();
                            String placeIsTowingService = isTS.getText().toString();
                            String placeCity = city.getSelectedItem().toString();
                            String placeProvideServiceFor = pSF.getText().toString();

                            AddPlaceHelper addPlaceHelper = new AddPlaceHelper(placeName, placeAddress, placeContact, placeCity, placeIsAuthorizedServiceCentre, placeIsTowingService, placeProvideServiceFor);

                            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Stations");
                            reference.child(placeContact).setValue(addPlaceHelper);
                            Toast.makeText(getApplicationContext(),"New place added successfully",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AddPlace.this, Dashboard.class);
                            startActivity(intent);
                            finish();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


    public void gotoHome(View view) {
    }
}