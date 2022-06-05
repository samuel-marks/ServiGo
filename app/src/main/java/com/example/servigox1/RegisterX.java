package com.example.servigox1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.servigox1.HelperClasses.RegisterHelper;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class RegisterX extends AppCompatActivity {

    TextInputLayout name, email, contact, newPassword, confirmPassword;
    Button register;
    Spinner city;
    ProgressBar progressBar;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_x);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        contact = findViewById(R.id.phone);
        newPassword = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirm_password);
        city = findViewById(R.id.city);
        register = findViewById(R.id.register);


//        register.setOnClickListener(v -> {
//            rootNode = FirebaseDatabase.getInstance();
//            reference = rootNode.getReference("Users");
//
//            String Rname = Objects.requireNonNull(name.getEditText()).getText().toString();
//            String Remail = Objects.requireNonNull(email.getEditText()).getText().toString();
//            String Rcontact = Objects.requireNonNull(contact.getEditText()).getText().toString();
//            String Rpassword = Objects.requireNonNull(confirmPassword.getEditText()).getText().toString();
//            String Rcity = city.getSelectedItem().toString();
//
//            RegisterHelper registerHelper = new RegisterHelper(Rname, Remail, Rcontact, Rpassword, Rcity);
//
//            reference.child(Rcontact).setValue(registerHelper);
//
//            Intent intent = new Intent(RegisterX.this,LoginX.class);
//            startActivity(intent);
//            finish();
//        });


        Spinner dropdown = city;
        String[] items = new String[]{"Mumbai",
                "Pune",
                "Bangalore",
                "Hyderabad",
                "Nashik"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
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

    public boolean validateEmail() {
        email = findViewById(R.id.email);
        String username = Objects.requireNonNull(email.getEditText()).getText().toString();
        if (username.isEmpty()) {
            email.setError("This field cannot be empty");
            return false;
        } else {
            email.setError(null);
            email.setEnabled(true);
            return true;
        }
    }

    public boolean validateNewPassword() {
        newPassword = findViewById(R.id.password);
        String username = Objects.requireNonNull(newPassword.getEditText()).getText().toString();
        if (username.isEmpty()) {
            newPassword.setError("This field cannot be empty");
            return false;
        } else {
            newPassword.setError(null);
            newPassword.setEnabled(true);
            return true;
        }
    }

    public boolean validateConfirmPassword() {
        confirmPassword = findViewById(R.id.confirm_password);
        String confirmPass = Objects.requireNonNull(confirmPassword.getEditText()).getText().toString();

        newPassword = findViewById(R.id.password);
        String newPass = Objects.requireNonNull(newPassword.getEditText()).getText().toString();
        if (newPass.isEmpty()) {
            confirmPassword.setError("This field cannot be empty");
            return false;
        }
        if (!confirmPass.equals(newPass)) {
            confirmPassword.setError("Password does not match");
            return false;
        } else {
            confirmPassword.setError(null);
            confirmPassword.setEnabled(true);
            return true;
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
                } else {

                    register = findViewById(R.id.register);
                    register.setVisibility(View.GONE);
                    progressBar = findViewById(R.id.loading);
                    progressBar.setVisibility(View.VISIBLE);

                    String Rname = Objects.requireNonNull(name.getEditText()).getText().toString();
                    String Remail = Objects.requireNonNull(email.getEditText()).getText().toString();
                    String Rcontact = Objects.requireNonNull(contact.getEditText()).getText().toString();
                    String Rpassword = Objects.requireNonNull(confirmPassword.getEditText()).getText().toString();
                    String Rcity = city.getSelectedItem().toString();

                    RegisterHelper registerHelper = new RegisterHelper(Rname, Remail, Rcontact, Rpassword, Rcity);

                    reference.child(Rcontact).setValue(registerHelper);

                    Toast.makeText(getApplicationContext(), "Registration successful", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(RegisterX.this, LoginX.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference("Stations");
        Query checkStation = reference2.orderByChild("contact").equalTo(enteredPhoneNo);

        checkStation.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.child(enteredPhoneNo).exists()) {
                    register = findViewById(R.id.register);
                    register.setVisibility(View.GONE);
                    progressBar = findViewById(R.id.loading);
                    progressBar.setVisibility(View.VISIBLE);

                    String Rname = Objects.requireNonNull(name.getEditText()).getText().toString();
                    String Remail = Objects.requireNonNull(email.getEditText()).getText().toString();
                    String Rcontact = Objects.requireNonNull(contact.getEditText()).getText().toString();
                    String Rpassword = Objects.requireNonNull(confirmPassword.getEditText()).getText().toString();
                    String Rcity = city.getSelectedItem().toString();

                    RegisterHelper registerHelper = new RegisterHelper(Rname, Remail, Rcontact, Rpassword, Rcity);

                    reference.child("Stations").child(Rcontact).setValue(registerHelper);

                    Toast.makeText(getApplicationContext(), "Station Registration successful", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(RegisterX.this, LoginX.class);
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
    public void onBackPressed() {
        Intent intent = new Intent(RegisterX.this, Dashboard.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }

    public void gotoLogin(View view) {
        Intent intent = new Intent(RegisterX.this, LoginX.class);
        startActivity(intent);
        finish();
    }

    public void verifyRegister(View view) {
        if (!validateName() | !validateContact() | !validateEmail() | !validateNewPassword() | !validateConfirmPassword()) {
            return;
        } else {
            isExistingUser();
        }
    }

//    public void gotoHome(View view) {
//        Intent intent = new Intent(RegisterX.this, Dashboard.class);
//        startActivity(intent);
//        finish();
//    }
}