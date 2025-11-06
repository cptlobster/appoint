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
    public String email;
    public String phone;

    public Guest() {}

    public Guest(UUID id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
    
    public Guest(UUID id, String name, String email) {
        this(id, name, email, null)
    }
    
    public Guest(String name, String email, String phone) {
        this(UUID.randomUUID(), name, email, phone)
    }
    
    public Guest(String name, String email) {
        this(name, email, null)
    }
}
