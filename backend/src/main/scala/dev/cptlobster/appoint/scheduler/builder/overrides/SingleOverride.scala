package dev.cptlobster.appoint.scheduler.builder.overrides

import dev.cptlobster.appoint.scheduler.{AppointmentSchedule, Slot}

import java.time.Instant
import java.util.UUID

/**
 * A manual override for a specific time range. Note that this will alter appointment slots through the entire time
 * range, so spanning over multiple days may produce undesired results!
 * @param start The start date of the override.
 * @param end The end date of the override.
 * @param free If true, this will add appointments
 */
case class SingleOverride(id: UUID = UUID.randomUUID(),
                          start: Instant,
                          end: Instant,
                          free: Boolean = false) extends CustomOverride {
  override def applyTo(schedule: AppointmentSchedule, slots: List[Slot]): List[Slot] = ???
}
