package dev.cptlobster.appoint.scheduler.builder.overlay

import dev.cptlobster.appoint.scheduler.builder.ScheduleOverlay
import dev.cptlobster.appoint.scheduler.{AppointmentSchedule, Slot}
import net.fortuna.ical4j.model.Calendar
import net.fortuna.ical4j.model.component.VEvent

import java.util.UUID
import scala.jdk.CollectionConverters.*

/**
 * An overlay obtained from an iCalendar source. If an event is present and matches the filter, any slots that overlap
 * this event will be removed from the schedule.
 * @param source The [[Calendar]] object generated from the iCalendar source.
 * @param invert Inverts the filter rule if enabled; i.e. if the filter is [[ICalEventFilter.Exists Exists]] and invert
 *               is [[true]], then any slots that do NOT overlap with an event will be removed.
 * @param filter Events will be used in this overlay if they match the filter.
 */
class ICalOverlay(id: UUID = UUID.randomUUID(),
                  source: Calendar,
                  invert: Boolean = false,
                  filter: ICalEventFilter = ICalEventFilter.Exists) extends ScheduleOverlay {
  override def applyTo(schedule: AppointmentSchedule, slots: List[Slot]): List[Slot] = {
    val events = source.getComponents[VEvent].asScala
    events.filter(filter.matches).foldLeft(slots)((slots, event) => {
      slots.filter(_.overlaps(event))
    })
  }
}
