package dev.cptlobster.appoint.scheduler

import dev.cptlobster.appoint.util.*
import net.fortuna.ical4j.model.parameter.{Cn, Role}
import net.fortuna.ical4j.model.property.Attendee

import java.net.URI

/**
 * Contact information for a host or guest.
 * @param name The name of the contact.
 * @param email The email address of the contact.
 */
class Contact(
             val name: String,
             val email: String
             ) {
  /**
   * Generate an iCalendar attendee object.
   * @param role The [[Role]] that the attendee should have. Defaults to [[Role.REQ_PARTICIPANT]].
   * @return An [[Attendee]] that can be added to a [[net.fortuna.ical4j.model.component.VEvent VEvent]].
   */
  def asAttendee(role: Role = Role.REQ_PARTICIPANT): Attendee = {
    val mailto = URI.create(s"mailto:${email}")
    (new Attendee(mailto)
      :+ role
      :+ new Cn(name)
    ).as[Attendee]
  }
}
