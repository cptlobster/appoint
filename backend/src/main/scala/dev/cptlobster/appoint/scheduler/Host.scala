package dev.cptlobster.appoint.scheduler

import dev.cptlobster.appoint.scheduler.builder.ScheduleOverlay
import dev.cptlobster.appoint.scheduler.builder.overrides.CustomOverride
import net.fortuna.ical4j.model.parameter.Role
import net.fortuna.ical4j.model.property.Attendee

import java.time.{Duration, Instant}
import java.util.UUID

case class Host(id: UUID,
                contact: Contact,
                baseSchedule: AppointmentSchedule,
                overlays: List[ScheduleOverlay],
                overrides: List[CustomOverride],
                maxAppointmentsPerDay: Option[Int] = None) {
  def asAttendee(role: Role = Role.CHAIR): Attendee = contact.asAttendee(role)

  def slots(schedule: AppointmentSchedule, start: Instant): List[Slot] = {
    val appointmentLength: Duration = schedule.appointmentLength
    val bufferBetweenStarts: Duration = schedule.bufferBetweenStarts

    val base = baseSchedule.getSlots(start)
    val overlaid = overlays.foldLeft(base)((base, o) => o.applyTo(schedule, base))
    val overridden = overrides.foldLeft(overlaid)((base, o) => o.applyTo(schedule, base))

    overridden
  }

  def slots(schedule: AppointmentSchedule): List[Slot] = slots(schedule, Instant.now())
}
