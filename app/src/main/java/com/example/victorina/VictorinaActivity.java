package com.example.victorina;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class VictorinaActivity extends AppCompatActivity {

    private ListView listView;
    private TextView textTitle, questionText;
    private List<Vopros> voprosList;
    private int questionNum = 0;
    private Button buttonNext;
    private List<String> answersList;
    private ArrayAdapter<String> adapter;
    private int otvet = 0; // номер выбранного ответа в вопросе
    private int countRightAnswers = 0; // количество верных ответов


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_victorina);
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
        adapter  =  new  ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, answersList);
        listView.setAdapter(adapter);
        questionText.setText(voprosList.get(questionNum).getQuestion());

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (otvet != 0){
                    if (otvet == voprosList.get(questionNum).getRightAnswer()){
                        countRightAnswers++;
                     //   Log.d("vic777", "верно");
                    }
                    otvet = 0;
                    questionNum++;
                    //снимем галочки для следующего вопроса
                    for (int i=0; i< listView.getCheckedItemPositions().size();i++){
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                            listView.getCheckedItemPositions().setValueAt(i,false);
                        }
                    }
                    if (questionNum < voprosList.size()) {
                        answersList.clear();
                        answersList.addAll(voprosList.get(questionNum).getAnswers());
                        questionText.setText(voprosList.get(questionNum).getQuestion());
                        adapter.notifyDataSetChanged();
                    }else {
                        Dialog.createDialog("Результаты теста", countRightAnswers + " из " + voprosList.size() + " правильно!", view.getContext()).show();
                    }
                }else {
                    Dialog.createDialog("Предупреждение", "Вы не выбрали вариант ответа",view.getContext()).show();
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                otvet = i+1;
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
                startActivity(new Intent(VictorinaActivity.this, AdminActivity.class));
                return true;

            case R.id.exit:
                startActivity(new Intent(VictorinaActivity.this, MainActivity.class));
                 return true;
        }

        return super.onOptionsItemSelected(item);
    }
}