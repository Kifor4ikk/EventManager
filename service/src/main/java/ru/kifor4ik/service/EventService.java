package ru.kifor4ik.service;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kifor4ik.entity.EventEntity;
import ru.kifor4ik.exception.NotFoundException;
import ru.kifor4ik.mapper.EventMapper;
import ru.kifor4ik.model.Event;
import ru.kifor4ik.repository.EventRepository;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class EventService {

    private EventRepository eventRepository;
    private EventMapper eventMapper;

    @Autowired
    public EventService(EventRepository eventRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }

    public boolean create(Event event) {
        return eventRepository.createOrUpdate(eventMapper.fromModelToEntity(event));
    }

    public Event getById(Long id){
        EventEntity eventEntity = eventRepository.getById(id);
        if(eventEntity == null || eventEntity.isDeleted()) throw new NotFoundException("Event with ID " + id + " was not found. Maybe it was deleted?");
        return eventMapper.fromEntityToModel(eventEntity);
    }
    public EventEntity getEntityById(Long id){
        EventEntity eventEntity = eventRepository.getById(id);
        if(eventEntity == null || eventEntity.isDeleted()) throw new NotFoundException("Event with ID " + id + " was not found.");
        return eventEntity;
    }

    public boolean update(Long id, Event event){
        //Function call to check exist and save creation time
        EventEntity temp = getEntityById(id);
        EventEntity update = eventMapper.fromModelToEntityWithId(event, id);

        update.setCreationDate(temp.getCreationDate());
        update.setCreationTime(temp.getCreationTime());

        return eventRepository.createOrUpdate(update);
    }

    public boolean softDelete(Long id){
        EventEntity eventEntity = eventMapper.fromModelToEntityWithId(getById(id), id);
        eventEntity.setDeleted(true);
        eventRepository.createOrUpdate(eventEntity);
        return true;
    }

    public boolean delete(Long id){
        eventRepository.delete(eventMapper.fromModelToEntityWithId(getById(id), id));
        return true;
    }

    //if not need any filter just put null instead of filter param
    public List<Event> getAllFiltered(int pageSize, int page, String theme, String manage, LocalDate date, LocalTime time){
        return eventMapper.fromListOfEntityToListOfModel(eventRepository.getByTest(pageSize, page, theme, manage, date, time));
    }
}
