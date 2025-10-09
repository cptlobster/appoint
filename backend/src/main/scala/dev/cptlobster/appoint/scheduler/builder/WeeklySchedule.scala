package dev.cptlobster.appoint.scheduler.builder

import dev.cptlobster.appoint.scheduler.{AppointmentSchedule, Slot}
import dev.cptlobster.appoint.util.*

import java.time.{DayOfWeek, Instant, LocalTime, ZoneId}

case class AvailabilitySlot(dayOfWeek: DayOfWeek,
                            start: LocalTime,
                            end: LocalTime) {
  def within(tz: ZoneId, slot: Slot): Boolean = {
    ???
  }
}

case class WeeklySchedule(timezone: ZoneId,
                          availabilities: List[AvailabilitySlot]) extends Schedule {

  override def generate(appointmentSchedule: AppointmentSchedule, starting: Instant): List[Slot] = {
    var timeStart = (starting + appointmentSchedule.schedulingRange._1) alignTo appointmentSchedule.appointmentLength
    var timeEnd = starting + appointmentSchedule.schedulingRange._2

    (for (slotStart <- TemporalRangeIterator(timeStart, timeEnd, appointmentSchedule.bufferBetweenStarts)) yield {
      Slot(slotStart, appointmentSchedule.appointmentLength, appointmentSchedule.hosts)
    }).filter(s => availabilities.exists(av => av.within(timezone, s))).toList
  }
}
