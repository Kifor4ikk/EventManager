package ru.kifor4ik.Controller;

import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jdk.jfr.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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
    public boolean create (
            @RequestParam String theme,
            @RequestParam String description,
            @RequestParam String manager,
            @RequestParam Date date,
            @RequestParam @Pattern(regexp = "[01][0-9]:[0-5][0-9]:[0-5][0-9]", message = "Bad time pattern") String time
    ) {
        return eventService.create(new Event(theme,description,manager,
                LocalDateTime.of(date.toLocalDate(),Time.valueOf(time).toLocalTime())));
    }

    @GetMapping("/byId")
    public Event get(Long id){
        return eventService.getById(id);
    }

    @PutMapping
    public boolean update(
            @RequestParam Long id,
            @RequestParam String theme,
            @RequestParam String description,
            @RequestParam String manager,
            @RequestParam Date date,
            @RequestParam
            @Pattern(regexp = "[01][0-9]:[0-5][0-9]:[0-5][0-9]", message = "Bad time pattern") String time
    ){
        return eventService.update(id,new Event(theme,description,manager,
                LocalDateTime.of(date.toLocalDate(),Time.valueOf(time).toLocalTime())));
    }

    @PutMapping("/softDelete")
    public boolean softDelete(Long id){
        return eventService.softDelete(id);
    }

    @DeleteMapping
    public boolean delete(Long id){
        return eventService.delete(id);
    }

    @GetMapping("/getAllFiltered")
    public List<Event> getAllFiltered(int pageSize, int page, String theme, String manager, LocalDate date, LocalTime time){
        return eventService.getAllFiltered(pageSize,page,theme,manager,date,time);
    }
}
