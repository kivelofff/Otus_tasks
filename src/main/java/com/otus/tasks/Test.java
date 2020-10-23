package com.otus.tasks;

import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;
import com.otus.tasks.service.ConsoleHelper;
import com.otus.tasks.service.TesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.io.IOException;
import java.util.Locale;

@Configuration
@ComponentScan("com.otus.tasks.service")
@PropertySource("classpath:settings.properties")
public class Test {
    private static Detector detector;


    private static MessageSource messageSource = getMessageSource();

    public static void main(String[] args) throws IOException {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Test.class);
        TesterService testerService = context.getBean(TesterService.class);
        ConsoleHelper.writeMessage("Enter name and surname. use space as delimeter:");
        String username;
        while ( (username = ConsoleHelper.readMessage()) == null && !username.contains(" ")) {
            ConsoleHelper.writeMessage("wrong format, try again");
            username = ConsoleHelper.readMessage();
        }
        String lang = "ru";
        String hello;
        if (lang == null) {lang = "ru";}
        hello = messageSource.getMessage("hello.user", new String[] {username}, new Locale("ru", "RU"));
        System.out.println(hello);

        String[] nameAndSurname  = username.split(" ");

        testerService.testParticipant(nameAndSurname[1], nameAndSurname[0]);
        testerService.printResults();
    }

    public static MessageSource getMessageSource() {
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasename("/messages/messages");
        source.setDefaultEncoding("UTF-8");
        return source;

    }

    private static String getLanguage(String input) {
        if (detector == null) {
            try {
                initDetector();

            } catch (LangDetectException e) {
                e.printStackTrace();
            }
        }
        detector.append(input);
        try {
            return detector.detect();
        } catch (LangDetectException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void initDetector() throws LangDetectException {
        DetectorFactory.loadProfile("trunk/profile");
        detector = DetectorFactory.create();
    }
}
