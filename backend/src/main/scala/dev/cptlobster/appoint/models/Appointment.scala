package dev.cptlobster.appoint.models

import java.time.Instant
import java.util.UUID

case class Appointment(id: UUID,
                       source: AppointmentSchedule,
                       start: Instant) {

}
