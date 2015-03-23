import sbt._, Keys._
import com.earldouglas.xsbtwebplugin._, WebPlugin._, PluginKeys._

import Dependencies._

object Build extends Build {

  val publishSettings = Seq(
    publishMavenStyle := true,
    publishArtifact in (Compile, packageDoc) := false,
    publishArtifact in (Compile, packageSrc) := true,
    credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")
  )

  val commonSettings = Seq(
    port in container.Configuration := sys.props.getOrElse("s.port", sys.env.getOrElse("shop_port", "8080")).toInt,
    organization := "com.github.sonenko",
    description := "",
    updateOptions := updateOptions.value withCachedResolution true,
    scalaVersion := "2.11.4",
    cancelable in Global := true,
    scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8", "-feature", "-Xlog-reflective-calls", "-Xfuture", "-Xlint"),
    testOptions in Test := Seq(Tests.Filter(x => x.endsWith("Test"))),
    parallelExecution in Test := false
  )

  lazy val sysmonsim = (project in file("."))
    .settings(libraryDependencies ++= Seq(
      log.logback, log.scalaloggingSlf4j, log.jclOverSlf4j, log.julToSlf4j, log.log4jOverSlf4j, log.slf4jApi,
      joda.time, joda.convert, joda.money,
      akka.actor, spray.servlet, spray.json, spray.routing, spray.httpx, spray.caching, // spray.websocket,
      webContainer.servlet, webContainer.tomcatCore, webContainer.jettyWebApp,
      scalaz,
      spray.testkit, akka.testkit, tests.specs2, tests.mockito
    ))
    .settings(webSettings: _*)
    .settings(commonSettings:_*)
}