ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.13.8"

val http4sVersion = "0.23.7"
val circeVersion = "0.14.1"
val scalatestVersion = "3.2.10"
val jsoupVersion = "1.14.3"

ThisBuild / libraryDependencies ++= Seq(
  "org.http4s" %% "http4s-core" % http4sVersion,
  "org.http4s" %% "http4s-dsl" % http4sVersion,
  "org.http4s" %% "http4s-blaze-server" % http4sVersion,
  "org.http4s" %% "http4s-blaze-client" % http4sVersion,
  "org.http4s" %% "http4s-circe" % http4sVersion,
  "io.circe" %% "circe-generic" % circeVersion,
  "io.circe" %% "circe-parser" % circeVersion,
  "org.jsoup" % "jsoup" % jsoupVersion,
  "org.scalatest" %% "scalatest" % scalatestVersion % "test"
)

lazy val root = (project in file("."))
  .settings(
    name := "2GisTest"
  )
