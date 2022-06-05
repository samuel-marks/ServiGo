package com.example.servigox1.HelperClasses;

import android.net.Uri;

public class AddPlaceHelper {
    String name;
    String address;
    String contact;
    String city;
    String isAuthorizedServiceCentre;
    String isTowingService;
    String provideServiceFor;

    public AddPlaceHelper() {
    }

    public AddPlaceHelper(String name, String address, String contact, String city, String isAuthorizedServiceCentre, String isTowingService, String provideServiceFor) {
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.city = city;
        this.isAuthorizedServiceCentre = isAuthorizedServiceCentre;
        this.isTowingService = isTowingService;
        this.provideServiceFor = provideServiceFor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIsAuthorizedServiceCentre() {
        return isAuthorizedServiceCentre;
    }

    public void setIsAuthorizedServiceCentre(String isAuthorizedServiceCentre) {
        this.isAuthorizedServiceCentre = isAuthorizedServiceCentre;
    }

    public String getIsTowingService() {
        return isTowingService;
    }

    public void setIsTowingService(String isTowingService) {
        this.isTowingService = isTowingService;
    }

    public String getProvideServiceFor() {
        return provideServiceFor;
    }

    public void setProvideServiceFor(String provideServiceFor) {
        this.provideServiceFor = provideServiceFor;
    }
}
