package ru.maryonegames.android.aliasgame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    String command_a, command_b, current_team, lang, add;
    int time, answ;
    boolean last_word, chb_newyear, chb_base,chb_starwars, chb_film, chb_famous, chb_middle, chb_valen;

    public static final String APP_PREFERENCES = "myset";
    public static final String APP_MAIN_LANG = "lang";
    public static final String APP_ADD_LANG = "add";
    private SharedPreferences mSet;


    //@RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();

        TextView txt_command_a = (TextView) findViewById(R.id.txt_command_a);
        TextView txt_command_b = (TextView) findViewById(R.id.txt_command_b);

        command_a = intent.getStringExtra("command_a");
        command_b = intent.getStringExtra("command_b");
        time = intent.getIntExtra("time", 0);
        answ = intent.getIntExtra("answ", 0);
        last_word = intent.getBooleanExtra("last_word", true);
        //chb_newyear = intent.getBooleanExtra("newyear", true);
        chb_starwars = intent.getBooleanExtra("starwars", true);
        chb_base = intent.getBooleanExtra("base", true);
        chb_film = intent.getBooleanExtra("film", true);
        chb_famous = intent.getBooleanExtra("famous", true);
        chb_middle = intent.getBooleanExtra("middle", true);
        chb_valen = intent.getBooleanExtra("valen", true);

        txt_command_a.setText(command_a);
        txt_command_b.setText(command_b);

        current_team = command_a;
        int base=0, newyear = 0, starwars = 0, film = 0, famous = 0, middle = 0, valen = 0;
        if (chb_base) base = 1;
        //if (chb_newyear) newyear = 1;
        if (chb_starwars) starwars = 1;
        if (chb_film) film = 1;
        if (chb_famous) famous = 1;
        if (chb_middle) middle = 1;
        if (chb_valen) valen = 1;


        mSet = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        if (mSet.contains(APP_MAIN_LANG)){
            lang = mSet.getString(APP_MAIN_LANG, "en");
            if (lang.equals("ru"))
            {
                lang = "ru";
            }
            else if (lang.equals("uk"))
            {
                lang = "uk";
            }
            else
            {
                lang = "en";
            }
        }

        else {
            String locale = getApplicationContext().getResources().getConfiguration().locale.getISO3Country();
            if (locale.toUpperCase().equals("RUS")) {
                lang = "ru";
            } else if (locale.toUpperCase().equals("UKR")) {
                lang = "uk";
            } else {
                lang = "en";
            }
        }

        mSet = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        if (mSet.contains(APP_MAIN_LANG)){
            add = mSet.getString(APP_ADD_LANG, "none");
            if (add.equals("ru"))
            {
                add = "ru";
            }
            else if (add.equals("uk"))
            {
                add = "uk";
            }
            else
            {
                add = "en";
            }
        }

        else {
        add = "none";
        }

       words.simple_game(getApplicationContext(),lang, add, base, newyear, starwars, film, famous, middle, valen);

    }

    public void start_btn (View view){
        Intent intent = new Intent(Main2Activity.this, Main3Activity.class );
        intent.putExtra("command_a", command_a);
        intent.putExtra("command_b", command_b);
        intent.putExtra("time", (Integer) time);
        intent.putExtra("answ", (Integer) answ);
        intent.putExtra("last_word", (Boolean) last_word);
        intent.putExtra("current_team", current_team);
        startActivity(intent);
        finish();
    }


}
