package com.ryotogashi.artistsfirebase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder> {

    private ArrayList<Artist> mArtistArrayList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private OnLongClickListenerDelegate mDelegate;

    public ArtistAdapter(Context context
            , ArrayList<Artist> artistArrayList
            , OnLongClickListenerDelegate delegate) {
        mArtistArrayList = artistArrayList;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mDelegate = delegate;
    }

    @NonNull
    @Override
    public ArtistViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // inflate layout for viewHolder
        View itemView = mLayoutInflater.inflate(R.layout.artist_list_item, viewGroup, false);
        return new ArtistViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistViewHolder artistViewHolder, int i) {
        artistViewHolder.bind(mArtistArrayList.get(i));
    }

    @Override
    public int getItemCount() { return mArtistArrayList.size(); }

    class ArtistViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        private TextView mArtistTextView;
        private TextView mGenreTextView;
        private TextView mRateTextView;
        private TextView mAddedDateTextView;


        public ArtistViewHolder(@NonNull View itemView) {
            super(itemView);

            mArtistTextView = itemView.findViewById(R.id.artistTextView);
            mGenreTextView = itemView.findViewById(R.id.genreTextView);
            mRateTextView = itemView.findViewById(R.id.rateTextView);
            mAddedDateTextView = itemView.findViewById(R.id.addedDateTextView);
            itemView.setOnLongClickListener(this);
        }

        public void bind(Artist artist) {
            mArtistTextView.setText(artist.getName());
            mGenreTextView.setText(artist.getGenre());
            mRateTextView.setText(artist.getRate());
            mAddedDateTextView.setText(artist.getAddedDate().toDate().toString());
        }

        @Override
        public boolean onLongClick(View v) {
            int position = getAdapterPosition();
            Toast.makeText(mContext
                    , "" + mArtistArrayList.get(position).getName()
                    , Toast.LENGTH_LONG).show();

            mDelegate.onLongClickViewHolder(v, position);

            return false;
        }
    }
}

interface OnLongClickListenerDelegate {
    void onLongClickViewHolder(View view, int position);
}