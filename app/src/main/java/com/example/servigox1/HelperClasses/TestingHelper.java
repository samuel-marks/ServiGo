package com.example.servigox1.HelperClasses;

public class TestingHelper {

    String movieName, genre, releaseYear;

    public TestingHelper(String movieName, String genre, String releaseYear) {
        this.movieName = movieName;
        this.genre = genre;
        this.releaseYear = releaseYear;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getGenre() {
        return genre;
    }

    public String getReleaseYear() {
        return releaseYear;
    }
}
