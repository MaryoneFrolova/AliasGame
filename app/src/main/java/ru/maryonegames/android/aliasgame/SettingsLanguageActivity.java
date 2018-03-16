package ru.maryonegames.android.aliasgame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Locale;

public class SettingsLanguageActivity extends AppCompatActivity {

    public static final String APP_PREFERENCES = "myset";
    public static final String CARD_MAIN_LANG = "cardMainLanguage";
    public static final String CARD_ADD_LANG = "cardAddLanguage";
    public static final String APP_LANG = "appLanguage";
    private SharedPreferences mSet;

    RadioButton
            rb_ru_cardMainLanguage, rb_en_cardMainLanguage, rb_uk_cardMainLanguage,
            rb_ru_cardAddLanguage, rb_en_cardAddLanguage, rb_uk_cardAddLanguage,
            rb_ru_appLanguage, rb_en_appLanguage, rb_uk_appLanguage;
    TextView tv_cardAddLanguage;
    CheckBox chb_cardAddLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_language);
        mSet = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        rb_ru_cardMainLanguage = (RadioButton) findViewById(R.id.rb_ru_cardMainLanguage);
        rb_en_cardMainLanguage = (RadioButton) findViewById(R.id.rb_en_cardMainLanguage);
        rb_uk_cardMainLanguage = (RadioButton) findViewById(R.id.rb_uk_cardMainLanguage);

        rb_ru_cardAddLanguage = (RadioButton) findViewById(R.id.rb_ru_cardAddLanguage);
        rb_en_cardAddLanguage= (RadioButton) findViewById(R.id.rb_en_cardAddLanguage);
        rb_uk_cardAddLanguage= (RadioButton) findViewById(R.id.rb_uk_cardAddLanguage);

        rb_ru_appLanguage = (RadioButton) findViewById(R.id.rb_ru_appLanguage);
        rb_en_appLanguage= (RadioButton) findViewById(R.id.rb_en_appLanguage);
        rb_uk_appLanguage= (RadioButton) findViewById(R.id.rb_uk_appLanguage);

        tv_cardAddLanguage = (TextView) findViewById(R.id.tv_cardAddLanguage);
        chb_cardAddLanguage = (CheckBox) findViewById(R.id.chb_cardAddLanguage);


        chb_cardAddLanguage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chb_cardAddLanguage.isChecked())
                {
                    tv_cardAddLanguage.setVisibility(View.VISIBLE);
                    rb_ru_cardAddLanguage.setVisibility(View.VISIBLE);
                    rb_en_cardAddLanguage.setVisibility(View.VISIBLE);
                    rb_uk_cardAddLanguage.setVisibility(View.VISIBLE);
                }
                else
                {
                    tv_cardAddLanguage.setVisibility(View.INVISIBLE);
                    rb_ru_cardAddLanguage.setVisibility(View.INVISIBLE);
                    rb_en_cardAddLanguage.setVisibility(View.INVISIBLE);
                    rb_uk_cardAddLanguage.setVisibility(View.INVISIBLE);
                }
            }
        });

        String appLanguage = "none";
        if (mSet.contains(APP_LANG)) {
            appLanguage = mSet.getString(APP_LANG, "none");
        }
        else {
            appLanguage = getApplicationContext().getResources().getConfiguration().locale.getLanguage();
        }
        if (appLanguage.toUpperCase().equals("RU")) {
            rb_ru_appLanguage.setChecked(true);
        }
        else if (appLanguage.toUpperCase().equals("UK")) {
            rb_uk_appLanguage.setChecked(true);
        }
        else {
            rb_en_appLanguage.setChecked(true);
        }

        String cardAddLanguage = "none";
        if (mSet.contains(CARD_ADD_LANG)){
            cardAddLanguage = mSet.getString(CARD_ADD_LANG, "none");
            if (cardAddLanguage.equals("RU"))
            {
                tv_cardAddLanguage.setVisibility(View.VISIBLE);
                rb_en_cardAddLanguage.setVisibility(View.VISIBLE);
                rb_ru_cardAddLanguage.setVisibility(View.VISIBLE);
                rb_uk_cardAddLanguage.setVisibility(View.VISIBLE);
                rb_ru_cardAddLanguage.setChecked(true);
                chb_cardAddLanguage.setChecked(true);
            }
            else if (cardAddLanguage.equals("EN"))
            {
                tv_cardAddLanguage.setVisibility(View.VISIBLE);
                rb_en_cardAddLanguage.setVisibility(View.VISIBLE);
                rb_ru_cardAddLanguage.setVisibility(View.VISIBLE);
                rb_uk_cardAddLanguage.setVisibility(View.VISIBLE);
                rb_en_cardAddLanguage.setChecked(true);
                chb_cardAddLanguage.setChecked(true);
            }
            else if (cardAddLanguage.equals("UK"))
            {
                tv_cardAddLanguage.setVisibility(View.VISIBLE);
                rb_en_cardAddLanguage.setVisibility(View.VISIBLE);
                rb_ru_cardAddLanguage.setVisibility(View.VISIBLE);
                rb_uk_cardAddLanguage.setVisibility(View.VISIBLE);
                rb_uk_cardAddLanguage.setChecked(true);
                chb_cardAddLanguage.setChecked(true);
            }
        }

        else {

        }
        rb_ru_cardMainLanguage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (rb_ru_cardMainLanguage.isChecked()){
                    rb_ru_cardAddLanguage.setEnabled(false);
                    rb_ru_cardAddLanguage.setChecked(false);
                    rb_ru_cardAddLanguage.setChecked(true);
                }
                else
                {
                    rb_ru_cardAddLanguage.setEnabled(true);
                }
            }
        });

        rb_en_cardMainLanguage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (rb_en_cardMainLanguage.isChecked()){
                    rb_en_cardAddLanguage.setEnabled(false);
                    rb_en_cardAddLanguage.setChecked(false);
                    rb_ru_cardAddLanguage.setChecked(true);
                }
                else
                {
                    rb_en_cardAddLanguage.setEnabled(true);
                }
            }
        });

        rb_uk_cardMainLanguage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (rb_uk_cardMainLanguage.isChecked()){
                    rb_uk_cardAddLanguage.setEnabled(false);
                    rb_uk_cardAddLanguage.setChecked(false);
                    rb_en_cardAddLanguage.setChecked(true);
                }
                else
                {
                    rb_uk_cardAddLanguage.setEnabled(true);
                }
            }
        });

        String cardMainLanguage;


        if (mSet.contains(CARD_MAIN_LANG)){
            cardMainLanguage = mSet.getString(CARD_MAIN_LANG, "en");
            if (cardMainLanguage.equals("ru"))
            {
                rb_ru_cardMainLanguage.setChecked(true);
            }
            else if (cardMainLanguage.equals("uk"))
            {
                rb_uk_cardMainLanguage.setChecked(true);
            }
            else
            {
                rb_en_cardMainLanguage.setChecked(true);
            }
        }

        else {
            String locale = getApplicationContext().getResources().getConfiguration().locale.getISO3Country();
            if (locale.toUpperCase().equals("RU")) {
                rb_ru_cardMainLanguage.setChecked(true);
            }
            else if (locale.toUpperCase().equals("UK")) {
                rb_uk_cardMainLanguage.setChecked(true);
            }
            else {
                rb_en_cardMainLanguage.setChecked(true);
            }
        }

    }

    public void saveSettings (View view){
        String cardMainLanguage;
        if (rb_ru_cardMainLanguage.isChecked()) cardMainLanguage = "RU";
        else if (rb_uk_cardMainLanguage.isChecked()) cardMainLanguage = "UK";
        else cardMainLanguage = "EN";

        String cardAddLanguage="none";
        if (chb_cardAddLanguage.isChecked() && rb_ru_cardAddLanguage.isChecked()) cardAddLanguage="RU";
        if (chb_cardAddLanguage.isChecked() && rb_en_cardAddLanguage.isChecked()) cardAddLanguage="EN";
        if (chb_cardAddLanguage.isChecked() && rb_uk_cardAddLanguage.isChecked()) cardAddLanguage="UK";

        String appLanguage="none";
        if (rb_ru_appLanguage.isChecked()) appLanguage = "ru";
        else if (rb_uk_appLanguage.isChecked()) appLanguage = "uk";
        else appLanguage = "en";

        //-----save settings in mSet-----
        mSet = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSet.edit();
        editor.putString(CARD_MAIN_LANG, cardMainLanguage);
        editor.putString(CARD_ADD_LANG, cardAddLanguage);
        editor.putString(APP_LANG, appLanguage);
        editor.apply();
        //-----save settings in mSet-----

        //-----update settings app language---//
        Locale locale = new Locale(appLanguage);
        Locale.setDefault(locale);
        Resources resources = getApplicationContext().getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        //-----update settings app language---//


        Intent intent = new Intent(SettingsLanguageActivity.this, StartActivity.class );
        startActivity(intent);
        finish();

    }



    public void pay_btn(View view){
        Intent intent = new Intent(SettingsLanguageActivity.this, Main12Activity.class);
        startActivity(intent);
    }

    public void go_site(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://maryone.ru/alias-populyarnaya-igra-dlya-vesyoloy-kompanii/"));
        startActivity(browserIntent);
    }

    public void write_msg(View view){
        Intent email = new Intent(Intent.ACTION_SENDTO);
        email.setData(Uri.parse("mailto:"));
        email.putExtra(Intent.EXTRA_EMAIL,"maryonegames@gmail.com");

        email.putExtra(Intent.EXTRA_SUBJECT, "AliasGame mail");
        email.putExtra(Intent.EXTRA_TEXT, "Хочу предложить следующее: \n\nНовые слова: \n1. \n2. \n3. \n\nНазвания команд: \n1. \n2. \n3. \n\nДругие пожелания: \n1. \n2. \n3. \n\n ");
        if (email.resolveActivity(getPackageManager()) != null)
            startActivity(email);
    }
}
