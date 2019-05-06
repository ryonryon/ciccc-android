package com.ryotogashi.assignment3contantsapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ContactList {

    @SerializedName("results")
    @Expose
    private ArrayList<Contact> contactList;

    public ArrayList<Contact> getContactList() {
        return contactList;
    }

}
