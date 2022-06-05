package com.example.servigox1;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailBookingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailBookingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button next;
    String radioValue;
    CheckBox brakePadChange, coolantChange, brakeOilChange, chainLubeClean, poloshing, tyreChange;
    TextInputLayout additionalService;
    int cost, time;
    String selectedOptions="", addOns="";
    String contact, title, address, costString, timeString;


    public DetailBookingFragment() {
        // Required empty public constructor
    }

    public DetailBookingFragment(String title, String address, String contact) {
        this.contact = contact;
        this.title = title;
        this.address = address;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailBookingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailBookingFragment newInstance(String param1, String param2) {
        DetailBookingFragment fragment = new DetailBookingFragment();
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

        View view = inflater.inflate(R.layout.fragment_detail_booking, container, false);


        next = view.findViewById(R.id.reg);
        brakePadChange = view.findViewById(R.id.brakePadsChange);
        brakeOilChange = view.findViewById(R.id.breakOilChange);
        coolantChange = view.findViewById(R.id.coolantChange);
        poloshing = view.findViewById(R.id.polishing);
        tyreChange = view.findViewById(R.id.tyreChange);
        chainLubeClean = view.findViewById(R.id.chainLubeClean);

        additionalService = view.findViewById(R.id.additionalService);
        additionalService.requestFocus();



        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (brakePadChange.isChecked()) {
                    cost += 500;
                    selectedOptions += "Brake Pads Change \n";
                    time += 40;
                }
                if (coolantChange.isChecked()) {
                    cost += 400;
                    selectedOptions += "Coolant Change \n";
                    time += 40;
                }
                if (chainLubeClean.isChecked()) {
                    cost += 200;
                    selectedOptions += "Chain Lube / Chain clean \n";
                    time += 10;
                }
                if (brakeOilChange.isChecked()) {
                    cost += 450;
                    selectedOptions += "Brake oil Change \n";
                    time += 15;
                }
                if (poloshing.isChecked()) {
                    cost += 180;
                    selectedOptions += "Polishing \n";
                    time += 10;
                }
                if (tyreChange.isChecked()) {
                    cost += 5000;
                    selectedOptions += "Tyre Change \n";
                    time += 50;
                }


                timeString = String.valueOf(time);
                costString = String.valueOf(cost);

                addOns = "Addition work to be done: "+Objects.requireNonNull(additionalService.getEditText()).getText().toString();

                if (addOns.isEmpty()) {
                    additionalService.setError("This field cannot be empty");
                    additionalService.requestFocus();
                    return;
                }

                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.detailed_bookingFragment, new ConfirmDetailedBooking(selectedOptions + addOns, costString, timeString, contact, title)).addToBackStack(null).commit();

//
//            Intent intent = new Intent(
//                    getActivity(), AddPlace.class);
//
//            intent.putExtra("ServiceSelected",selectedOptions);
//            intent.putExtra("ServiceCharges",cost);
//            intent.putExtra("ServiceTime",time);
//            intent.putExtra("AdditionalServicing", Objects.requireNonNull(additionalService.getEditText()).getText().toString());
//
//            startActivity(intent);

            }
        });

        return view;
    }
}