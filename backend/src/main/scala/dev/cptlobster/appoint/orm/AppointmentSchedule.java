package dev.cptlobster.appoint.orm;

import jakarta.persistence.*;

import java.time.Duration;
import java.util.Set;
import java.util.UUID;

/**
 * A single host's appointment schedule.
 */
@Entity
@Table(name = "schedules")
public class AppointmentSchedule {
    @Id
    @GeneratedValue
    public UUID id;
    @OneToMany
    @JoinColumn(name = "id")
    public Set<Availability> availabilities;
    @OneToMany
    @JoinColumn(name = "id")
    public Set<Overlay> overlays;
    @ManyToOne
    @JoinColumn(name = "id")
    public Host host;
    @Column(nullable = false)
    public Duration appointmentLength;
    public Duration bufferTime = Duration.ZERO;
}
