package ru.maryonegames.android.aliasgame;

/**
 * Created by vamar on 05.12.2017.
 */

public class explain_word {
    private String word;
    private int res;

    public String getWord() {
        return word;
    }

    public int isRes() {
        return res;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public explain_word (String word, int res){
        this.word = word;
        this.res = res;
    }

}
