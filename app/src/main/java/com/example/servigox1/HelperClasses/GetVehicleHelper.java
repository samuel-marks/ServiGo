package com.example.servigox1.HelperClasses;

public class GetVehicleHelper {

    String company, model, type;

    public GetVehicleHelper() {
    }

    public GetVehicleHelper(String company, String model, String type) {
        this.company = company;
        this.model = model;
        this.type = type;
    }

    public String getCompany() {
        return company;
    }

    public String getModel() {
        return model;
    }

    public String getType() {
        return type;
    }
}
