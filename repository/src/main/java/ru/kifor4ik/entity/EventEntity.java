package ru.kifor4ik.entity;

import jakarta.persistence.*;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Objects;

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

    public EventEntity(String theme, String description, String manager, Time startTime,Date startDate) {
        this();
        this.theme = theme;
        this.description = description;
        this.manager = manager;
        this.startDate = startDate;
        this.startTime = startTime;
    }

    public EventEntity(Long id, String theme, String description, String manager, Time startTime, Date startDate) {
        this(theme, description, manager,startTime,startDate);
        this.id = id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Time getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Time creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventEntity that = (EventEntity) o;
        return Objects.equals(theme, that.theme)
                && Objects.equals(description, that.description)
                && Objects.equals(manager, that.manager) && Objects.equals(startTime, that.startTime)
                && Objects.equals(startDate, that.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(theme, description, manager, startTime, startDate);
    }

    @Override
    public String toString() {
        return "EventEntity{" +
                "id=" + id +
                ", theme='" + theme + '\'' +
                ", description='" + description + '\'' +
                ", manager='" + manager + '\'' +
                ", startTime=" + startTime +
                ", startDate=" + startDate +
                ", creationDate=" + creationDate +
                ", creationTime=" + creationTime +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
