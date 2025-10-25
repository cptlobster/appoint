package dev.cptlobster.appoint.orm;

import jakarta.persistence.*;

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
}
