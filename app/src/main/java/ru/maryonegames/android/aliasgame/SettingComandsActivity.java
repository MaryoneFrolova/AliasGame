package ru.maryonegames.android.aliasgame;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SettingComandsActivity extends AppCompatActivity {


    TableRow tr_command1, tr_command2, tr_command3, tr_command4,
             tr_command5, tr_command6, tr_command7, tr_command8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_comands);

        tr_command1 = (TableRow) findViewById(R.id.tr_command1);
        tr_command2 = (TableRow) findViewById(R.id.tr_command2);
        tr_command3 = (TableRow) findViewById(R.id.tr_command3);
        tr_command4 = (TableRow) findViewById(R.id.tr_command4);
        tr_command5 = (TableRow) findViewById(R.id.tr_command5);
        tr_command6 = (TableRow) findViewById(R.id.tr_command6);
        tr_command7 = (TableRow) findViewById(R.id.tr_command7);
        tr_command8 = (TableRow) findViewById(R.id.tr_command8);



    }

    void next_btn (View view) {
        Intent intent = new Intent(SettingComandsActivity.this, Settings.class);
        startActivity(intent);
    }

    void add_command (View view) {

        TableRow[] commandsRows = {tr_command2,tr_command2,tr_command3,tr_command4,tr_command5,tr_command6,tr_command7,tr_command8};


        for (TableRow tr: commandsRows
             ) {
            if (tr.getVisibility() != View.VISIBLE) {
                tr.setVisibility(View.VISIBLE);
                break;
            }
        }



    }
}
