package com.ryotogashi.assignment3contantsapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ContactList {

    public ContactList() {}

    public ContactList(ArrayList<Contact> contactList) {
        this.contactList = contactList;
    }

    @SerializedName("results")
    @Expose
    private ArrayList<Contact> contactList;

    public ArrayList<Contact> getContactList() {
        return contactList;
    }

    public void addContact(Contact contact){
        this.contactList.add(contact);
    }

    public Contact getContact(int index) {
        return this.contactList.get(index);
    }
}
