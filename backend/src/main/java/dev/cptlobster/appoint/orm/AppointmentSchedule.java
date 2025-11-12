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
    @JoinColumn(name = "schedule_id", nullable = false)
    public Set<Availability> availabilities;
    @OneToMany
    @JoinColumn(name = "schedule_id", nullable = false)
    public Set<Overlay> overlays;
    @ManyToOne
    @JoinColumn(name = "host_id", nullable = false)
    public Host host;
    @Column(nullable = false)
    public Duration appointmentLength;
    @Column(nullable = false)
    public Duration bufferTime = Duration.ZERO;
    @OneToMany
    public Set<Appointment> appointments;
}
