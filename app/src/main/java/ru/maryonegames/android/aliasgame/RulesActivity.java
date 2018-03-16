package ru.maryonegames.android.aliasgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RulesActivity extends AppCompatActivity {

    TextView tv_rules;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        tv_rules = (TextView) findViewById(R.id.tv_rules);
        tv_rules.setText(getString(R.string.rules));
    }

    public void undo (View view){
        Intent intent = new Intent(RulesActivity.this, StartActivity.class );
        startActivity(intent);
        finish();
    }
}
