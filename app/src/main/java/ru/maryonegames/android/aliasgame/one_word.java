package ru.maryonegames.android.aliasgame;

/**
 * Created by vamar on 07.12.2017.
 */

public class one_word {
    private String word_for_explain;
    private String word_for_explain_ad;
    private int frequency;

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getFrequency() {

        return frequency;
    }

    public String getWord_for_explain() {

        return word_for_explain;
    }

    one_word(String word_for_explain){
        this.word_for_explain = word_for_explain;
        frequency = 0;

    }
    one_word(String word_for_explain, String word_for_explain_ad){
        this.word_for_explain = word_for_explain;
        this.word_for_explain_ad=word_for_explain_ad;
        frequency = 0;

    }

    public String getWord_for_explain_ad() {
        return word_for_explain_ad;
    }

    public void setWord_for_explain(String word_for_explain) {
        this.word_for_explain = word_for_explain;
    }

    public void setWord_for_explain_ad(String word_for_explain_ad) {
        this.word_for_explain_ad = word_for_explain_ad;
    }
}
