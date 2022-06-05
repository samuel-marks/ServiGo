package com.example.servigox1;

import android.content.Intent;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.servigox1.HelperClasses.RatingHelper;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Success#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Success extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ImageView img;
    RatingBar ratingBar;
    Button button;
    Toast toast;
    float rating;
    EditText editText;
    String feedback, randKey, userName;

    public Success() {
        // Required empty public constructor
    }

    public Success(String randKey, String userName) {
        this.randKey = randKey;
        this.userName = userName;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Success.
     */
    // TODO: Rename and change types and number of parameters
    public static Success newInstance(String param1, String param2) {
        Success fragment = new Success();
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
        View view =  inflater.inflate(R.layout.fragment_success, container, false);
        img = view.findViewById(R.id.photo);
        AnimatedVectorDrawable drawable =(AnimatedVectorDrawable) img.getDrawable();
        drawable.start();

        button = view.findViewById(R.id.sendFeedback);
        ratingBar = view.findViewById(R.id.rbstars);
        editText = view.findViewById(R.id.Feedback);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rating = ratingBar.getRating();
                feedback = editText.getText().toString();
                RatingHelper ratingHelper = new RatingHelper(userName, String.valueOf(rating),randKey);
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Ratings").child("AppRatings");
                reference.child(randKey).setValue(ratingHelper);
                Toast.makeText(getContext(),"Thank you for your feedback",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), Dashboard.class);
                startActivity(intent);
            }
        });

        return  view;
    }

}