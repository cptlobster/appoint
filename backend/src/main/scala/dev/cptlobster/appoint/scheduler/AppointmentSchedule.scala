package dev.cptlobster.appoint.scheduler

import dev.cptlobster.appoint.scheduler.selector.{HostSelector, LRUHostSelector}

import java.time.{Duration, Instant}
import java.util.UUID
import scala.util.Try

case class AppointmentSchedule(id: UUID = UUID.randomUUID(),
                               name: String,
                               hosts: List[Host],
                               appointmentLength: Duration,
                               schedulingRange: (Duration, Duration),
                               bufferTime: Duration = Duration.ZERO,
                               description: String = "",
                               hostSelectionStrategy: HostSelector = LRUHostSelector(),
                               appointments: List[Appointment] = List()) {
  val bufferBetweenStarts: Duration = appointmentLength.plus(bufferTime)

  /**
   * Get all upcoming available appointment slots.
   * @param starting The date to start searching from.
   * @return All available appointment slots within the scheduling range.
   */
  def getSlots(starting: Instant): List[Slot] = {
    hosts.map(_.slots(this, starting)).reduce((left, right) => {
      ???
    })
  }

  /**
   * Try to schedule an appointment in an appointment slot.
   * @param slot The slot to request.
   * @return A [[Try]] containing the result of scheduling; if successful, returns an [[Appointment]]; otherwise returns
   *         an [[Exception]] with the reason that the appointment cannot be scheduled.
   */
  def schedule(slot: Slot): Try[Appointment] = ???

  /**
   * Cancel an appointment completely.
   * @param appointment The appointment object to cancel.
   * @return If successful, returns a [[Unit]]. Otherwise, returns an [[Exception]] with the reason that the appointment
   *         cannot be cancelled.
   */
  def cancel(appointment: Appointment): Try[Unit] = ???

  /**
   * Cancel an appointment completely.
   *
   * @param appointment The UUID of the appointment to cancel.
   * @return If successful, returns a [[Unit]]. Otherwise, returns an [[Exception]] with the reason that the appointment
   *         cannot be cancelled.
   */
  def cancel(appointment: UUID): Boolean = ???

  /**
   * Cancel a guest's attendance of an appointment.
   *
   * @param appointment The appointment object to cancel attendance of.
   * @param guest The guest to cancel attendance for.
   * @return If successful, returns a [[Unit]]. Otherwise, returns an [[Exception]] with the reason that the appointment
   *         cannot be cancelled.
   */
  def cancel(appointment: Appointment, guest: Contact): Boolean = ???

  /**
   * Cancel a guest's attendance of an appointment.
   *
   * @param appointment The UUID of the appointment to cancel attendance of.
   * @param guest       The guest to cancel attendance for.
   * @return If successful, returns a [[Unit]]. Otherwise, returns an [[Exception]] with the reason that the appointment
   *         cannot be cancelled.
   */
  def cancel(appointment: UUID, guest: Contact): Boolean = ???
}
