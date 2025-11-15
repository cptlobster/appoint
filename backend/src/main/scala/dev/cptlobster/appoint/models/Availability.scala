package dev.cptlobster.appoint.models

import java.time.{DayOfWeek, LocalTime}
import java.util.UUID

case class Availability(id: UUID,
                        weekday: DayOfWeek,
                        startTime: LocalTime,
                        endTime: LocalTime) {

}
