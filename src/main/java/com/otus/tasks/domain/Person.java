package com.otus.tasks.domain;

public class Person {
    private String name;
    private String surname;
    private double result;

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
