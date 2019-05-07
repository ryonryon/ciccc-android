package com.ryotogashi.droidcafe;

import java.io.Serializable;

public class UserInformation implements Serializable {

    public UserInformation(String name, String street, String city, String province, String postelCode, String phoneNumber, DeliverType deliverType) {
        this.name = name;
        this.street = street;
        this.city = city;
        this.province = province;
        this.postalCode = postelCode;
        this.phoneNumber = phoneNumber;
        this.deliverType = deliverType;
    }

    private String name;
    private String street;
    private String city;
    private String province;
    private String postalCode;
    private String phoneNumber;
    private DeliverType deliverType;

    public enum DeliverType {
        Soon,
        NextDay,
        PickUp,
    }

    public String getName() {
        return name;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getPostelCode() {
        return postalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public DeliverType getDeliverType() {
        return deliverType;
    }
}
