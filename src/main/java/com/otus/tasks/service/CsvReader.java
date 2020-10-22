package com.otus.tasks.service;

import com.otus.tasks.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class CsvReader {
    private String fileName;
    @Autowired()
    public CsvReader(@Value("${file.path}") String fileName) {
        this.fileName = fileName;
    }

    public List<Question> getQuestions() throws IOException {
        List<Question> questions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String currentRow;
            String[] arrayRow;
            while ((currentRow = br.readLine()) != null) {

                arrayRow = currentRow.split(";");
                String text = arrayRow[0];
                List<String> options = new ArrayList<>();
                for (int i = 1; i < 6; i++) {
                    if (!arrayRow[i].equals("")) {
                        options.add(arrayRow[i]);
                    }
                }
                List<String> rightAnswers = new ArrayList<>();
                for (int i = 6; i <= arrayRow.length-1; i++) {
                    if(!arrayRow[i].equals("")) {
                        rightAnswers.add(arrayRow[i]);
                    }
                }
                questions.add(new Question(text, options, rightAnswers));
            }
        }
        return questions;
    }
}
