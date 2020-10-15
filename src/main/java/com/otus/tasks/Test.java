package com.otus.tasks;

import com.otus.tasks.domain.Question;
import com.otus.tasks.service.CsvReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {
        CsvReader br = new CsvReader("C:\\test\\test.csv");
        List<Question> questions = br.getQuestions();
        System.out.println("Done!");
    }
}
