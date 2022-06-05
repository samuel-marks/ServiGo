package com.example.servigox1.HelperClasses;

public class RatingHelper {
    String name, rating, bookingID;

    public RatingHelper() {
    }

    public RatingHelper(String name, String rating, String bookingID) {
        this.name = name;
        this.rating = rating;
        this.bookingID = bookingID;
    }

    public String getName() {
        return name;
    }

    public String getRating() {
        return rating;
    }

    public String getBookingID() {
        return bookingID;
    }
}
