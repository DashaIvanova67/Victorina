package com.example.victorina;

import java.util.ArrayList;
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

    public Vopros(String question, int rightAnswer) {
        this.question = question;
        this.rightAnswer = rightAnswer;
    }

    @Override
    public String toString() {
        return "Vopros{" +
                "question='" + question + '\'' +
                ", rightAnswer=" + rightAnswer +
                ", answers=" + answers +
                '}';
    }

    public void setAnswers(List<String> answers) {
        this.answers = new ArrayList(answers);
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setRightAnswer(int rightAnswer) {
        this.rightAnswer = rightAnswer;
    }
}
