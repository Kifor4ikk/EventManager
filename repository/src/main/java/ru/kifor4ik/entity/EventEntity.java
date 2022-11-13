package ru.kifor4ik.entity;

import jakarta.persistence.*;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "event_")
public class EventEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, message = "Theme size min = 3")
    @Size(max = 60, message = "Theme size max = 60")
    @NotBlank(message = "Theme should be not blank!")
    @NotNull(message = "Theme should be not null")
    private String theme;

    @Size(min = 20, message = "Description size min = 20")
    @Size(max = 10000, message = "Description size max = 10000")
    @NotBlank(message = "Description should be not blank!")
    @NotNull(message = "Description should be not null")
    private String description;

    @Size(min = 3, message = "Manager size min = 3")
    @Size(max = 70, message = "Manager size max = 70")
    @NotBlank(message = "Manager should be not blank!")
    @NotNull(message = "Manager should be not null")
    private String manager;

    @Future(message = "Event should be in future! Unless you have time machine...")
    @NotNull
    private Date startDate;
    @NotNull
    private Time startTime;
    @NotNull
    private Date creationDate;
    @NotNull
    private Time creationTime;

    @NotNull
    boolean isDeleted;

    public EventEntity() {
        creationDate = Date.valueOf(LocalDateTime.now().toLocalDate());
        creationTime = Time.valueOf(LocalDateTime.now().toLocalTime());
        isDeleted = false;
    }

    public EventEntity(String theme, String description, String manager, Time startTime, Date startDate) {
        this();
        this.theme = theme;
        this.description = description;
        this.manager = manager;
        this.startDate = startDate;
        this.startTime = startTime;
    }

}
