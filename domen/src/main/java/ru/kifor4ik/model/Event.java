package ru.kifor4ik.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Event {

    private String theme;
    private String description;
    private String manager;
    private LocalDateTime dateTime;

    public Event() {
    }

    public Event(String theme, String description, String manager, LocalDateTime dateTime) {
        this.theme = theme;
        this.description = description;
        this.manager = manager;
        this.dateTime = dateTime;
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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(theme, event.theme)
                && Objects.equals(description, event.description)
                && Objects.equals(manager, event.manager)
                && Objects.equals(dateTime, event.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(theme, description, manager, dateTime);
    }

    @Override
    public String toString() {
        return "Event{" +
                " theme='" + theme + '\'' +
                ", description='" + description + '\'' +
                ", manager='" + manager + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
