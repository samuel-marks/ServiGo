package com.example.servigox1.HelperClasses;

public class RegisterHelper {

    String name;
    String email;
    String contact;
    String confirmPassword;
    String city;

    public RegisterHelper() {
    }

    public RegisterHelper(String name, String email, String contact, String newPassword, String city) {
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.newPassword = newPassword;
        this.city = city;
    }

    String newPassword;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
