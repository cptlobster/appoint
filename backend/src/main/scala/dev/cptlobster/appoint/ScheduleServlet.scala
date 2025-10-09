package dev.cptlobster.appoint

import dev.cptlobster.appoint.scheduler.AppointmentSchedule
import dev.cptlobster.appoint.scheduler.builder.ScheduleOverlay
import dev.cptlobster.appoint.scheduler.builder.overrides.CustomOverride
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.*
import org.scalatra.json.*
import org.scalatra.swagger.*

import java.util.UUID
import scala.util.{Failure, Success, Try}

class ScheduleServlet(implicit val swagger: Swagger) extends ScalatraServlet with JacksonJsonSupport with SwaggerSupport {
  protected implicit lazy val jsonFormats: Formats = DefaultFormats

  protected val applicationDescription = "The Appoint scheduling system API. Exposes operations to manage appointments and appointment schedules."

  before() {
    contentType = formats("json")
  }

  private val getSchedules = (
    apiOperation[List[AppointmentSchedule]]("getSchedules")
      summary "List all appointment schedules for a user"
    )

  // get all schedules for a user
  get("/", operation(getSchedules)) {
    "[]"
  }

  private val getScheduleInfo = (
    apiOperation[AppointmentSchedule]("getScheduleInfo")
      summary "Get information on the selected appointment schedule."
      parameter queryParam[UUID]("uuid").paramType(ParamType.Path).description("The UUID for the appointment schedule")
    )

  // get details for a schedule
  get("/:uuid", operation(getScheduleInfo)) {
    val uuidTry: Try[UUID] = Try(UUID.fromString(params("uuid")))
    uuidTry match {
      case Success(uuid) => s"{\"id\":\"${uuid.toString}\"}"
      case Failure(exception) => halt(400, "{\"error\":\"Failed to parse UUID\"")
    }
  }

  private val getScheduleOverlays = (
    apiOperation[List[ScheduleOverlay]]("getScheduleOverlays")
      summary "List all configured schedule overlays."
      parameter queryParam[UUID]("uuid").paramType(ParamType.Path).description("The UUID for the appointment schedule")
    )

  // get schedule overlays
  get("/:uuid/overlays", operation(getScheduleOverlays)) {
    "{\"implemented\":false}"
  }

  private val getScheduleOverrides = (
    apiOperation[List[CustomOverride]]("getScheduleOverlays")
      summary "List all configured custom overrides for a schedule."
      parameter queryParam[UUID]("uuid").paramType(ParamType.Path).description("The UUID for the appointment schedule")
    )

  // get schedule overrides
  get("/:uuid/overrides", operation(getScheduleOverrides)) {
    "{\"implemented\":false}"
  }

  private val createScheduleOverride = (
    apiOperation[Unit]("createScheduleOverride")
      summary "Create a new custom override on the appointment schedule."
      parameter queryParam[UUID]("uuid").paramType(ParamType.Path).description("The UUID for the appointment schedule")
    )

  // add a new schedule override
  post("/:uuid/overrides", operation(createScheduleOverride)) {
    "{\"implemented\":false}"
  }

  private val createSchedule = (
    apiOperation[AppointmentSchedule]("createSchedule")
      summary "Create a new appointment schedule."
    )

  // create a schedule
  post("/", operation(createSchedule)) {
    "{\"implemented\":false}"
  }

  private val modifySchedule = (
    apiOperation[AppointmentSchedule]("modifySchedule")
      summary "Modify an existing appointment schedule."
      parameter queryParam[UUID]("uuid").paramType(ParamType.Path).description("The UUID for the appointment schedule")
    )

  // modify an appointment schedule
  patch("/:uuid", operation(modifySchedule)) {
    "{\"implemented\":false}"
  }

  private val deleteSchedule = (
    apiOperation[Unit]("deleteSchedule")
      summary "Delete an existing appointment schedule."
      parameter queryParam[UUID]("uuid").paramType(ParamType.Path).description("The UUID for the appointment schedule")
    )

  // delete an appointment schedule
  delete("/:uuid", operation(deleteSchedule)) {
    "{\"implemented\":false}"
  }

}
