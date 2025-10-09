package dev.cptlobster.appoint.scheduler.builder

import dev.cptlobster.appoint.scheduler.{AppointmentSchedule, Slot}

trait ScheduleOverlay {
  def applyTo(schedule: AppointmentSchedule, slots: List[Slot]): List[Slot]
}
