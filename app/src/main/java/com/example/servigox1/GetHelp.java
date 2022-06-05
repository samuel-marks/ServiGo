package com.example.servigox1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class GetHelp extends AppCompatActivity {

    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_help);
        spinner=findViewById(R.id.spinner);

        String[] items = new String[]{
                "Yamaha R15",
                "Activa 4G",
                "BMW",
                "Audi"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(adapter);
    }

    public void gotoHome(View view) {
        Intent intent = new Intent(GetHelp.this, Dashboard.class);
        startActivity(intent);
        finish();
    }
}