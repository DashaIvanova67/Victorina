package com.example.victorina;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AdminActivity extends AppCompatActivity {

    private RecyclerView listView;
    private List<Vopros> voprosList;
    private int questionNum = 0;
    private EditText questionField, rightAnswer;
    private Button nextBut, saveBut;
    private AnswerAdapter adapter;
    private List<String> answersList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        getSupportActionBar().setTitle("Редактирование списка вопросов");

        listView = findViewById(R.id.adminList);
        questionField = findViewById(R.id.questionField);
        rightAnswer = findViewById(R.id.rightAnswerNum);
        nextBut = findViewById(R.id.nextButton);
        saveBut = findViewById(R.id.saveButton);

        //получим список всех вопросов с ответами из БД
        voprosList = DB.dbToClass(this);
        //Log.d("vic777", voprosList.toString());
        answersList = voprosList.get(questionNum).getAnswers();
       // Log.d("vic777", answersList.toString());
        listView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AnswerAdapter(this,answersList);
        listView.setAdapter(adapter);
        questionField.setText(voprosList.get(questionNum).getQuestion());
        rightAnswer.setText(voprosList.get(questionNum).getRightAnswer() + "");

        //Log.d("vic777", voprosList.get(questionNum).getRightAnswer() + "");

        nextBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    questionNum++;
                    if (questionNum < voprosList.size()) {
                        answersList.clear();
                        answersList.addAll(voprosList.get(questionNum).getAnswers());
                        questionField.setText(voprosList.get(questionNum).getQuestion());
                        rightAnswer.setText(voprosList.get(questionNum).getRightAnswer() + "");
                        adapter.notifyDataSetChanged();
                    }
            }
        });

        saveBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voprosList.get(questionNum).setRightAnswer(Integer.parseInt(rightAnswer.getText().toString()));
                voprosList.get(questionNum).setQuestion(questionField.getText().toString());
              //  Log.d("vic777", questionNum + "");
                Log.d("vic777", adapter.getAnswerList().toString());
               voprosList.get(questionNum).setAnswers(adapter.getAnswerList());


                DB.updateVopros(questionNum,voprosList.get(questionNum),view.getContext());
            }
        });





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_admin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch(id){

            case R.id.admin_exit:
                startActivity(new Intent(AdminActivity.this, MainActivity.class));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}