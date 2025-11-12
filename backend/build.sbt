val ScalatraVersion = "3.1.2"
val HibernateVersion = "7.1.7.Final"

ThisBuild / scalaVersion := "3.3.4"
ThisBuild / organization := "dev.cptlobster"

lazy val appoint_backend = (project in file("."))
  .settings(
    name := "appoint-backend",
    version := "0.1.0-SNAPSHOT",
    libraryDependencies ++= Seq(
      "org.scalatra" %% "scalatra-jakarta" % ScalatraVersion,
      "org.scalatra" %% "scalatra-json-jakarta" % ScalatraVersion,
      "org.scalatra" %% "scalatra-swagger-jakarta" % ScalatraVersion,
      "org.scalatra" %% "scalatra-scalatest-jakarta" % ScalatraVersion % "test",
      "org.json4s"   %% "json4s-jackson" % "4.0.7",
      "com.fasterxml.jackson.datatype" % "jackson-datatype-jsr310" % "2.20.1",
      "org.mnode.ical4j" % "ical4j" % "4.2.0",
      "ch.qos.logback" % "logback-classic" % "1.5.20" % "runtime",
      "jakarta.servlet" % "jakarta.servlet-api" % "6.0.0" % "provided",
      "org.hibernate.orm" % "hibernate-core" % HibernateVersion,
      "com.h2database" % "h2" % "2.4.240"
    ),
  )

enablePlugins(SbtWar)

Test / fork := true
