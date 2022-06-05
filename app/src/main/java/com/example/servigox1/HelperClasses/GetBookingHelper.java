package com.example.servigox1.HelperClasses;

public class GetBookingHelper {
    String bookingID, booking_date, booking_details, station_contact, station_name, user_contact, user_name;

    public GetBookingHelper() {
    }

    public GetBookingHelper(String bookingID, String booking_date, String booking_details, String station_contact, String station_name, String user_contact, String user_name) {
        this.booking_date = booking_date;
        this.booking_details = booking_details;
        this.station_contact = station_contact;
        this.station_name = station_name;
        this.user_contact = user_contact;
        this.user_name = user_name;
        this.bookingID = bookingID;
    }

    public String getBookingID() { return bookingID;}

    public String getBooking_date() {
        return booking_date;
    }

    public String getBooking_details() {
        return booking_details;
    }

    public String getStation_contact() {
        return station_contact;
    }

    public String getStation_name() {
        return station_name;
    }

    public String getUser_contact() {
        return user_contact;
    }

    public String getUser_name() {
        return user_name;
    }
}
