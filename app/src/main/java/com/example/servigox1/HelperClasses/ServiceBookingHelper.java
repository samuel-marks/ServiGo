package com.example.servigox1.HelperClasses;

public class ServiceBookingHelper {
    String bookingID,station_contact, station_name, user_name, user_contact, booking_details, booking_date;

    public ServiceBookingHelper() {
    }

    public ServiceBookingHelper(String bookingID, String station_contact, String station_name, String user_name, String user_contact, String booking_details, String booking_date) {
        this.station_contact = station_contact;
        this.station_name = station_name;
        this.user_name = user_name;
        this.user_contact = user_contact;
        this.booking_details = booking_details;
        this.booking_date = booking_date;
        this.bookingID = bookingID;
    }

    public String getStation_contact() {
        return station_contact;
    }

    public String getStation_name() {
        return station_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getBookingID() {
        return bookingID;
    }

    public String getUser_contact() {
        return user_contact;
    }

    public String getBooking_details() {
        return booking_details;
    }

    public String getBooking_date() {
        return booking_date;
    }
}
