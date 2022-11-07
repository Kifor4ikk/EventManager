package ru.kifor4ik;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import ru.kifor4ik.service.EventService;

@ComponentScan
@SpringBootApplication
public class Starter implements CommandLineRunner {

    public static void main(String[] args) {
        try {
            SpringApplication.run(Starter.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    EventService eventService;
    @Override
    public void run(String... args) throws Exception {

    }
}
