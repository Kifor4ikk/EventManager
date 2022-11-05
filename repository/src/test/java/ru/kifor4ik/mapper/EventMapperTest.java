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

@Test
public class EventMapperTest {

    private EventEntity eventEntity;
    private Event event;
    private EventMapper eventMapper = new EventMapper();
    @BeforeTest
    public void setData(){
        eventEntity = new EventEntity(1L,"Theme","Description","Manager",
                Time.valueOf("12:12:00"), Date.valueOf("2012-12-12"));

        event = new Event(1L, "Theme","Description","Manager",
                LocalDateTime.of(LocalDate.of(2012,12,12), LocalTime.of(12,12,00)));
    }


    @Test
    public void fromEntityToModelTest(){
        Assert.assertEquals(event, eventMapper.fromEntityToModel(eventEntity));
    }

    @Test
    public void fromModelToEntityTest(){
        Assert.assertEquals(eventEntity, eventMapper.fromModelToEntity(event));
    }
}
