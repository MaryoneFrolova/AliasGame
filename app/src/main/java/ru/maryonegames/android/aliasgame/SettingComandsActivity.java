package ru.maryonegames.android.aliasgame;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.util.ArraySet;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

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

        EditText command1 = (EditText) findViewById(R.id.et_commandName1);
        EditText command2 = (EditText) findViewById(R.id.et_commandName2);
        command1.setText(getNewName());
        command2.setText(getNewName());

    }
    public void delete_row (View view) {

        int currentIdRow = view.getId();
        String currentNameRow = getResources().getResourceEntryName(currentIdRow);
        int currentNumberRow = Integer.parseInt(currentNameRow.replaceAll("[\\D]",""));
        //int currentNumberRow = ((Number)NumberFormat.getInstance().parse(currantNameRow)).intValue();

        if (getCountCommands() == 2) {
            Toast.makeText(getApplicationContext(),getString(R.string.min_count_commands), Toast.LENGTH_LONG).show();
            return;
        }
        if (currentNumberRow == 8)
        {
            String nameRow = "tr_command" + currentNumberRow;
            int idRow = getResources().getIdentifier(nameRow, "id", getPackageName());
            TableRow row = (TableRow) findViewById(idRow);
            row.setVisibility(View.GONE);
        }
        else {
            for (int i = currentNumberRow; i <= 8; i++) {


                String newNameRow = "tr_command" + i;
                int newIdRos = getResources().getIdentifier(newNameRow, "id", getPackageName());
                TableRow newRow = (TableRow) findViewById(newIdRos);
                if (newRow.getVisibility() == View.VISIBLE) {
                    if (i==8) {
                        String nameRow = "tr_command" + 8;
                        int idRow = getResources().getIdentifier(nameRow, "id", getPackageName());
                        TableRow row = (TableRow) findViewById(idRow);
                        row.setVisibility(View.GONE);
                        break;
                    }
                    String nextEditText = "et_commandName" + (i + 1);
                    int nextId = getResources().getIdentifier(nextEditText, "id", getPackageName());
                    EditText editText = (EditText) findViewById(nextId);

                    String thisEditTextName = "et_commandName" + i;
                    int thisId = getResources().getIdentifier(thisEditTextName, "id", getPackageName());
                    EditText thisEditText = (EditText) findViewById(thisId);

                    thisEditText.setText(editText.getText());
                } else {
                    if (i == currentNumberRow + 1) {
                        String nameRow = "tr_command" + currentNumberRow;
                        int idRow = getResources().getIdentifier(nameRow, "id", getPackageName());
                        TableRow row = (TableRow) findViewById(idRow);
                        row.setVisibility(View.GONE);
                        break;
                    } else {
                        String nameRow = "tr_command" + (i-1);
                        int idRow = getResources().getIdentifier(nameRow, "id", getPackageName());
                        TableRow row = (TableRow) findViewById(idRow);
                        row.setVisibility(View.GONE);
                        break;
                    }

                }
            }
        }

    }

    public void next_btn (View view) {

        //------only uniq and not null name commands-----
        ArrayList<String> commandNames = getCommandsName();
        ArraySet<String> commandUniqNames = new ArraySet<String>();
        commandUniqNames.addAll(commandNames);

        if (commandNames.contains("")) {
            String title = getString(R.string.title_team_null);
            String message = getString(R.string.text_team_null);
            String btn1 = getString(R.string.ok_end);


            AlertDialog.Builder ad = new AlertDialog.Builder(SettingComandsActivity.this);
            ad.setTitle(title);
            ad.setMessage(message);
            ad.setPositiveButton(btn1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });


            ad.show();
            return;
        }

        if (commandNames.size() != commandUniqNames.size()) {
            String title = getString(R.string.title_team);
            String message = getString(R.string.text_team);
            String btn1 = getString(R.string.ok_end);

            Context context = SettingComandsActivity.this;

            AlertDialog.Builder ad = new AlertDialog.Builder(context);
            ad.setTitle(title);
            ad.setMessage(message);
            ad.setPositiveButton(btn1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });


            ad.show();
            return;
        }

        //-----only uniq and not null name commands-----

        GameProcessor gameProcessor = new GameProcessor();
        gameProcessor.setCommands(commandNames);

        Intent intent = new Intent(SettingComandsActivity.this, Settings.class);
        startActivity(intent);
    }

    public void add_command (View view) {

        TableRow[] commandsRows = {tr_command2,tr_command2,tr_command3,tr_command4,tr_command5,tr_command6,tr_command7,tr_command8};

        if (tr_command8.getVisibility() == View.VISIBLE)
            Toast.makeText(getApplicationContext(),getString(R.string.max_count_commands), Toast.LENGTH_LONG).show();

        for (TableRow tr: commandsRows
             ) {
            if (tr.getVisibility() != View.VISIBLE) {
                tr.setVisibility(View.VISIBLE);
                int currentIdRow = tr.getId();
                String currentNameRow = getResources().getResourceEntryName(currentIdRow);
                int currentNumberRow = Integer.parseInt(currentNameRow.replaceAll("[\\D]",""));

                String thisEditTextName = "et_commandName" + currentNumberRow;
                int thisId = getResources().getIdentifier(thisEditTextName, "id", getPackageName());
                EditText thisEditText = (EditText) findViewById(thisId);
                thisEditText.setText(getNewName());
                break;
            }
        }
    }

    public void changeName (View view) {
        int currentIdRow = view.getId();
        String currentNameRow = getResources().getResourceEntryName(currentIdRow);
        int currentNumberRow = Integer.parseInt(currentNameRow.replaceAll("[\\D]",""));

        String thisEditTextName = "et_commandName" + currentNumberRow;
        int thisId = getResources().getIdentifier(thisEditTextName, "id", getPackageName());
        EditText thisEditText = (EditText) findViewById(thisId);
        thisEditText.setText(getNewName());
    }

    String getNewName () {
        String res = "";
        String[] names_ru = new String[]{"Мстители", "Великие", "Черепашки Ниндзя", "Победители",
                "Команда А", "Кузнечики", "Stalker", "СССР", "Торнадо", "Пельмешки", "Гвозди",
                "Семейка", "Весельчаки", "Охотники", "Первые", "Друзья",
                "Опять 25"};
        String[] names_en = new String[]{"Asteroids", "Cyclones", "Storm", "Bulls", "Aliens",
                "Smurfs", "Pokeman", "Grrrrr", "Pizzazz", "Digimon", "Psychos", "T-Rex",
                "Hawks", "Spider Pigs", "Scared Hitless", "Here for Beer", "Cunning Stunts"};
        Random randNumber = new Random();


        String locale = getApplicationContext().getResources().getConfiguration().locale.getLanguage();
        if (locale.toUpperCase().equals("RU")) {
            int rand = randNumber.nextInt(names_ru.length);
            res = names_ru[rand];

            while (getCommandsName().contains(res))
            {
                rand = randNumber.nextInt(names_ru.length);
                res = names_ru[rand];
            }

        } else {
            int rand = randNumber.nextInt(names_en.length);
            res = names_en[rand];

            while (getCommandsName().contains(res))
            {
                rand = randNumber.nextInt(names_en.length);
                res = names_en[rand];
            }
        }

        return res;

    }

    ArrayList<String> getCommandsName (){
        ArrayList<String> names = new ArrayList<String>();
        for (int i = 1; i <= 8; i++) {
            String nameRow = "tr_command" + i;
            int idRow = getResources().getIdentifier(nameRow, "id", getPackageName());
            TableRow row = (TableRow) findViewById(idRow);
            if (row.getVisibility() == View.VISIBLE){
                String thisEditTextName = "et_commandName" + i;
                int thisId = getResources().getIdentifier(thisEditTextName, "id", getPackageName());
                EditText thisEditText = (EditText) findViewById(thisId);

                names.add(thisEditText.getText().toString());
            }
            else break;
        }
        return names;
    }

    int getCountCommands (){
        int countCommands = 0;
        for (int i = 1; i <= 8; i++) {
            String nameRow = "tr_command" + i;
            int idRow = getResources().getIdentifier(nameRow, "id", getPackageName());
            TableRow row = (TableRow) findViewById(idRow);
            if (row.getVisibility() == View.VISIBLE){
                countCommands++;
            }
            else break;
        }
        return countCommands;
    }
}
