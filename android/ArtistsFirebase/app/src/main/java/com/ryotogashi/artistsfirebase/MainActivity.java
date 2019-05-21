package com.ryotogashi.artistsfirebase;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import javax.annotation.Nullable;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mArtistRecyclerView;
    private ArtistAdapter mArtistAdapter;
    private EditText mNameEditText;
    private SeekBar mRageSeekBar;
    private Spinner mGenreSpinner;
    private ArrayList<Artist> mArtistArrayList;

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ListenerRegistration mListenerRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNameEditText = findViewById(R.id.nameEditText);
        mGenreSpinner = findViewById(R.id.genreSpinner);
        mRageSeekBar = findViewById(R.id.rateSeekBar);
        mArtistRecyclerView = findViewById(R.id.artistRecyclerView);
        mArtistRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onStart() {
        super.onStart();
        // read in data (attach data change listener)
        mListenerRegistration = db.collection("artists")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        mArtistArrayList = (ArrayList<Artist>) queryDocumentSnapshots.toObjects(Artist.class);
                        mArtistAdapter = new ArtistAdapter(getApplicationContext(), mArtistArrayList);
                        mArtistRecyclerView.setAdapter(mArtistAdapter);
                    }
                });
    }

    @Override
    protected void onStop() {
        super.onStop();
        // detach listener
        mListenerRegistration.remove();
    }

    public void addArtist(View view) {
        final String name = mNameEditText.getText().toString().trim(); // get rid of whitespaces
        String genre = mGenreSpinner.getSelectedItem().toString();
        String rate = String.valueOf(mRageSeekBar.getProgress());
        if (!TextUtils.isEmpty(name)) {
            // if name is not empty
            Artist artist = new Artist(name, genre, rate);
            mArtistArrayList.add(artist);
            // 1. get the database instance
            // 2. set the collection (path)
            db.collection("artists")
                    .add(artist) // generates id string automatically
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            // when added successfully
                            Snackbar.make(findViewById(R.id.coordinatorLayout), name + " successfully added!", Snackbar.LENGTH_LONG)
                                    .show();
                            // after adding an artist
                            mNameEditText.setText("");
                            mNameEditText.clearFocus();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // when failed
                        }
                    });
//            mArtistAdapter.notifyDataSetChanged(); // refresh recyclerView
        } else {
            // if name is empty
            Snackbar.make(findViewById(R.id.coordinatorLayout), "Please set the artist name!", Snackbar.LENGTH_LONG)
                    .show();
        }

    }
}
