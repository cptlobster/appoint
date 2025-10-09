package dev.cptlobster.appoint

import dev.cptlobster.appoint.scheduler.Appointment
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.*
import org.scalatra.json.*
import org.scalatra.swagger.*

import java.util.UUID
import scala.util.{Failure, Success, Try}

class AppointmentServlet(implicit val swagger: Swagger) extends ScalatraServlet with JacksonJsonSupport with SwaggerSupport {
  protected implicit lazy val jsonFormats: Formats = DefaultFormats

  protected val applicationDescription = "The Appoint scheduling system API. Exposes operations to manage appointments and appointment schedules."

  before() {
    contentType = formats("json")
  }

  private val getAppointments = (
    apiOperation[List[Appointment]]("getAppointments")
      summary "List all appointments for a user"
  )

  // get list of appointments for user
  get("/", operation(getAppointments)) {
    "[]"
  }

  private val getAppointment = (
    apiOperation[Appointment]("getAppointment")
      summary "Get information for a specific appointment"
      parameter queryParam[UUID]("uuid").paramType(ParamType.Path).description("The UUID for the appointment")
    )

  // get information for an appointment
  get("/:uuid", operation(getAppointment)) {
    val uuidTry: Try[UUID] = Try(UUID.fromString(params("uuid")))
    uuidTry match {
      case Success(uuid) => s"{\"id\":\"${uuid.toString}\"}"
      case Failure(exception) => halt(400, "{\"error\":\"Failed to parse UUID\"")
    }
  }

  private val modifyAppointment = (
    apiOperation[Appointment]("modifyAppointment")
      summary "Modify details of a scheduled appointment"
      parameter queryParam[UUID]("uuid").paramType(ParamType.Path).description("The UUID for the appointment")
    )


  // modify an appointment
  patch("/:uuid", operation(modifyAppointment)) {
    "{\"implemented\":false}"
  }

  private val cancelAppointment = (
    apiOperation[Unit]("cancelAppointment")
      summary "Cancel an existing appointment. If you are an attendee, this just removes your attendance; if you are " +
      "the organizer then it cancels for all attendees."
      parameter queryParam[UUID]("uuid").paramType(ParamType.Path).description("The UUID for the appointment")
    )

  // cancel an appointment
  delete("/:uuid", operation(cancelAppointment)) {
    "{\"implemented\":false}"
  }
}
