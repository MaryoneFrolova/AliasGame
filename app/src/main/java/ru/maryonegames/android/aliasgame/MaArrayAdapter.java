package ru.maryonegames.android.aliasgame;

/**
 * Created by vamar on 25.12.2017.
 */

import android.app.Activity;
import android.content.Context;
import android.util.ArrayMap;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;
class ViewHolder {
    TextView t = null;
    RadioGroup group;



    ViewHolder(View v) {
        t = (TextView) v.findViewById(R.id.label);
        group = (RadioGroup) v.findViewById(R.id.radioGroup);
    }

}

public class MaArrayAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] names;
    private List<Model> list;
    private final ArrayList<explain_word> explainWords;



    public MaArrayAdapter(Activity context, String[] names, ArrayList<explain_word> explain_words) {
        super(context, R.layout.rowbuttonlayout, names);
        this.context = context;
        this.names = names;
        this.explainWords = explain_words;
        this.list = Model.makelist(explain_words);
    }

    // Класс для сохранения во внешний класс и для ограничения доступа
    // из потомков класса
    /*static class ViewHolder {
        public RadioGroup radioGroup;
        public RadioButton first, second, third;
        public TextView textView;
    }*/

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // ViewHolder буферизирует оценку различных полей шаблона элемента

        // Очищает сущетсвующий шаблон, если параметр задан
        // Работает только если базовый шаблон для всех классов один и тот же
        /*View rowView = convertView;
        if (rowView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.rowbuttonlayout, null, true);
            holder = new ViewHolder();
            holder.textView = (TextView) rowView.findViewById(R.id.label);
            holder.radioGroup = (RadioGroup) rowView.findViewById(R.id.radioGroup);
            final RadioButton[] rb = new RadioButton[5];
            for(int i=0; i<3; i++){
                rb[i]  = new RadioButton(context);
//                rb[i].setButtonDrawable(R.drawable.single_radio_chice);
                rb[i].setId(i);
                RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(
                        0, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.weight=1.0f;
                params.setMargins(15, 0, 5, 10);
                holder.radioGroup.addView(rb[i],params); //the RadioButtons are added to the radioGroup instead of the layout
            }
            rowView.setTag(holder);
        } else {
            holder = (ViewHolder) rowView.getTag();
        }

        holder.textView.setText(names[position]);
        // Изменение иконки для Windows и iPhone
        String s = names[position];


        return rowView;*/

        //LayoutInflater inflater = context.getLayoutInflater();

        View v = convertView;
        ViewHolder holder = null;

        if (v == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            v = inflater.inflate(R.layout.rowbuttonlayout, parent, false);
            holder = new ViewHolder(v);
            v.setTag(holder);
            holder.group
                    .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                        public void onCheckedChanged(RadioGroup group,
                                                     int checkedId) {
                            Integer pos = (Integer) group.getTag(); // To identify the Model object i get from the RadioGroup with getTag()
                            //  an integer representing the actual position
                            Model element = list.get(pos);
                            switch (checkedId) { //set the Model to hold the answer the user picked
                                case R.id.answer0:
                                    element.current = Model.ANSWER_ONE_SELECTED;
                                    words.change_explain_word(pos,1);
                                    break;
                                case R.id.answer1:
                                    element.current = Model.ANSWER_TWO_SELECTED;
                                    words.change_explain_word(pos,0);                                    break;
                                case R.id.answer2:
                                    element.current = Model.ANSWER_THREE_SELECTED;
                                    words.change_explain_word(pos, 2);
                                    break;

                                default:
                                    element.current = Model.NONE; // Something was wrong set to the default
                            }

                        }
                    });
        } else {
            holder = (ViewHolder) v.getTag();
        }
        holder.group.setTag(new Integer(position)); // I passed the current position as a tag

        holder.t.setText(list.get(position).question); // Set the question body

        if (list.get(position).current != Model.NONE) {
            RadioButton r = (RadioButton) holder.group.getChildAt(list
                    .get(position).current);
            r.setChecked(true);
        } else {
            holder.group.clearCheck(); // This is required because although the Model could have the current
            // position to NONE you could be dealing with a previous row where
            // the user already picked an answer.

        }
        return v;
    }


}


