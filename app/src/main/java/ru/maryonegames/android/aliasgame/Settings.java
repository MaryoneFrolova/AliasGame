package ru.maryonegames.android.aliasgame;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Random;

public class Settings extends AppCompatActivity {

    TextView txt_time_count;
    TextView txt_answ_count;

    SeekBar seekbar_time;
    SeekBar seekbar_answ;

    EditText command_a_edit;
    EditText command_b_edit;

    AlertDialog.Builder ad;
    Context context;

    Switch last_word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        command_a_edit = (EditText) findViewById(R.id.command_a_edit);
        command_b_edit = (EditText) findViewById(R.id.command_b_edit);

        txt_time_count = (TextView) findViewById(R.id.txt_time_count);
        txt_answ_count = (TextView) findViewById(R.id.txt_answ_count);

        seekbar_time = (SeekBar) findViewById(R.id.seekbar_time);
        seekbar_answ = (SeekBar) findViewById(R.id.seekbar_answ);

        //command_a_edit.setText("Комманда A");
        //command_b_edit.setText("Комманда B");

        last_word = (Switch) findViewById(R.id.switch_last);
        context = Settings.this;

        txt_answ_count.setText(String.valueOf(seekbar_answ.getProgress()));
        txt_time_count.setText(String.valueOf(seekbar_time.getProgress()));

        seekbar_answ.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txt_answ_count.setText(String.valueOf(seekbar_answ.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                txt_answ_count.setText(String.valueOf(seekbar_answ.getProgress()));
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                txt_answ_count.setText(String.valueOf(seekbar_answ.getProgress()));
            }
        });
        seekbar_time.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txt_time_count.setText(String.valueOf(seekbar_time.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                txt_time_count.setText(String.valueOf(seekbar_time.getProgress()));
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                txt_time_count.setText(String.valueOf(seekbar_time.getProgress()));
            }
        });


    }

    public void next_name_command_a(View view) {
        EditText command_a_edit = (EditText) findViewById(R.id.command_a_edit);
        String name_cmd = change_command_name();
        command_a_edit.setText(name_cmd);

    }

    public void next_name_command_b(View view) {
        EditText command_b_edit = (EditText) findViewById(R.id.command_b_edit);
        String name_cmd = change_command_name();
        command_b_edit.setText(name_cmd);


    }

    public void next_btn (View view){
        String a = command_a_edit.getText().toString().toUpperCase();
        String b = command_b_edit.getText().toString().toUpperCase();
        if (a.equals(b))
        {
            String title = getString(R.string.title_team);
            String message = getString(R.string.text_team);
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
            Intent intent = new Intent(Settings.this, Main13Activity.class);
            intent.putExtra("command_a", command_a_edit.getText().toString());
            intent.putExtra("command_b", command_b_edit.getText().toString());
            intent.putExtra("time", (Integer) seekbar_time.getProgress());
            intent.putExtra("answ", (Integer) seekbar_answ.getProgress());
            intent.putExtra("last_word", (Boolean) last_word.isChecked());
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    public String change_command_name(){
        String res = "";
        String[] names_ru = new String[]{"Мстители", "Великие", "Черепашки Ниндзя", "Победители",
                "Команда А", "Кузнечики", "Stalker", "СССР", "Торнадо", "Пельмешки", "Гвозди",
                "Семейка", "Весельчаки", "Охотники", "Первые", "Друзья",
                "Опять 25"};
        String[] names_en = new String[]{"Asteroids", "Cyclones", "Storm", "Bulls", "Aliens",
                "Smurfs", "Pokeman", "Grrrrr", "Pizzazz", "Digimon", "Psychos", "T-Rex",
                "Hawks", "Spider Pigs", "Scared Hitless", "Here for Beer", "Cunning Stunts"};
        Random randNumber = new Random();


        String locale = getApplicationContext().getResources().getConfiguration().locale.getISO3Country();
        if (locale.toUpperCase().equals("RUS")) {
            int rand = randNumber.nextInt(names_ru.length);
            res = names_ru[rand];

            String a = String.valueOf(command_a_edit.getText());
            String b = String.valueOf(command_b_edit.getText());

            while ((a.equals(b)) || (a.equals(res))||(b.equals(res)))
            {
                rand = randNumber.nextInt(names_ru.length);
                res = names_ru[rand];
            }
            return res;
        } else {
            int rand = randNumber.nextInt(names_en.length);
            res = names_en[rand];

            String a = String.valueOf(command_a_edit.getText());
            String b = String.valueOf(command_b_edit.getText());

            while ((a.equals(b)) || (a.equals(res))||(b.equals(res)))
            {
                rand = randNumber.nextInt(names_en.length);
                res = names_en[rand];
            }
            return res;
        }

  }

}

