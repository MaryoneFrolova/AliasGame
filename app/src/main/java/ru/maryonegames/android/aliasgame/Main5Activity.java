package ru.maryonegames.android.aliasgame;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Main5Activity extends AppCompatActivity {

    TextView the_last_word, txt_lst_word;
    Button btn_cmd1, btn_cmd2, btn_noname;

    Context context;
    AlertDialog.Builder ad;

    String command_a, command_b, the_laster_word, current_team;
    int time, answ;
    boolean last_word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);


        txt_lst_word = (TextView) findViewById(R.id.txt_lst_word);
        the_last_word = (TextView) findViewById(R.id.txt_the_last_word);
        btn_cmd1 = (Button) findViewById(R.id.btn_cmdA);
        btn_cmd2 = (Button) findViewById(R.id.btn_cmdB);
        btn_noname = (Button) findViewById(R.id.btn_noname);

        Intent intent = getIntent();

        command_a = intent.getStringExtra("command_a");
        command_b = intent.getStringExtra("command_b");
        current_team = intent.getStringExtra("current_team");
        time = intent.getIntExtra("time", 0);
        answ = intent.getIntExtra("answ", 0);
        last_word = intent.getBooleanExtra("last_word", true);
        the_laster_word = intent.getStringExtra("the_laster_word");

        txt_lst_word.setText(the_laster_word);

        context = Main5Activity.this;

        if (last_word == true) {
            btn_cmd1.setText(command_a);
            btn_cmd2.setText(command_b);
            btn_noname.setText(getString(R.string.nobody));

            the_last_word.setText(getString(R.string.last_dot));

        } else {
            btn_cmd1.setText(R.string.no);
            btn_cmd2.setText(R.string.yes);

            btn_noname.setText(getString(R.string.skip));
            the_last_word.setText(getString(R.string.last_que));
        }

    }

    public void cmd1(View view) {
        if (last_word == true) {
            if (current_team.equals(command_a))
            {
            words.new_explain_word(the_laster_word, 1);
            nextactvity(view);}
            else
            {
                words.count_a_plus(1);
                nextactvity(view);
            }
        } else {
            words.new_explain_word(the_laster_word, 0);
            nextactvity(view);
        }
    }

    public void cmd2(View view) {
        if (last_word == true) {
            if (current_team.equals(command_b))
            {
                words.new_explain_word(the_laster_word, 1);
                nextactvity(view);}
            else
            {
                words.count_b_plus(1);
                nextactvity(view);
            }
        } else {
            words.new_explain_word(the_laster_word, 1);
            nextactvity(view);
        }
    }

    public void noname(View view) {
        nextactvity(view);
    }

    public void nextactvity(View view) {


        Intent intent = new Intent(Main5Activity.this, Main6Activity.class);
        intent.putExtra("command_a", command_a);
        intent.putExtra("command_b", command_b);
        intent.putExtra("time", (Integer) time);
        intent.putExtra("answ", (Integer) answ);
        intent.putExtra("current_team", current_team);
        intent.putExtra("last_word", (Boolean) last_word);
        startActivity(intent);
        finish();



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
