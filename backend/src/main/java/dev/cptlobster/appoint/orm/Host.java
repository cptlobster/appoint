package dev.cptlobster.appoint.orm;

import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;

/**
 * Contact information for an appointment host.
 */
@Entity
@Table(name = "hosts")
public class Host {
    @Id
    @GeneratedValue
    public UUID id;
    @Column(nullable = false)
    public String name;
    @Column(nullable = false)
    public String email;
    public String phone;
    @OneToMany
    public Set<Appointment> appointments;

    public Host() {}

    public Host(UUID id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Host(UUID id, String name, String email) {
        this(id, name, email, null);
    }

    public Host(String name, String email, String phone) {
        this(UUID.randomUUID(), name, email, phone);
    }

    public Host(String name, String email) {
        this(name, email, null);
    }
}
