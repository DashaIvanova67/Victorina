package com.example.victorina;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        Log.d("vic777", DB.dbToClass(this).toString());
    }

    public void openTest(View view) {
        startActivity(new Intent(MainActivity.this, VictorinaActivity.class));
    }

    public void redact(View view) {
        startActivity(new Intent(MainActivity.this, AdminActivity.class));
    }
}