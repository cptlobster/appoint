package dev.cptlobster.appoint

import org.json4s.{DefaultFormats, Formats}
import org.scalatra.*
import org.scalatra.json.*

import java.util.UUID
import scala.util.{Try, Success, Failure}

class AppointApiServlet extends ScalatraServlet with JacksonJsonSupport {
  protected implicit lazy val jsonFormats: Formats = DefaultFormats

  before() {
    contentType = formats("json")
  }

  // get configuration options for the application
  get("/") {
    "{\"hello\":\"world\"}"
  }

  // get list of appointments for user
  get("/appointments") {
    "[]"
  }

  // get information for an appointment
  get("/appointments/:uuid") {
    val uuidTry: Try[UUID] = Try(UUID.fromString(params("uuid")))
    uuidTry match {
      case Success(uuid) => s"{\"id\":\"${uuid.toString}\"}"
      case Failure(exception) => halt(400, "{\"error\":\"Failed to parse UUID\"")
    }
  }

  // modify an appointment
  patch("/appointments/:uuid") {
    "{\"implemented\":false}"
  }

  // cancel an appointment
  delete("/appointments/:uuid") {
    "{\"implemented\":false}"
  }

  // get all schedules for a user
  get("/schedules") {
    "[]"
  }

  // get details for a schedule
  get("/schedules/:uuid") {
    val uuidTry: Try[UUID] = Try(UUID.fromString(params("uuid")))
    uuidTry match {
      case Success(uuid) => s"{\"id\":\"${uuid.toString}\"}"
      case Failure(exception) => halt(400, "{\"error\":\"Failed to parse UUID\"")
    }
  }
  
  // get schedule overlays
  get("/schedules/:uuid/overlays") {
    "{\"implemented\":false}"
  }
  
  // get schedule overrides
  get("/schedules/:uuid/overrides") {
    "{\"implemented\":false}"
  }
  
  // add a new schedule override
  post("/schedules/:uuid/overrides") {
    "{\"implemented\":false}"
  }

  // create a schedule
  post("/schedules") {
    "{\"implemented\":false}"
  }
  
  // modify an appointment schedule
  patch("/schedules/:uuid") {
    "{\"implemented\":false}"
  }

  // delete an appointment schedule
  delete("/schedules/:uuid") {
    "{\"implemented\":false}"
  }

}
