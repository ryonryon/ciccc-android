package com.ryotogashi.artistsfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Transaction;

import java.util.ArrayList;

import javax.annotation.Nullable;

public class MainActivity extends AppCompatActivity implements OnLongClickListenerDelegate, OnClickListenerDelegate {

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

                        mArtistArrayList = new ArrayList<>();
                        for(DocumentSnapshot documentSnapshot: queryDocumentSnapshots.getDocuments()) {
                            Artist artist = documentSnapshot.toObject(Artist.class);
                            artist.setId(documentSnapshot.getId());
                            mArtistArrayList.add(artist);
                        }
                        mArtistAdapter = new ArtistAdapter(getApplicationContext(), mArtistArrayList, MainActivity.this, MainActivity.this);
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

    private  void updateArtist(String id, final String name, final String genre, final int rate){
        final DocumentReference artistRef = db.collection("artists").document(id);
        db.runTransaction(new Transaction.Function<Void>() {
            @android.support.annotation.Nullable
            @Override
            public Void apply(@NonNull Transaction transaction) throws FirebaseFirestoreException {
                transaction.set(artistRef, new Artist(name, genre, rate));
                return null;
            }
        });

    }

    private  void deleteArtist(String id){
        db.collection("artists").document(id)
            .delete()
            .addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {

                }
            })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    public void addArtist(View view) {

        final String name = mNameEditText.getText().toString().trim();
        final String genre = mGenreSpinner.getSelectedItem().toString();
        final int rate = mRageSeekBar.getProgress();

        if(TextUtils.isEmpty(name)){
            // if name is empty
            Snackbar.make(findViewById(R.id.coordinatorLayout), "Please set the artist name!", Snackbar.LENGTH_LONG)
                    .show();

            return;
        }

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
                        mRageSeekBar.setProgress(1);
                        mGenreSpinner.setSelection(0);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // when failed
                    }
                });
    }

    @Override
    public void onLongClickViewHolder(View view, int position) {
        showAlertDialog(position);
    }

    @Override
    public void onClickViewHolder(View view, int position) {
        Intent intent = new Intent(getApplicationContext(), TrackActivity.class);
        startActivity(intent);
    }

    private void showAlertDialog(int position){
        final Artist artist = mArtistArrayList.get(position);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        View dialogView = LayoutInflater.from(this).inflate(R.layout.artist_edit_dialog, null);
        builder.setView(dialogView);

        final EditText nameET = dialogView.findViewById(R.id.dialogNameEditText);
        final Spinner spinner = dialogView.findViewById(R.id.dialogGenreSpinner);
        final SeekBar seekBar = dialogView.findViewById(R.id.dialogRateSeekBar);

        Button updateBtn = dialogView.findViewById(R.id.dialogUpdateButton);
        Button deleteBtn = dialogView.findViewById(R.id.dialogDeleteButton);

        nameET.setText(artist.getName());
        spinner.setSelection(getIndexForGenre(artist.getGenre()));
        seekBar.setProgress(artist.getRate());

        builder.setTitle(("Update " + artist.getName()));
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        updateBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                final String newName = nameET.getText().toString().trim();
                final String newGenre = spinner.getSelectedItem().toString();
                final int newRate = seekBar.getProgress();

                if(TextUtils.isEmpty(newName)){
                    nameET.setError("Artist Name Required");
                    return;
                }

                updateArtist(artist.getId(), newName, newGenre, newRate);
                alertDialog.dismiss();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                deleteArtist(artist.getId());
                alertDialog.dismiss();
            }
        });

    }

    private int getIndexForGenre(String genre) {
        switch (genre) {
            case "Hip-Hop":
                return 0;
            case "R&B":
                return 1;
            case "Pop":
                return 2;
            case "Rock":
                return 3;
            case "EDM":
                return 4;
            case "Classical":
                return 5;
            default:
                return 0;
        }
    }
}
