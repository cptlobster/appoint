package dev.cptlobster.appoint.scheduler

import java.time.{Duration, Instant}
import dev.cptlobster.appoint.util.*
import net.fortuna.ical4j.model.component.VEvent

case class Slot(start: Instant,
                length: Duration,
                hosts: List[Host]) {
  val end = start.plus(length)
  def overlaps(oStart: Instant, oEnd: Instant): Boolean = {
    start.between(oStart, oEnd) || end.between(oStart, oEnd)
  }
  def overlaps(oStart: Instant, oLength: Duration): Boolean = overlaps(oStart, oStart + oLength)
  def overlaps(event: VEvent): Boolean = {
    val oStart = event.getDateTimeStart.as[Instant]
    val oEnd = event.getDateTimeEnd.as[Instant]
    overlaps(oStart, oEnd)
  }
}
