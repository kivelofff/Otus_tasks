package com.otus.tasks.service;

import com.otus.tasks.domain.Person;

import java.util.ArrayList;
import java.util.List;

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



}
