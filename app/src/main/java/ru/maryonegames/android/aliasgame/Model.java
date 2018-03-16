package ru.maryonegames.android.aliasgame;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vamar on 25.12.2017.
 */

public class Model {

     String question;
     // hold the question
    int current = NONE; // hold the answer picked by the user, initial is NONE(see below)
    public static final int NONE = 1000; // No answer selected
    public static final int ANSWER_ONE_SELECTED = 0; // first answer selected
    public static final int ANSWER_TWO_SELECTED = 1; // second answer selected
    public static final int ANSWER_THREE_SELECTED = 2; // third answer selected


    public Model(String question, int current) {
        this.question = question;
        this.current = current;
    }

    public static List<Model> makelist(ArrayList<explain_word> words){
        List<Model> list = new ArrayList<Model>();
        for (explain_word x:words) {
            int current=Model.NONE;
            if (x.isRes() == 1) current = Model.ANSWER_ONE_SELECTED;
            else if (x.isRes() == 0) current = Model.ANSWER_TWO_SELECTED;
            else if (x.isRes() == 2) current = Model.ANSWER_THREE_SELECTED;
            list.add(new Model(x.getWord(), current));
        }
        return list;
    }

}
