package dev.cptlobster.appoint.orm;

import jakarta.persistence.*;

import java.net.URL;
import java.util.UUID;

/**
 * A calendar overlay; this is a link to a URL serving an iCalendar file that defines specific free/busy times.
 */
@Entity
@Table(name = "overlays")
public class Overlay {
    @Id
    @GeneratedValue
    public UUID id;
    @Column(nullable = false)
    public URL url;
}
