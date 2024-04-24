package com.beyond.bookrental;

public class AddressModel {
    private String fullName;
    private String mobileNo;
    private String address;
    private String city;
    private String state;
    private String pinCode;

    // Constructor, getters, and setters

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    // Example of constructor:
    public AddressModel(String fullName, String mobileNo, String address, String city, String state, String pinCode) {
        this.fullName = fullName;
        this.mobileNo = mobileNo;
        this.address = address;
        this.city = city;
        this.state = state;
        this.pinCode = pinCode;
    }
}
