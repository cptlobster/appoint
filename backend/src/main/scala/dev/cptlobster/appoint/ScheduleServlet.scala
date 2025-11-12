package dev.cptlobster.appoint

import com.typesafe.scalalogging.LazyLogging
import dev.cptlobster.appoint.orm.{AppointmentSchedule, Overlay}
import jakarta.persistence.NoResultException
import jakarta.servlet.ServletConfig
import org.json4s.*
import org.scalatra.*
import org.scalatra.json.*
import org.scalatra.swagger.*

import scala.jdk.CollectionConverters.*
import java.util.UUID
import scala.util.{Failure, Success, Try}

class ScheduleServlet(implicit val swagger: Swagger)
  extends ScalatraServlet
    with JacksonJsonSupport
    // with SwaggerSupport
    with HibernateConnection
    with LazyLogging {
  protected implicit lazy val jsonFormats: Formats = DefaultFormats

  protected val applicationDescription = "The Appoint scheduling system API. Exposes operations to manage appointments and appointment schedules."

  override def init(config: ServletConfig): Unit = {
    super.init(config)
    sessionFactory.getSchemaManager.create(true)
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

//  private val getSchedules = (
//    apiOperation[List[String]]("getSchedules")
//      summary "List all appointment schedules for a user"
//    )

  // get all schedules for a user
//   get("/", operation(getSchedules)) {
  get("/") {
    val schedules = sessionFactory.fromTransaction((session) => {
      val query = "from AppointmentSchedule"
      session.createSelectionQuery(query, classOf[AppointmentSchedule])
        .getResultList
        .asScala
    })
    schedules
  }

//  private val getScheduleInfo = (
//    apiOperation[String]("getScheduleInfo")
//      summary "Get information on the selected appointment schedule."
//      parameter queryParam[UUID]("uuid").paramType(ParamType.Path).description("The UUID for the appointment schedule")
//    )

  // get details for a schedule
// get("/:uuid", operation(getScheduleInfo)) {
  get("/:uuid") {
    val uuid = getUuid
    val schedule = Try(sessionFactory.fromTransaction(session => {
      val query = "from AppointmentSchedule where id = :uuid"
      session.createSelectionQuery(query, classOf[AppointmentSchedule])
        .setParameter("uuid", uuid)
        .getSingleResult
    })) match {
      case Success(a) => a
      case Failure(e: NoResultException) => halt(404, s"{\"error\":\"Did not find schedule with ID $uuid.\"}")
      case scala.util.Failure(e) =>
        logger.warn("Database query failed.", e)
        halt(500, "{\"error\":\"Internal database error.\"}")
    }
    schedule
  }

//  private val getScheduleOverlays = (
//    apiOperation[List[String]]("getScheduleOverlays")
//      summary "List all configured schedule overlays."
//      parameter queryParam[UUID]("uuid").paramType(ParamType.Path).description("The UUID for the appointment schedule")
//    )

  // get schedule overlays
// get("/:uuid/overlays", operation(getScheduleOverlays)) {
  get("/:uuid/overlays") {
    val uuid = getUuid
    val overlays = Try(sessionFactory.fromTransaction(session => {
      val query = "from Overlay where schedule_id = :uuid"
      session.createSelectionQuery(query, classOf[Overlay])
        .setParameter("uuid", uuid)
        .getResultList
        .asScala
    })) match {
      case Success(a) => a
      case Failure(e: NoResultException) => halt(404, s"{\"error\":\"Did not find overlays for schedule $uuid.\"}")
      case scala.util.Failure(e) =>
        logger.warn("Database query failed.", e)
        halt(500, "{\"error\":\"Internal database error.\"}")
    }
    overlays
  }

//  private val getScheduleOverrides = (
//    apiOperation[List[String]]("getScheduleOverlays")
//      summary "List all configured custom overrides for a schedule."
//      parameter queryParam[UUID]("uuid").paramType(ParamType.Path).description("The UUID for the appointment schedule")
//    )
//
//  // get schedule overrides
//  get("/:uuid/overrides", operation(getScheduleOverrides)) {
//    val uuid = getUuid
//    "{\"implemented\":false}"
//  }
//
//  private val createScheduleOverride = (
//    apiOperation[Unit]("createScheduleOverride")
//      summary "Create a new custom override on the appointment schedule."
//      parameter queryParam[UUID]("uuid").paramType(ParamType.Path).description("The UUID for the appointment schedule")
//    )
//
//  // add a new schedule override
//  post("/:uuid/overrides", operation(createScheduleOverride)) {
//    val uuid = getUuid
//    "{\"implemented\":false}"
//  }

//  private val createSchedule = (
//    apiOperation[String]("createSchedule")
//      summary "Create a new appointment schedule."
//    )

  // create a schedule
//  post("/", operation(createSchedule)) {
  post("/") {
    val newSchedule: AppointmentSchedule = parsedBody.extract[AppointmentSchedule]
    val result = Try(sessionFactory.fromTransaction(session => {
      val user = newSchedule.host
      session.persist(newSchedule)
    }))
    result match {
      case Success(v) => v
      case Failure(e) =>
        logger.error("Failed to commit to database", e)
        halt(500, "{\"error\":\"Internal server error.\"}")
    }
  }

//  private val modifySchedule = (
//    apiOperation[String]("modifySchedule")
//      summary "Modify an existing appointment schedule."
//      parameter queryParam[UUID]("uuid").paramType(ParamType.Path).description("The UUID for the appointment schedule")
//    )

  // modify an appointment schedule
//  patch("/:uuid", operation(modifySchedule)) {
  patch("/:uuid") {
    val uuid = getUuid
    "{\"implemented\":false}"
  }

//  private val deleteSchedule = (
//    apiOperation[Unit]("deleteSchedule")
//      summary "Delete an existing appointment schedule."
//      parameter queryParam[UUID]("uuid").paramType(ParamType.Path).description("The UUID for the appointment schedule")
//    )

  // delete an appointment schedule
// delete(":/uuid", operation(deleteSchedule)) {
  delete("/:uuid") {
    val uuid = getUuid
    "{\"implemented\":false}"
  }
}
