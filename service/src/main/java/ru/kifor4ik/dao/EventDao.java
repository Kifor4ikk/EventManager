package ru.kifor4ik.dao;

import lombok.*;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class EventDao {

    private Long id;
    private String theme;
    private String description;
    private String manager;
    private Date date;
    private String time;

}
