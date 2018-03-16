package ru.maryonegames.android.aliasgame;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Size;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main6Activity extends AppCompatActivity {

    String command_a, command_b, the_laster_word, current_team;
    int time, answ;
    boolean last_word;
    int count, count2;

    ListView listView;

    Context context;
    AlertDialog.Builder ad;

    String[] data = {"угадано", "не угадано", "не в счёт"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        Intent intent = getIntent();

        command_a = intent.getStringExtra("command_a");
        command_b = intent.getStringExtra("command_b");
        current_team = intent.getStringExtra("current_team");
        time = intent.getIntExtra("time", 0);
        answ = intent.getIntExtra("answ", 0);
        last_word = intent.getBooleanExtra("last_word", true);
        the_laster_word = intent.getStringExtra("the_laster_word");
        listView = (ListView) findViewById(R.id.list_view);

        ArrayList<explain_word> explain_words = new ArrayList<explain_word>();
        explain_words = words.getArray_explain();
        String[] wordslist = new String[explain_words.size()];
        for (int i = 0; i < explain_words.size(); i++)
            wordslist[i] = explain_words.get(i).getWord();

        context = Main6Activity.this;
       final ArrayAdapter<String> adapter2;
        adapter2 = new MaArrayAdapter(this,
                wordslist, explain_words);
        // Привяжем массив через адаптер к ListView
        listView.setAdapter(adapter2);

        /*TableLayout tableLayout = (TableLayout) findViewById(R.id.container2);
        int i = 0;
        for (explain_word x:explain_words)
        {
            i++;
            TableRow row = new TableRow(getApplicationContext());


            TextView txt = new TextView(getApplicationContext());
            txt.setText(x.getWord());
            txt.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
            txt.setTextColor(Color.BLACK);
            TableRow.LayoutParams params = new TableRow.LayoutParams(0, TableLayout.LayoutParams.WRAP_CONTENT, 6f);
            txt.setLayoutParams(params);
            txt.setId(1000+i);
            row.addView(txt);

            final Spinner spinner = new Spinner(getApplicationContext());
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


            spinner.setAdapter(adapter);
            spinner.setId(2000+i);
            spinner.setPrompt("");
            // выделяем элемент
            if (x.isRes() == true)
            spinner.setSelection(0);
            else spinner.setSelection(1);
            // устанавливаем обработчик нажатия
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view,
                                           int position, long id) {
                    // показываем позиция нажатого элемента
                    // Toast.makeText(getBaseContext(), data[position], Toast.LENGTH_SHORT).show();

                    if (position ==  0) {
                        int c = 2000+1;
                        int num = spinner.getId() - c;
                        words.change_explain_word(num, true);

                    }
                    else if (position == 1){
                        int c = 2000+1;
                        int num = spinner.getId() - c;
                        words.change_explain_word(num, false);

                    }
                    else if (position == 2){
                        int c = 2000+1;
                        int num = spinner.getId() - c;
                        words.delete_explain_word(num);

                    }

                }
                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                }
            });
            TableRow.LayoutParams params2 = new TableRow.LayoutParams(0, TableLayout.LayoutParams.WRAP_CONTENT, 4f);
            spinner.setLayoutParams(params2);
            row.addView(spinner);

            tableLayout.addView(row);
            }
*/

/*



*/

    }

    public void nxt_btn(View view){



        ArrayList<explain_word> explain_words = words.getArray_explain();
        for (explain_word x:explain_words){
            int res = x.isRes();
            if (res == 1)
                count++;
            else if (res == 0) count--;
        }

        if (current_team.equals(command_a)) words.count_a_plus(count);
        else words.count_b_plus(count);


        Intent intent = new Intent(Main6Activity.this, Main7Activity.class );
        intent.putExtra("command_a", command_a);
        intent.putExtra("command_b", command_b);
        intent.putExtra("time", (Integer) time);
        intent.putExtra("answ", (Integer) answ);
        intent.putExtra("last_word", (Boolean) last_word);
        intent.putExtra("current_team", current_team);
        startActivity(intent);
        finish();
//finish();
    }

    public int count(){
        int count3 = 0;
        ArrayList<explain_word> explain_words3 = words.getArray_explain();
        for (explain_word x:explain_words3){
            int res = x.isRes();
            if (res == 1)
                count3++;
            else if (res == 0) count3--;
        }
        return count3;
    }

    @Override
    public void onBackPressed() {
        String title = getString(R.string.title_end);
        String message = getString(R.string.text_end);
        String btn1 = getString(R.string.cancel_end);
        String btn2 = getString(R.string.ok_end);

        ad = new AlertDialog.Builder(context);
        ad.setTitle(title);
        ad.setMessage(message);
        ad.setPositiveButton(btn2, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        ad.setNegativeButton(btn1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(context,getString(R.string.continue_end), Toast.LENGTH_LONG).show();

            }
        });

        ad.show();

    }


}
