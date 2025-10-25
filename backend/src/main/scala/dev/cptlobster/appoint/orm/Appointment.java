package dev.cptlobster.appoint.orm;

import jakarta.persistence.*;

import java.time.Duration;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;

/**
 * A scheduled appointment.
 */
@Entity
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue
    public UUID id;
    @ManyToOne
    @JoinColumn(name = "id")
    public AppointmentSchedule schedule;
    @ManyToMany
    @JoinColumn(name = "id")
    public Set<Guest> guests;
    @Column(nullable = false)
    public Instant timestamp;
    @Column(nullable = false)
    public Duration length;
}
