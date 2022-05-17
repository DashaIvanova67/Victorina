package com.example.victorina;

import java.util.List;

public class Vopros {

    private String question;
    private int rightAnswer;
    private List<String> answers;

    public String getQuestion() {
        return question;
    }

    public int getRightAnswer() {
        return rightAnswer;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public Vopros(String question, int rightAnswer, List<String> answers) {
        this.question = question;
        this.rightAnswer = rightAnswer;
        this.answers = answers;


    }
}
