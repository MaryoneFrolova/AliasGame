package ru.maryonegames.android.aliasgame;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

public class Main13Activity extends AppCompatActivity {

    String command_a, command_b, current_team;
    int time, answ;
    boolean last_word;

    CheckBox chb_starwars, chb_newyear, chb_base, chb_film, chb_famous, chb_middle, chb_valen;

    AlertDialog.Builder ad;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main13);

        Intent intent = getIntent();
        command_a = intent.getStringExtra("command_a");
        command_b = intent.getStringExtra("command_b");
        time = intent.getIntExtra("time", 0);
        answ = intent.getIntExtra("answ", 0);
        last_word = intent.getBooleanExtra("last_word", true);

        chb_starwars = (CheckBox) findViewById(R.id.chb_starwars);
        //chb_newyear = (CheckBox) findViewById(R.id.chb_newyear);
        chb_base = (CheckBox) findViewById(R.id.chb_base);
        chb_film = (CheckBox) findViewById(R.id.chb_film);
        chb_famous = (CheckBox) findViewById(R.id.chb_famous);
        chb_middle = (CheckBox) findViewById(R.id.chb_middle);
        chb_valen = (CheckBox) findViewById(R.id.chb_valen);

        chb_base.setChecked(true);

        context = Main13Activity.this;
    }

    public void start_btn (View view){
//!chb_newyear.isChecked()
        if (!chb_base.isChecked() &&  !chb_starwars.isChecked() && !chb_film.isChecked() && !chb_famous.isChecked() && !chb_middle.isChecked() && !chb_valen.isChecked())
        {
            String title = getString(R.string.title_set);
            String message = getString(R.string.text_set);
            String btn1 = getString(R.string.ok_end);


            ad = new AlertDialog.Builder(context);
            ad.setTitle(title);
            ad.setMessage(message);
            ad.setPositiveButton(btn1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });


            ad.show();
        }
        else {
            Intent intent = new Intent(Main13Activity.this, Main2Activity.class);
            intent.putExtra("command_a", command_a);
            intent.putExtra("command_b", command_b);
            intent.putExtra("time", (Integer) time);
            intent.putExtra("answ", (Integer) answ);
            intent.putExtra("last_word", (Boolean) last_word);
            intent.putExtra("current_team", current_team);

            intent.putExtra("base", (boolean) chb_base.isChecked());
            //intent.putExtra("newyear", (boolean) chb_newyear.isChecked());
            intent.putExtra("starwars", (boolean) chb_starwars.isChecked());
            intent.putExtra("film", (boolean) chb_film.isChecked());
            intent.putExtra("famous", (boolean) chb_famous.isChecked());
            intent.putExtra("middle", (boolean) chb_middle.isChecked());
            intent.putExtra("valen", (boolean) chb_valen.isChecked());
            startActivity(intent);
            finish();
        }
    }
}
