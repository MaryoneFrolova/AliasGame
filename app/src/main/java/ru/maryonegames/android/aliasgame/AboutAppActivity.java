package ru.maryonegames.android.aliasgame;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AboutAppActivity extends AppCompatActivity {

    TextView txt_about;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);

        txt_about = (TextView) findViewById(R.id.txt_about);
        txt_about.setText(getString(R.string.oficial));
    }

    public void undo (View view){
        Intent intent = new Intent(AboutAppActivity.this, StartActivity.class );
        startActivity(intent);
        finish();
    }

    public void go_site(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://maryone.ru/alias-populyarnaya-igra-dlya-vesyoloy-kompanii/"));
        startActivity(browserIntent);
    }
}
