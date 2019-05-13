package com.ryotogashi.todolist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {
    private WordList mWordsList;
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    
    public WordListAdapter(Context context, WordList wordList) {
        mLayoutInflater = LayoutInflater.from(context);
        this.mWordsList = wordList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View wordHolder = mLayoutInflater.inflate(R.layout.word_holder, viewGroup, false);
        return new WordViewHolder(wordHolder, this);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder wordViewHolder, int i) {
        Word word = mWordsList.getWord(i);

        wordViewHolder.bind(word);
    }

    @Override
    public int getItemCount() {
        return mWordsList.getmWords().size();
    }

    class WordViewHolder extends RecyclerView.ViewHolder {
        private TextView wordTextView;
        private WordListAdapter mWordListAdapter;

        public WordViewHolder(@NonNull View itemView, WordListAdapter adapter) {
            super(itemView);
            wordTextView = itemView.findViewById(R.id.word);
            mWordListAdapter = adapter;

           itemView.setOnLongClickListener(new View.OnLongClickListener() {
               @Override
               public boolean onLongClick(View v) {
                   int position = getLayoutPosition();
                   Word word = mWordsList.getWord(position);

                   Toast.makeText(mContext, word.getDefinition(), Toast.LENGTH_LONG).show();

                   return true;
               }
           });

        }

        public void bind(Word word) {

            wordTextView.setText(word.getWord());

        }
    }
}
