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

public class Main3Activity extends AppCompatActivity {

    String command_a, command_b, current_team;
    int time, answ;
    boolean last_word;

    Context context;
    AlertDialog.Builder ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Intent intent = getIntent();

        TextView txt_command_a_txt = (TextView) findViewById(R.id.txt_command_a_txt);
        int i = 0;
        current_team = intent.getStringExtra("current_team");
        txt_command_a_txt.setText(current_team);

        command_a = intent.getStringExtra("command_a");
        command_b = intent.getStringExtra("command_b");
        current_team = intent.getStringExtra("current_team");
        time = intent.getIntExtra("time", 0);
        answ = intent.getIntExtra("answ", 0);
        last_word = intent.getBooleanExtra("last_word", true);

        context = Main3Activity.this;






    }

    public void go_btn (View view){
        words.delete_all_explain_words();
        Intent intent = new Intent(Main3Activity.this, Main4Activity.class );
        intent.putExtra("command_a", command_a);
        intent.putExtra("command_b", command_b);
        intent.putExtra("time", (Integer) time);
        intent.putExtra("answ", (Integer) answ);
        intent.putExtra("last_word", (Boolean) last_word);
        intent.putExtra("current_team", current_team);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    public void onBackPressed() {
        String title = "Вы уверены что хотите завершить игру?";
        String message = "Ваше действие приведёт к переходу в окно настройки игры. Текущая игра будет завершена.";
        String btn1 = "Отмена";
        String btn2 = "ОК";

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
                Toast.makeText(context,"игра продолжается", Toast.LENGTH_LONG).show();
            }
        });

        ad.show();

    }
}
