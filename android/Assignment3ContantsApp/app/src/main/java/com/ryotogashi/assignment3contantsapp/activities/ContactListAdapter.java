package com.ryotogashi.assignment3contantsapp.activities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ryotogashi.assignment3contantsapp.R;
import com.ryotogashi.assignment3contantsapp.models.Contact;
import com.ryotogashi.assignment3contantsapp.models.ContactList;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactListViewHolder> {
    private ContactList mContactList;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public ContactListAdapter(ContactList mContactList, Context mContext) {
        mLayoutInflater = LayoutInflater.from(mContext);
        this.mContactList = mContactList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ContactListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View viewHolder = mLayoutInflater.inflate(R.layout.contact_holder, viewGroup, false);
        return new ContactListViewHolder(viewHolder, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactListViewHolder contactListViewHolder, int i) {
        Contact contact = mContactList.getContact(i);

        contactListViewHolder.bind(contact);
    }

    @Override
    public int getItemCount() { return mContactList.getContactList().size(); }

    class ContactListViewHolder extends RecyclerView.ViewHolder{
        private TextView contactNameTV;
        private TextView contactNumberTV;
        private ContactListAdapter mContactListAdapter;

        public ContactListViewHolder(@NonNull View itemView,  ContactListAdapter adapter) {
            super(itemView);
            contactNameTV = itemView.findViewById(R.id.contact_name);
            contactNumberTV = itemView.findViewById(R.id.contact_number);
            mContactListAdapter = adapter;
        }

        public void bind(Contact contact) {
            //TODO this is not proper way.
            contactNameTV.setText("" + contact.getName());
            contactNumberTV.setText(contact.getCell());
        }
    }
}
