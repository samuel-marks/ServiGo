package com.example.servigox1.HelperClasses;

public class AddVehicleHelper {
    String vehicleType;
    String vehicleCompany;
    String vehicleModel;

    public AddVehicleHelper() {
    }

    public AddVehicleHelper(String vehicleType, String vehicleCompany, String vehicleModel) {
        this.vehicleType = vehicleType;
        this.vehicleCompany = vehicleCompany;
        this.vehicleModel = vehicleModel;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleCompany() {
        return vehicleCompany;
    }

    public void setVehicleCompany(String vehicleCompany) {
        this.vehicleCompany = vehicleCompany;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }
}
