package com.otus.tasks;

import com.otus.tasks.service.TesterService;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.io.IOException;

@Configuration
@ComponentScan("com.otus.tasks.service")
@PropertySource("classpath:settings.properties")
public class Test {
    public static void main(String[] args) throws IOException {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Test.class);
        TesterService testerService = context.getBean(TesterService.class);
        testerService.testParticipant("Ivanov", "Ivan");
        testerService.printResults();
    }

    public MessageSource getMessageSource() {
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasename("/messages/message");
        source.setDefaultEncoding("UTF-8");
        return source;

    }
}
