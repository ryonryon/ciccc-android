package com.ryotogashi.assignment3contantsapp.network;

import com.ryotogashi.assignment3contantsapp.models.ContactList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RandomUserService {
    @GET("?nat=ca")
    Call<ContactList> listContacts(@Query("results") int num);
}
