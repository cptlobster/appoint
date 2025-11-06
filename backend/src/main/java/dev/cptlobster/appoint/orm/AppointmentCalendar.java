package dev.cptlobster.appoint.orm;

import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;

/**
 * An appointment calendar. This can consist of multiple schedules, i.e. for multiple hosts.
 */
@Entity
@Table(name = "calendars")
public class AppointmentCalendar {
    @Id
    @GeneratedValue
    public UUID id;
    @OneToMany
    @JoinColumn(name = "id")
    public Set<AppointmentSchedule> schedules;
    @Column(nullable = false)
    public String name;
    public String description;

    public AppointmentCalendar() {}

    public AppointmentCalendar(UUID id, Set<AppointmentSchedule> schedules, String name, String description) {
        this.id = id;
        this.schedules = schedules;
        this.name = name;
        this.description = description;
    }

    public AppointmentCalendar(Set<AppointmentSchedule> schedules, String name, String description) {
        this(UUID.randomUUID(), schedules, name, description)
    }
}
