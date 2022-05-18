package com.example.victorina;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private TextView textTitle, questionText;
    private List<Vopros> voprosList;
    private int questionNum = 0;
    private Button buttonNext;
    private List<String> answersList;
    private ArrayAdapter<String> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Викторина на знание Смоленска");

        listView = findViewById(R.id.answers);
        textTitle = findViewById(R.id.textTitle);
        questionText = findViewById(R.id.questionText);
        buttonNext = findViewById(R.id.buttonNext);

        //получим список всех вопросов с ответами из БД
        voprosList = DB.dbToClass(this);
        answersList = voprosList.get(questionNum).getAnswers();

      //  Log.d("vic777", answersList.toString());

      //  Log.d("vic777", voprosList.toString());
        adapter  =  new  ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, answersList);
        listView.setAdapter(adapter);
        questionText.setText(voprosList.get(questionNum).getQuestion());

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                questionNum++;
                if (questionNum < voprosList.size()) {
                    answersList.clear();
                    answersList.addAll(voprosList.get(questionNum).getAnswers());
                    questionText.setText(voprosList.get(questionNum).getQuestion());
                    adapter.notifyDataSetChanged();
                    Log.d("vic777", answersList.toString());
                }else {
                    Dialog.createDialog("Предупреждение", "Это был последний вопрос!", view.getContext()).show();
                }


            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch(id){

            case R.id.admin:
                startActivity(new Intent(MainActivity.this, AdminActivity.class));
                return true;

            case R.id.exit:
                 return true;
        }

        return super.onOptionsItemSelected(item);
    }
}