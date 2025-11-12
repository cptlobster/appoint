package dev.cptlobster.appoint.orm;

import jakarta.persistence.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.UUID;

/**
 * A weekly availability slot on an appointment schedule.
 */
@Entity
@Table(name = "availabilities")
public class Availability {
    @Id
    @GeneratedValue
    public UUID id;
    @Column(nullable = false)
    public DayOfWeek weekday;
    @Column(nullable = false)
    public LocalTime start;
    @Column(nullable = false)
    public LocalTime end;
    
    public Availability() {}
    
    public Availability(UUID id, DayOfWeek weekday, LocalTime start, LocalTime end) {
        this.id = id;
        this.weekday = weekday;
        this.start = start;
        this.end = end;
    }
    
    public Availability(DayOfWeek weekday, LocalTime start, LocalTime end) {
        this(UUID.randomUUID(), weekday, start, end);
    }
}
