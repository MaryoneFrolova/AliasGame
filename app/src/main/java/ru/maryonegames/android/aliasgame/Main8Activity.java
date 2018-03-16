package ru.maryonegames.android.aliasgame;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Main8Activity extends AppCompatActivity {

    String command_a, command_b, win_team;
    int time, answ;
    boolean last_word;

    Context context;
    AlertDialog.Builder ad;

    TextView win_txt, count_txt_win;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        Intent intent = getIntent();
        context = Main8Activity.this;

        command_a = intent.getStringExtra("command_a");
        command_b = intent.getStringExtra("command_b");
        win_team = intent.getStringExtra("win_team");

        time = intent.getIntExtra("time", 0);
        answ = intent.getIntExtra("answ", 0);
        last_word = intent.getBooleanExtra("last_word", true);


        win_txt = (TextView) findViewById(R.id.win_txt);
        count_txt_win = (TextView) findViewById(R.id.count_txt_win);
        int count = 0;
        if (win_team.equals(command_a)) count = words.getCount_a();
        else  count = words.getCount_b();
        win_txt.setText(win_team);
        count_txt_win.setText(String.valueOf(count));

    }

    public void nextactvity (View view){



            Intent intent = new Intent(Main8Activity.this, StartActivity.class );
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
