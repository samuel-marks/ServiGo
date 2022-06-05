package com.example.servigox1;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StationDetails2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StationDetails2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    int image;
    String title;
    String description;
    String contact;
    Button instantBooking, detailedBooking;

    public StationDetails2() {
        // Required empty public constructor
    }
    public StationDetails2(int image, String title, String description, String contact) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.contact = contact;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StationDetails2.
     */
    // TODO: Rename and change types and number of parameters
    public static StationDetails2 newInstance(String param1, String param2) {
        StationDetails2 fragment = new StationDetails2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_station_details2, container, false);

        ImageView imageView = view.findViewById(R.id.fragment_image);
        TextView name = view.findViewById(R.id.fragment_title);
        TextView desc = view.findViewById(R.id.fragment_desc);
        ImageButton back = view.findViewById(R.id.back_icon);

        imageView.setImageResource(image);
        name.setText(title);
        desc.setText(description);


        instantBooking = view.findViewById(R.id.instant_service);

        instantBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity)v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.stationDetails2, new DetailBookingFragment(title,description,contact)).addToBackStack(null).commit();

            }
        });

        detailedBooking = view.findViewById(R.id.detailed_booking);
        detailedBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity)v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.stationDetails2, new DetailBookingFragment(title,description,contact)).addToBackStack(null).commit();

            }
        });

        return view;



    }


}