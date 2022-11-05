package ru.kifor4ik;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import ru.kifor4ik.entity.EventEntity;
import ru.kifor4ik.repository.EventRepository;
import ru.kifor4ik.utility.HibernateUtil;

import java.sql.Date;
import java.sql.Time;

@ComponentScan
@SpringBootApplication
public class Starter implements CommandLineRunner {

    public static void main(String[] args) {
        try{
            SpringApplication.run(Starter.class,args);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Autowired
    private EventRepository eventRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Я запустился!");
        eventRepository.create(new EventEntity(1L,"Theme","DescriptionDescriptionDescriptionDescription","Manager",
                Time.valueOf("12:12:00"), Date.valueOf("2024-12-12")));
        eventRepository.getById(52L);
    }
}
