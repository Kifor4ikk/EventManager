package ru.kifor4ik.mapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import ru.kifor4ik.entity.EventEntity;
import ru.kifor4ik.model.Event;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;

@Component
public class EventMapper {

    public EventEntity fromModelToEntity(Event event){
        EventEntity eventEntity = new EventEntity();

        eventEntity.setId(event.getId());
        eventEntity.setTheme(event.getTheme());
        eventEntity.setManager(event.getManager());
        eventEntity.setDescription(event.getDescription());
        eventEntity.setStartDate(Date.valueOf(event.getDateTime().toLocalDate()));
        eventEntity.setStartTime(Time.valueOf(event.getDateTime().toLocalTime()));

        return eventEntity;
    }

    public Event fromEntityToModel(EventEntity eventEntity){
        Event event = new Event();

        event.setId(eventEntity.getId());
        event.setTheme(eventEntity.getTheme());
        event.setManager(eventEntity.getManager());
        event.setDescription(eventEntity.getDescription());
        event.setDateTime(LocalDateTime.of(eventEntity.getStartDate().toLocalDate(), eventEntity.getStartTime().toLocalTime()));

        return event;
    }
}
