package com.example.servigox1.HelperClasses;

public class ViewAllHelper {

    String address, city, contact, isAuthorizedServiceCentre, isTowingService, name, provideServiceFor;

    public ViewAllHelper() {
    }

    public ViewAllHelper(String address, String city, String contact, String isAuthorizedServiceCentre, String isTowingService, String name, String provideServiceFor) {
        this.address = address;
        this.city = city;
        this.contact = contact;
        this.isAuthorizedServiceCentre = isAuthorizedServiceCentre;
        this.isTowingService = isTowingService;
        this.name = name;
        this.provideServiceFor = provideServiceFor;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getContact() {
        return contact;
    }

    public String getIsAuthorizedServiceCentre() {
        return isAuthorizedServiceCentre;
    }

    public String getIsTowingService() {
        return isTowingService;
    }

    public String getName() {
        return name;
    }

    public String getProvideServiceFor() {
        return provideServiceFor;
    }
}
