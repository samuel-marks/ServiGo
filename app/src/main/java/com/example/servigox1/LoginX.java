package com.example.servigox1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Objects;

public class LoginX extends AppCompatActivity {

    TextInputLayout phone_no, password;
    Button login;
    CheckBox rememberMe;
    TextInputEditText phone_no_EditText;
    TextInputEditText password_EditText;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_x);

        login = findViewById(R.id.login_button);
        phone_no_EditText = findViewById(R.id.login_id_editText);
        password_EditText = findViewById(R.id.password_id_editText);
        progressBar = findViewById(R.id.login_progressBar);
        progressBar.setVisibility(View.GONE);

        SessionManager sessionManager = new SessionManager(LoginX.this, SessionManager.SESSION_REMEMBER_ME);
        if (sessionManager.checkRemember()){
            HashMap<String,String> rememberMeDetails = sessionManager.getRememberMeDetailsFromSession();
            phone_no_EditText.setText(rememberMeDetails.get(SessionManager.SESSION_CONTACT));
            password_EditText.setText(rememberMeDetails.get(SessionManager.SESSION_PASSWORD));

        }

    }

    public boolean validateUsername(){
        phone_no = findViewById(R.id.login_id);
        String username = Objects.requireNonNull(phone_no.getEditText()).getText().toString();
        if(username.isEmpty()){
            phone_no.setError("This field cannot be empty");
            return false;
        } else {
            phone_no.setError(null);
            phone_no.setEnabled(true);
            return true;
        }
    }

    public boolean validatePassword(){
        password = findViewById(R.id.password_id);
        String pswrd = Objects.requireNonNull(password.getEditText()).getText().toString();

        if(pswrd.isEmpty()){
            password.setError("This field cannot be empty");
            return false;
        } else {
            password.setError(null);
            password.setEnabled(true);
            return true;
        }
    }
    public void verifyLogin(View view) {
        if (!validateUsername() | !validatePassword()) {
            return;
        } else {
            isUser();
        }
    }

    private void isUser(){
        phone_no = findViewById(R.id.login_id);
        password = findViewById(R.id.password_id);
        String enteredPhoneNo = Objects.requireNonNull(phone_no.getEditText()).getText().toString().trim();
        String enteredPassword = Objects.requireNonNull(password.getEditText()).getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");

        Query checkUser = reference.orderByChild("contact").equalTo(enteredPhoneNo);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){

                    phone_no.setError(null);
                    phone_no.setEnabled(true);

                    String DBpassword = snapshot.child(enteredPhoneNo).child("newPassword").getValue(String.class);

                    assert DBpassword != null;
                    if (DBpassword.equals(enteredPassword)){
                        String contactFromDB = snapshot.child(enteredPhoneNo).child("contact").getValue(String.class);
                        String cityFromDB = snapshot.child(enteredPhoneNo).child("city").getValue(String.class);
                        String nameFromDB = snapshot.child(enteredPhoneNo).child("name").getValue(String.class);
                        String emailFromDB = snapshot.child(enteredPhoneNo).child("email").getValue(String.class);

                        SessionManager sessionManager = new SessionManager(LoginX.this, SessionManager.SESSION_USERSESSION);
                        sessionManager.createLoginSession(contactFromDB,DBpassword,cityFromDB,emailFromDB,nameFromDB);

                        rememberMe = findViewById(R.id.remember_me_checkBox);

                        if (rememberMe.isChecked()){

                            SessionManager sessionManagerRememberMe = new SessionManager(LoginX.this,SessionManager.SESSION_REMEMBER_ME);
                            sessionManagerRememberMe.createRememberMeSession(contactFromDB,DBpassword);

                        }

                        progressBar.setVisibility(View.VISIBLE);
                        login.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginX.this,UserProfile.class);

//                        intent.putExtra("name",nameFromDB);
//                        intent.putExtra("email",emailFromDB);
//                        intent.putExtra("contact",contactFromDB);
//                        intent.putExtra("city",cityFromDB);

                        startActivity(intent);
                        finish();
                    } else {
                        password.setError("Incorrect Password");
                        password.requestFocus();
                    }
                } else {
                    phone_no.setError("No such user exists");
                    phone_no.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(LoginX.this,Dashboard.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }

    public void gotoHome(View view) {
        Intent intent = new Intent(LoginX.this, Dashboard.class);
        startActivity(intent);
        finish();
    }

    public void gotoRegister(View view) {
        Intent intent = new Intent(LoginX.this, RegisterX.class);
        startActivity(intent);
        finish();
    }

}