package com.example.servigox1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.servigox1.HelperClasses.ServiceBookingHelper;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConfirmDetailedBooking#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConfirmDetailedBooking extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView details,cost,time;
    Button button;
    String bookingDetails, estimatedCost, estimatedTime, stationContact, stationName;

    public ConfirmDetailedBooking() {
        // Required empty public constructor
    }
    public ConfirmDetailedBooking(String bookingDetails, String estimatedCost, String estimatedTime, String stationContact, String stationName) {

        this.bookingDetails = bookingDetails;
        this.estimatedTime = estimatedTime;
        this.estimatedCost = estimatedCost;
        this.stationContact = stationContact;
        this.stationName = stationName;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConfirmDetailedBooking.
     */
    // TODO: Rename and change types and number of parameters
    public static ConfirmDetailedBooking newInstance(String param1, String param2) {
        ConfirmDetailedBooking fragment = new ConfirmDetailedBooking();
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

        SessionManager sessionManager = new SessionManager(getContext(), SessionManager.SESSION_USERSESSION);
        HashMap<String ,String> userDetails = sessionManager.getUserDetailsFromSession();
        String userName = userDetails.get(SessionManager.KEY_FULLNAME);
        String userContact = userDetails.get(SessionManager.KEY_CONTACT);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_confirm_detailed_booking, container, false);

        details = view.findViewById(R.id.booking_details);
        cost = view.findViewById(R.id.estimatedCost);
        time = view.findViewById(R.id.estimatedTime);

        estimatedCost = "Your estimated service cost is Rs."+estimatedCost+" (approx)";
        estimatedTime = "Minimum time required for your service to be completed successfully is "+estimatedTime+" mins (approx)";

        details.setText(bookingDetails);
        cost.setText(estimatedCost);
        time.setText(estimatedTime);

        button = view.findViewById(R.id.finish_booking);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Random random = new Random();
                int randValue = random.nextInt(999999999);
                String randKey = String.valueOf(randValue);

                Date date = Calendar.getInstance().getTime();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                String formattedDate = dateFormat.format(date);

                ServiceBookingHelper serviceBookingHelper = new ServiceBookingHelper(randKey,stationContact, stationName, userName, userContact,bookingDetails, formattedDate);
                assert userContact != null;
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Bookings").child("forStations").child(stationContact);
                reference.child(randKey).setValue(serviceBookingHelper);
                reference = FirebaseDatabase.getInstance().getReference("Bookings").child("forUsers").child(userContact);
                reference.child(randKey).setValue(serviceBookingHelper);

                Toast.makeText(getContext(),"Booking successful",Toast.LENGTH_SHORT).show();
                button.setVisibility(View.GONE);

                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.confirmDetailBooking, new Success(randKey,userName)).addToBackStack(null).commit();


            }
        });

        return view;
    }
}