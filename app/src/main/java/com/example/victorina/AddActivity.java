package com.example.victorina;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class AddActivity extends AppCompatActivity {

    private RecyclerView listView;
    private Vopros vopros;
    private EditText questionField, rightAnswer;
    private Button saveBut, addItemBut;
    private AnswerAdapter adapter;
    private List<String> answersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        getSupportActionBar().setTitle("Добавление вопросов");

        listView = findViewById(R.id.addList);
        questionField = findViewById(R.id.addQuestionField);
        rightAnswer = findViewById(R.id.addRightAnswerNum);
        saveBut = findViewById(R.id.addSaveButton);
        addItemBut = findViewById(R.id.addItemButton);

        vopros = new Vopros("",0);
        answersList = new ArrayList<>();
        answersList.add("");

        listView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AnswerAdapter(this,answersList);
        listView.setAdapter(adapter);
        questionField.setText(vopros.getQuestion());
        rightAnswer.setText(vopros.getRightAnswer() + "");
    }

    public void addItem(View view) {
        answersList.add("");
        adapter.notifyDataSetChanged();
    }

    public void addVoprosToDB(View view) {
        vopros.setRightAnswer(Integer.parseInt(rightAnswer.getText().toString()));
        vopros.setQuestion(questionField.getText().toString());
        vopros.setAnswers(adapter.getAnswerList());

        DB.insertVopros(vopros,view.getContext());

        answersList.clear();
        answersList.add("");
        questionField.setText("");
        rightAnswer.setText("");
        adapter.notifyDataSetChanged();
    }
}