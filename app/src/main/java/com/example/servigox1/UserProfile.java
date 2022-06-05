package com.example.servigox1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;

public class UserProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        SessionManager sessionManager = new SessionManager(this, SessionManager.SESSION_USERSESSION);
        HashMap<String ,String> userDetails = sessionManager.getUserDetailsFromSession();
        String name = userDetails.get(SessionManager.KEY_FULLNAME);
        String email = userDetails.get(SessionManager.KEY_EMAIL);
        String city = userDetails.get(SessionManager.KEY_CITY);
        String contact = userDetails.get(SessionManager.KEY_CONTACT);


        Intent intent = getIntent();
//        String name = intent.getStringExtra("name");
//        String email = intent.getStringExtra("email");
//        String city = intent.getStringExtra("city");
//        String contact = intent.getStringExtra("contact");

        TextView fullName = findViewById(R.id.full_name);
        TextInputEditText user_name = findViewById(R.id.user_name);
        TextInputEditText user_city = findViewById(R.id.user_city);
        TextInputEditText user_email = findViewById(R.id.user_email);
        TextInputEditText user_contact = findViewById(R.id.user_contact);

        user_city.setEnabled(false);
        user_name.setEnabled(false);
        user_contact.setEnabled(false);
        user_email.setEnabled(false);

        fullName.setText(name);
        user_city.setText(city);
        user_name.setText(name);
        user_contact.setText(contact);
        user_email.setText(email);
    }

    public void gotoHome(View view) {
        Intent intent = new Intent(UserProfile.this, Dashboard.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(UserProfile.this,Dashboard.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}