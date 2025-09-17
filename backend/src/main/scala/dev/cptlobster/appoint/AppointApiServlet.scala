package dev.cptlobster.appoint

import org.json4s.{DefaultFormats, Formats}

import org.scalatra._
import org.scalatra.json._

class AppointApiServlet extends ScalatraServlet with JacksonJsonSupport {
  protected implicit lazy val jsonFormats: Formats = DefaultFormats

  before() {
    contentType = formats("json")
  }

  get("/") {
    "{\"hello\":\"world\"}"
  }

}
