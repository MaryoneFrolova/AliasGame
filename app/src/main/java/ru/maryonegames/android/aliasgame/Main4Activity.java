package ru.maryonegames.android.aliasgame;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Vibrator;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Main4Activity extends AppCompatActivity {

    String command_a, command_b,current_team;
    int time, answ;
    boolean last_word;
    final Timer timer= new Timer();
    boolean pause_timer = false;

    TextView txt_explain_word, txt_expl_the_word, explain_word_txt_ad;
    TextView time_counter_txt;
    ProgressBar time_progress;
    TextView txt_last;
    TextView plus_txt, minus_txt, count_txt;
    int answ_this;


    int count = 0, plus = 0, minus = 0,  tap = 0;
    Context context;
    AlertDialog.Builder ad;
    AlertDialog.Builder pa;

    public static final String APP_PREFERENCES = "myset";
    public static final String APP_MAIN_LANG = "lang";
    public static final String APP_ADD_LANG = "add";
    private SharedPreferences mSet;

    String add;
    int stop = 0;

    //@RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        Intent intent = getIntent();
        context = Main4Activity.this;

        command_a = intent.getStringExtra("command_a");
        command_b = intent.getStringExtra("command_b");
        current_team = intent.getStringExtra("current_team");
        time = intent.getIntExtra("time", 0);
        answ = intent.getIntExtra("answ", 0);
        last_word = intent.getBooleanExtra("last_word", true);

        answ_this = answ;

        txt_explain_word = (TextView) findViewById(R.id.explain_word_txt);
        time_counter_txt = (TextView) findViewById(R.id.time_counter_txt);
        txt_last = (TextView) findViewById(R.id.txt_last);
        txt_expl_the_word = (TextView) findViewById(R.id.txt_expl_the_word);

        plus_txt = (TextView) findViewById(R.id.plus_txt);
        minus_txt = (TextView) findViewById(R.id.minus_txt);
        count_txt = (TextView) findViewById(R.id.count_txt);
        txt_expl_the_word.setText(current_team + " "+getString(R.string.explain));
        explain_word_txt_ad = (TextView) findViewById(R.id.explain_word_txt_ad);



        plus_txt.setText("+0");
        minus_txt.setText("-0");
        count_txt.setText("0");

        time_progress = (ProgressBar) findViewById(R.id.time_progress);

        time_progress.setMax(answ);
        time_progress.setProgress(answ);


        next_word();

            Stopwatch();

    }

   // @RequiresApi(api = Build.VERSION_CODES.N)
    public void good_btn (View view) {
        count = count+1;
        plus = plus +1;
        count_txt.setText(String.valueOf(count));
        plus_txt.setText("+" + String.valueOf(plus));
        words.new_explain_word(String.valueOf(txt_explain_word.getText()), 1);

        next_word();

        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(100);
    }

   // @RequiresApi(api = Build.VERSION_CODES.N)
    public void bad_btn (View view) {
        count = count - 1;
        minus = minus + 1;
        count_txt.setText(String.valueOf(count));
        minus_txt.setText("-" + String.valueOf(minus));
        words.new_explain_word(String.valueOf(txt_explain_word.getText()), 0);

        next_word();

        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(100);
    }

    //@RequiresApi(api = Build.VERSION_CODES.N)
    public void next_word (){
        words words = new words();
        one_word current_word = words.getWord();
        txt_explain_word.setText(current_word.getWord_for_explain());

        mSet = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        if (mSet.contains(APP_MAIN_LANG)){
            add = mSet.getString(APP_ADD_LANG, "none");
            if (add.equals("ru"))
            {
                add = "ru";
            }
            else
            {
                add = "en";
            }
        }

        else {
            add = "none";
        }
        if (!add.equals("none"))
        {

            explain_word_txt_ad.setText(current_word.getWord_for_explain_ad());
        }

    }

    private void Stopwatch() {

        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (!pause_timer){
                        time_counter_txt.setText(String.valueOf(answ_this--));
                        time_progress.setProgress(answ_this+1);
                        if (answ_this < 15)
                        {
                            time_counter_txt.setTextColor(Color.RED);
                            time_counter_txt.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
                        }
                        if (answ_this == -1) {
                            timer.cancel();
                            stop = 1;
                            Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibe.vibrate(1000);

                            if (last_word) {
                                Intent intent = new Intent(Main4Activity.this, Main5Activity.class);
                                intent.putExtra("command_a", command_a);
                                intent.putExtra("command_b", command_b);
                                intent.putExtra("time", (Integer) time);
                                intent.putExtra("answ", (Integer) answ);
                                intent.putExtra("last_word", (Boolean) last_word);
                                intent.putExtra("the_laster_word", txt_explain_word.getText());
                                intent.putExtra("current_team", current_team);

                                startActivity(intent);
                                finish();
                            }
                            else
                            {
                                words.new_explain_word(String.valueOf(txt_explain_word.getText()), 2);
                                Intent intent = new Intent(Main4Activity.this, Main6Activity.class);
                                intent.putExtra("command_a", command_a);
                                intent.putExtra("command_b", command_b);
                                intent.putExtra("time", (Integer) time);
                                intent.putExtra("answ", (Integer) answ);
                                intent.putExtra("last_word", (Boolean) last_word);
                                intent.putExtra("the_laster_word", txt_explain_word.getText());
                                intent.putExtra("current_team", current_team);

                                startActivity(intent);
                                finish();
                            }

                        }
                        }
                    }
                });
            }
        }, 0, 1000);
    }

    //@RequiresApi(api = Build.VERSION_CODES.N)
    public void tap (View view){
        if (tap == 0)
        next_word();
        tap= tap+1;
    }

    @Override
    protected void onPause() {

        super.onPause();

        String title = getString(R.string.title_pause);
        String message = getString(R.string.text_pause);
        String btn1 = getString(R.string.ok_end);


        pa = new AlertDialog.Builder(context);
        pa.setTitle(title);
        pa.setMessage(message);
        pa.setPositiveButton(btn1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                pause_timer = false;
            }
        });


        pa.show();
    }

    @Override
    protected void onStop() {

        //Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        //vibe.vibrate(300);

        super.onStop();
        pause_timer = true;
    }

    /*@Override
    protected void onStart() {
        super.onStart();
        pause_timer = false;
    }
*/
    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();

        finish();
    }


    @Override
    public void onBackPressed() {
        pause_timer = true;
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
                pause_timer = false;
            }
        });

        ad.show();

    }

    public void pause_btn(View view){
        pause_timer = true;
        onPause();

    }
}
