package com.otus.tasks.service;

import com.otus.tasks.domain.Person;
import com.otus.tasks.domain.Question;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TesterService {
    private PersonService personService;
    private CsvReader csvReader;

    public TesterService() {
        this.personService = new PersonService();
        this.csvReader = new CsvReader("c:\\test\\test.csv");
    }

    public void testParticipant (String surname, String name) throws IOException {
        int id = personService.addPerson(surname, name);
        List<Question> questions = csvReader.getQuestions();
        String answer;
        Person person = personService.getPerson(id);
        double score = 0.0d;
        for (int i = 0; i < questions.size()-1; i++) {
            score += askQuestion(questions.get(i));

        }
        person.setResult(score);
    }

    private double askQuestion(Question question) throws IOException {
        double result = 0.0d;
        ConsoleHelper.writeMessage(question.getQuestion());
        for (int j = 1; j < question.getOptions().size(); j++) {
            ConsoleHelper.writeMessage(j + " - " + question.getOptions().get(j-1));
        }
        String answer = ConsoleHelper.readMessage();
        List<String> rightAnswers = question.getRightAnswers();
        double scorePerOprion = question.getPrice()/rightAnswers.size();
        if (answer.contains(" ")) {
            List<String> userAnswers = Arrays.asList(answer.split(" "));
            for (int i = 0; i < userAnswers.size(); i++) {
                if (rightAnswers.contains(userAnswers.get(i))) {
                    result += scorePerOprion;
                } else {
                    result -= scorePerOprion;
                }
            }
            if (result <0) {
                result=0;
            }
        } else {

            if (rightAnswers.size() > 1) {
                if (rightAnswers.contains(answer)) {
                   result = scorePerOprion;
                }
            } else {
                if (rightAnswers.contains(answer)) {
                    result = question.getPrice();
                }
            }
        }
        return result;
    }
}
