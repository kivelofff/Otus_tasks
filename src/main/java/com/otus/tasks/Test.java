package com.otus.tasks;

import com.otus.tasks.service.TesterService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;


public class Test {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/config.xml");
        TesterService testerService = context.getBean(TesterService.class);
        testerService.testParticipant("Ivanov", "Ivan");
        testerService.printResults();
    }
}
