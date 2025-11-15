package dev.cptlobster.appoint.models

import java.util.UUID
import java.time.{Duration, ZoneId}

case class AppointmentSchedule(id: UUID,
                               timezone: ZoneId,
                               length: Duration) {

}
