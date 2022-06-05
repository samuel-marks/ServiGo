package com.example.servigox1.HelperClasses;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.servigox1.R;
import com.example.servigox1.SessionManager;
import com.example.servigox1.UserBookings;

import java.util.ArrayList;
import java.util.HashMap;

public class GetBookingsAdapter extends RecyclerView.Adapter<GetBookingsAdapter.GetBookingsViewHolder>{

    ArrayList<GetBookingHelper> getBookingHelperArrayList;
    Context context;

    public GetBookingsAdapter(UserBookings userBookings, ArrayList<GetBookingHelper> getBookingHelperArrayList) {
        this.getBookingHelperArrayList = getBookingHelperArrayList;
        context = userBookings;
    }

    @NonNull
    @Override
    public GetBookingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.get_bookings_card,parent,false);
        GetBookingsViewHolder getBookingsViewHolder = new GetBookingsViewHolder(view);
        return getBookingsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GetBookingsViewHolder holder, int position) {

        GetBookingHelper getBookingHelper = getBookingHelperArrayList.get(position);


            String bDate = "Booking Date: " + getBookingHelper.getBooking_date();
            String name = "Service booked by: " + getBookingHelper.getUser_name();
            String bID = "Booking ID: " + getBookingHelper.getBookingID();
            String bike = "Yamaha R15 V3";
            String contact = getBookingHelper.getStation_contact();
            holder.title.setText(getBookingHelper.getStation_name());
            holder.bookingDate.setText(bDate);
            holder.bookingID.setText(bID);
            holder.vehicleName.setText(bike);
            holder.name.setText(name);
            holder.bookingDetails.setText(getBookingHelper.getBooking_details());

            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:"+contact));
                    context.startActivity(intent);
                }
            });

    }

    @Override
    public int getItemCount() {
        return getBookingHelperArrayList.size();
    }

    public static class GetBookingsViewHolder extends RecyclerView.ViewHolder{

        TextView title, name, bookingDetails, bookingID, bookingDate, vehicleName;
        Button button;

        public GetBookingsViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.booking_title);
            name = itemView.findViewById(R.id.booked_by);
            bookingDetails = itemView.findViewById(R.id.details_tv);
            bookingID = itemView.findViewById(R.id.bookingID);
            bookingDate = itemView.findViewById(R.id.booked_on);
            vehicleName = itemView.findViewById(R.id.booking_vehicle);
            button = itemView.findViewById(R.id.call_booking);
        }
    }
}
