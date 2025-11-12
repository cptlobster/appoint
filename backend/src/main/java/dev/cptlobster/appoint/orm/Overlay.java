package dev.cptlobster.appoint.orm;

import jakarta.persistence.*;

import java.net.*;
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
    @ManyToOne
    @JoinColumn(name = "schedule_id", insertable = false, updatable = false)
    public AppointmentSchedule schedule;
    @Column(nullable = false, length = 512)
    public URL url;

    public Overlay() {}

    public Overlay(UUID id, URL url) {
        this.id = id;
        this.url = url;
    }

    public Overlay(UUID id, URI uri) throws MalformedURLException {
        this(id, uri.toURL());
    }

    public Overlay(UUID id, String url) throws URISyntaxException, MalformedURLException {
        this(id, new URI(url));
    }

    public Overlay(URL url) {
        this(UUID.randomUUID(), url);
    }

    public Overlay(URI uri) throws MalformedURLException {
        this(uri.toURL());
    }

    public Overlay(String url) throws URISyntaxException, MalformedURLException {
        this(new URI(url));
    }
}
