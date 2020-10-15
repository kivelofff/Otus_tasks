package com.otus.tasks.domain;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private String question;
    private List<String> options;
    private List<String> rightAnswers;
    private double price;

    public Question(String question, List<String> options, List<String> rightAnswers) {
        this.question = question;
        this.options = options;
        this.rightAnswers = rightAnswers;
        price = 1.0d;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public List<String> getRightAnswers() {
        return rightAnswers;
    }

    public double getPrice() {
        return price;
    }
}
