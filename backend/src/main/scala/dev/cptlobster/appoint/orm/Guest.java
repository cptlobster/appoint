package dev.cptlobster.appoint.orm;

import jakarta.persistence.*;

import java.util.UUID;

/**
 * Contact information for an appointment guest.
 */
@Entity
@Table(name = "guests")
public class Guest {
    @Id
    @GeneratedValue
    public UUID id;
    @Column(nullable = false)
    public String name;
    @Column(nullable = false)
    String email;
    public String phone;
}
