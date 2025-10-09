package dev.cptlobster.appoint

import org.scalatra.ScalatraServlet
import org.scalatra.swagger.*

class ResourcesApp(implicit val swagger: Swagger) extends ScalatraServlet with JacksonSwaggerBase

object AppointApiInfo extends ApiInfo(
  "Appoint Backend API",
  "Backend API for interacting with Appoint scheduling system.",
  "https://appoint.cptlobster.dev",
  ContactInfo(
    "Dustin Thomas",
    "https://cptlobster.dev",
    "io@cptlobster.dev"
  ),
  LicenseInfo(
    "GPLv3",
    "https://www.gnu.org/licenses/gpl-3.0.en.html"
  )
)

class AppointSwagger extends Swagger(Swagger.SpecVersion, "0.0.1", AppointApiInfo)