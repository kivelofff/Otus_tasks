package com.otus.tasks.service;

import com.otus.tasks.domain.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class PersonService {
    private List<Person> persons;

    public PersonService() {
        this.persons = new ArrayList<>();
    }

    public int addPerson(String name, String surname) {
        Person person = new Person(name, surname);
        int personId = persons.size();
        persons.add(person);
        return personId;
    }

    public void removePerson(Person person) {
        persons.remove(person);

    }

    public Person getPerson(int number) {
        return persons.get(number);
    }

    public Map<String, Double> getResults() {
        Map<String, Double> results = new HashMap<>();
        for (int i = 0; i < persons.size(); i++) {
            Person currentPerson = persons.get(i);
            results.put(currentPerson.getName() + " " + currentPerson.getSurname(), currentPerson.getResult());
        }
        return results;
    }



}
