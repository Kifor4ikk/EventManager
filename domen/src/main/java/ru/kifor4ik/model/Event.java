package ru.kifor4ik.model;

import lombok.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Event {

    private Long id;
    private String theme;
    private String description;
    private String manager;
    private LocalDateTime dateTime;

    public Event(String theme, String description, String manager, LocalDateTime dateTime) {
        this.theme = theme;
        this.description = description;
        this.manager = manager;
        this.dateTime = dateTime;
    }
}
