package ru.kifor4ik.mapper;


import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.kifor4ik.entity.EventEntity;
import ru.kifor4ik.model.Event;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Test
public class EventMapperTest {

    private EventEntity eventEntity;
    private Event event;
    private EventMapper eventMapper = new EventMapper();
    private List<EventEntity> eventEntities = new ArrayList<>();
    private List<Event> events = new ArrayList<>();
    @BeforeTest
    public void setData() {
        eventEntity = new EventEntity("Theme", "Description", "Manager",
                Time.valueOf("12:12:00"), Date.valueOf("2012-12-12"));

        events.add(new Event("Theme1", "Description", "Manager",
                LocalDateTime.of(Date.valueOf("2012-12-12").toLocalDate(), Time.valueOf("12:12:00").toLocalTime())));
        events.add(new Event("Theme2", "Description", "Manager",
                LocalDateTime.of(Date.valueOf("2012-12-12").toLocalDate(), Time.valueOf("12:12:00").toLocalTime())));

        eventEntities.add(new EventEntity("Theme1", "Description", "Manager",
                Time.valueOf("12:12:00"), Date.valueOf("2012-12-12")));
        eventEntities.add(new EventEntity("Theme2", "Description", "Manager",
                Time.valueOf("12:12:00"), Date.valueOf("2012-12-12")));

        event = new Event("Theme", "Description", "Manager",
                LocalDateTime.of(LocalDate.of(2012, 12, 12), LocalTime.of(12, 12, 00)));
    }

    @Test
    public void fromEntityToModelTest() {
        Assert.assertEquals(event, eventMapper.fromEntityToModel(eventEntity));
    }

    @Test
    public void fromModelToEntityTest() {
        Assert.assertEquals(eventEntity, eventMapper.fromModelToEntity(event));
    }

    @Test
    public void fromListOfEntityToListOfModelTest(){
        Assert.assertEquals(events, eventMapper.fromListOfEntityToListOfModel(eventEntities));
    }
}
