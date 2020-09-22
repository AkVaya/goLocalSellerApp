package com.example.android.golocalsellerapp;


public class OutletInformation {
    String emailId, outletName, outletAddress, outletCity, outletLocality, outletContactName, outletTypeSold, outletNumber;

    public OutletInformation() {
    }

    public OutletInformation(String emailId, String outletName, String outletAddress, String outletCity, String outletLocality, String outletContactName, String outletTypeSold, String outletNumber) {
        this.emailId = emailId;
        this.outletName = outletName;
        this.outletAddress = outletAddress;
        this.outletCity = outletCity;
        this.outletLocality = outletLocality;
        this.outletContactName = outletContactName;
        this.outletNumber = outletNumber;
        this.outletTypeSold = outletTypeSold;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getOutletName() {
        return outletName;
    }

    public String getOutletAddress() {
        return outletAddress;
    }

    public String getOutletCity() {
        return outletCity;
    }

    public String getOutletLocality() {
        return outletLocality;
    }

    public String getOutletContactName() {
        return outletContactName;
    }

    public String getOutletTypeSold() {
        return outletTypeSold;
    }

    public String getOutletNumber() {
        return outletNumber;
    }
}