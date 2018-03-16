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

public class Main7Activity extends AppCompatActivity {

    String command_a, command_b,  current_team;
    int time, answ;
    boolean last_word;

    Context context;
    AlertDialog.Builder ad;

    TextView cmd_a_txt, cmd_a_count,cmd_b_txt, cmd_b_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        Intent intent = getIntent();
        context = Main7Activity.this;

        command_a = intent.getStringExtra("command_a");
        command_b = intent.getStringExtra("command_b");
        current_team = intent.getStringExtra("current_team");
        time = intent.getIntExtra("time", 0);
        answ = intent.getIntExtra("answ", 0);
        last_word = intent.getBooleanExtra("last_word", true);


        cmd_a_txt = (TextView) findViewById(R.id.cmd_a_txt);
        cmd_a_count = (TextView) findViewById(R.id.cmd_a_count);
        cmd_b_txt = (TextView) findViewById(R.id.cmd_b_txt);
        cmd_b_count = (TextView) findViewById(R.id.cmd_b_count);


        cmd_b_count.setText(String.valueOf(words.getCount_b()));
        cmd_a_count.setText(String.valueOf(words.getCount_a()));
        cmd_a_txt.setText(command_a);
        cmd_b_txt.setText(command_b);

        words.remove_explain();

    }

    public void nxt_btn(View view){
        if ((words.getCount_a() >= time) && (current_team.equals(command_b)) && words.getCount_a() > words.getCount_b())
        {
            Intent intent = new Intent(Main7Activity.this, Main8Activity.class);
            intent.putExtra("command_a", command_a);
            intent.putExtra("command_b", command_b);
            intent.putExtra("time", (Integer) time);
            intent.putExtra("answ", (Integer) answ);
            intent.putExtra("last_word", (Boolean) last_word);
            intent.putExtra("current_team", current_team);
            intent.putExtra("win_team", command_a);
            startActivity(intent);
            finish();
        }
        else if ((words.getCount_b() >= time) && (current_team.equals(command_b)) && words.getCount_b() > words.getCount_a())
        {
            Intent intent = new Intent(Main7Activity.this, Main8Activity.class);
            intent.putExtra("command_a", command_a);
            intent.putExtra("command_b", command_b);
            intent.putExtra("time", (Integer) time);
            intent.putExtra("answ", (Integer) answ);
            intent.putExtra("last_word", (Boolean) last_word);
            intent.putExtra("current_team", current_team);
            intent.putExtra("win_team", command_b);
            startActivity(intent);
            finish();
        }
        else {
            if (current_team.equals(command_a)) current_team = command_b;
            else current_team = command_a;

            Intent intent = new Intent(Main7Activity.this, Main3Activity.class);
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
