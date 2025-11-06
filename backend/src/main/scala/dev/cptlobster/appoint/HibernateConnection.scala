package dev.cptlobster.appoint

import dev.cptlobster.appoint.orm.{Appointment, AppointmentCalendar, AppointmentSchedule, Availability, Guest, Host, Overlay}
import org.hibernate.SessionFactory
import org.hibernate.jpa.HibernatePersistenceConfiguration

/**
 * Trait for managing connection to the backend database.
 */
trait HibernateConnection {
  protected val sessionFactory: SessionFactory = new HibernatePersistenceConfiguration("appoint")
    .managedClass(classOf[Appointment])
    .managedClass(classOf[AppointmentCalendar])
    .managedClass(classOf[AppointmentSchedule])
    .managedClass(classOf[Availability])
    .managedClass(classOf[Guest])
    .managedClass(classOf[Host])
    .managedClass(classOf[Overlay])
    .jdbcUrl("jdbc:h2:mem:appoint")
    .jdbcCredentials("sa", "")
    .jdbcPoolSize(16)
    .showSql(true, true, true)
    .createEntityManagerFactory()
}
