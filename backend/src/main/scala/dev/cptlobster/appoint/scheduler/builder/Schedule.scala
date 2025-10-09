package dev.cptlobster.appoint.scheduler.builder

import dev.cptlobster.appoint.scheduler.{AppointmentSchedule, Slot}

import java.time.{Duration, Instant}

trait Schedule {
  def generate(appointmentSchedule: AppointmentSchedule, start: Instant): List[Slot]
  def generate(appointmentSchedule: AppointmentSchedule): List[Slot] = generate(appointmentSchedule, Instant.now())
}
