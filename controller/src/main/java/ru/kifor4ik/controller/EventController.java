package ru.kifor4ik.controller;

import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.kifor4ik.dao.EventDao;
import ru.kifor4ik.model.Event;
import ru.kifor4ik.service.EventService;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Validated
@RestController
@RequestMapping("api/v3/event/")
public class EventController {

    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public boolean create(
            @RequestParam String theme,
            @RequestParam String description,
            @RequestParam String manager,
            @RequestParam Date date,
            @RequestParam @Pattern(regexp = "[01][0-9]:[0-5][0-9]:[0-5][0-9]", message = "Bad time pattern") String time
    ) {
        return eventService.create(new Event(theme, description, manager,
                LocalDateTime.of(date.toLocalDate(), Time.valueOf(time).toLocalTime())));
    }

    @GetMapping("/byId")
    public Event get(@RequestParam Long id) {
        return eventService.getById(id);
    }

    @PutMapping
    public boolean update(@RequestBody EventDao eventDao) {return eventService.update(eventDao);
    }

    @PutMapping("/softDelete")
    public boolean softDelete(@RequestParam Long id) {
        return eventService.softDelete(id);
    }

    @DeleteMapping
    public boolean delete(@RequestParam Long id) {
        return eventService.delete(id);
    }

    @GetMapping("/getAll")
    public List<Event> getAllFiltered(
            Integer pageSize, Integer page, String theme, String manager, LocalDate date, LocalTime time) {
        return eventService.getAllFiltered(pageSize, page, theme, manager, date, time);
    }
}
