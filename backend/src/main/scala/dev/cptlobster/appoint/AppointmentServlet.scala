package dev.cptlobster.appoint

import com.typesafe.scalalogging.LazyLogging
import dev.cptlobster.appoint.orm.Appointment
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.*
import org.scalatra.json.*
import org.scalatra.swagger.*

import java.util.UUID
import scala.util.{Failure, Success, Try}
import jakarta.servlet.ServletConfig

import scala.jdk.CollectionConverters.*

class AppointmentServlet(implicit val swagger: Swagger)
  extends ScalatraServlet
    with JacksonJsonSupport
    // with SwaggerSupport
    with LazyLogging {
  protected implicit lazy val jsonFormats: Formats = DefaultFormats

  protected val applicationDescription = "The Appoint scheduling system API. Exposes operations to manage appointments and appointment schedules."

  override def init(config: ServletConfig): Unit = {
    super.init(config)
  }

  def getUuid: UUID = {
    val uuidTry: Try[UUID] = Try(UUID.fromString(params("uuid")))
    uuidTry match {
      case Success(uuid) => uuid
      case Failure(exception) => halt(400, "{\"error\":\"Failed to parse UUID\"}")
    }
  }

  before() {
    contentType = formats("json")
  }

//  private val getAppointments = (
//    apiOperation[List[Appointment]]("getAppointments")
//      summary "List all appointments for a user"
//  )

  // get list of appointments for user
//  get("/", operation(getAppointments)) {
  get("/") {
    val appointments = List()
    appointments
  }

//  private val getAppointment = (
//    apiOperation[String]("getAppointment")
//      summary "Get information for a specific appointment"
//      parameter queryParam[UUID]("uuid").paramType(ParamType.Path).description("The UUID for the appointment")
//    )

  // get information for an appointment
//  get("/:uuid", operation(getAppointment)) {
  get("/:uuid") {
    val uuid = getUuid
    "{\"implemented\":false}"
  }

//  private val modifyAppointment = (
//    apiOperation[String]("modifyAppointment")
//      summary "Modify details of a scheduled appointment"
//      parameter queryParam[UUID]("uuid").paramType(ParamType.Path).description("The UUID for the appointment")
//    )


  // modify an appointment
//  patch("/:uuid", operation(modifyAppointment)) {
  patch("/:uuid") {
    val uuid = getUuid
    "{\"implemented\":false}"
  }

//  private val cancelAppointment = (
//    apiOperation[Unit]("cancelAppointment")
//      summary "Cancel an existing appointment. If you are an attendee, this just removes your attendance; if you are " +
//      "the organizer then it cancels for all attendees."
//      parameter queryParam[UUID]("uuid").paramType(ParamType.Path).description("The UUID for the appointment")
//    )

  // cancel an appointment
//  delete("/:uuid", operation(cancelAppointment)) {
  delete("/:uuid") {
    val uuid = getUuid
    "{\"implemented\":false}"
  }
}
