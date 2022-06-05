package com.example.servigox1.HelperClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.servigox1.R;

import java.util.ArrayList;

public class GetVehicleAdapter extends ArrayAdapter<GetVehicleHelper> {


    public GetVehicleAdapter(@NonNull Context context, @NonNull ArrayList<GetVehicleHelper> objects) {
        super(context, 0, objects);
    }

    private View initView(View convertView, int position, ViewGroup viewGroup) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.vehicle_spinner, viewGroup, false);
        }


        return convertView;
    }
}