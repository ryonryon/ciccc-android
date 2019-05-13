package com.ryotogashi.todolist;

import java.util.ArrayList;

public class WordList {

    private ArrayList<Word> mWords;

    public WordList() {
        this.mWords = new ArrayList<>();
        addWord("bail", "leaving in hurry.");
        addWord("ghosted", "getting completely blocked from someone.");
        addWord("chilling", "hanging out with your friends.");
        addWord("chink", "refer to Chinese(Asian) people.");
        addWord("wop", "refer to Italian (without paper).");
        addWord("chicks", "baby chickens (girls)");
        addWord("dope", "cool, nice");
        addWord("vancouverite", "people from vancouver");
    }

    public void addWord(String word, String definition) {
        this.mWords.add(new Word(word, definition));
    }

    public Word getWord(int index) {
        return this.mWords.get(index);
    }

    public ArrayList<Word> getmWords() {
        return mWords;
    }
}
