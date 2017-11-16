package com.kesteli.filip.lauzhack.introduction;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kesteli.filip.lauzhack.R;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        Intent intent = new Intent(this, IntroActivity.class);
        startActivity(intent);
    }
}





