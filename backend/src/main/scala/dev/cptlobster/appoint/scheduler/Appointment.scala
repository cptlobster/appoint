package dev.cptlobster.appoint.scheduler

import dev.cptlobster.appoint.util.*
import net.fortuna.ical4j.model.component.VEvent
import net.fortuna.ical4j.model.parameter.Role
import net.fortuna.ical4j.model.property.Uid
import net.fortuna.ical4j.model.{Calendar, TimeZoneRegistryFactory}

import java.time.{Duration, Instant}
import java.util.UUID

/**
 * A record of a scheduled appointment.
 * @param id The appointment's UUID.
 * @param name The appointment name.
 * @param host The host of the appointment.
 * @param guests The list of guests at this appointment.
 * @param start The start time of the appointment.
 * @param length The length of the appointment.
 * @param desc The long description field that will be appended to the iCalendar event.
 */
case class Appointment(
                 id: UUID = UUID.randomUUID(),
                 name: String,
                 host: Contact,
                 guests: List[Contact],
                 start: Instant,
                 length: Duration,
                 desc: String = ""
                 ) {
  val end: Instant = start.plus(length)

  /**
   * Add a guest to this appointment.
   * @param guest The [[Contact]] to be added as a guest.
   */
  def withGuest(guest: Contact): Appointment = Appointment(id, name, host, guest :: guests, start, length, desc)

  /**
   * Add multiple guests to this appointment.
   * @param other_guests The [[Contact]]s to be added as guests.
   */
  def withGuests(other_guests: List[Contact]): Appointment = Appointment(id, name, host, other_guests ::: guests, start, length, desc)

  /**
   * Generate a single-event iCalendar object from this appointment.
   * @return A [[Calendar]] that can be used to generate an ICS file.
   */
  def asCalendar: Calendar = {
    val event = asVEvent

    // create a calendar containing this single event
    val cal = new Calendar()
      .withProdId("-//cptlobster.dev//Appoint Scheduler//EN")
      .withDefaults()
      .withComponent(event)
      .getFluentTarget

    cal
  }

  /**
   * Generate an iCalendar event from this appointment. This is not a complete calendar, you will still need to add it
   * to an iCalendar object in order to do anything useful with it.
   * @return a [[VEvent]] that can be added to a [[Calendar]].
   */
  def asVEvent: VEvent = {
    // generate things in UTC (hopefully this works!)
    val registry = TimeZoneRegistryFactory.getInstance.createRegistry
    val timezone = registry.getTimeZone("UTC")
    val tz = timezone.getVTimeZone

    // create event with all basic components
    val event = new VEvent(start, end, name)
      :+ tz.getTimeZoneId
      :+ new Uid(id.toString)
      :+ host.asAttendee(Role.CHAIR)
      :++ guests.map(_.asAttendee(Role.REQ_PARTICIPANT))

    event.as[VEvent]
  }
}