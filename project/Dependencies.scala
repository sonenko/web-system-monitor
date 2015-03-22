import sbt._

object Dependencies {

  object log {
    val logback = "ch.qos.logback" % "logback-classic" % "1.1.2"
    val scalaloggingSlf4j = "com.typesafe.scala-logging" %% "scala-logging-slf4j" % "2.1.2"

    private val slf4jVersion = "1.7.7"
    val jclOverSlf4j = "org.slf4j" % "jcl-over-slf4j" % slf4jVersion
    val julToSlf4j = "org.slf4j" % "jul-to-slf4j" % slf4jVersion
    val log4jOverSlf4j = "org.slf4j" % "log4j-over-slf4j" % slf4jVersion
    val slf4jApi = "org.slf4j" % "slf4j-api" % slf4jVersion
  }

  object akka {
    val version = "2.3.6"
    def apply(x: String): ModuleID = "com.typesafe.akka" %% s"akka-$x" % version

    val actor = apply("actor")
    val testkit = apply("testkit") % "test"
  }

  object spray {
    val version = "1.3.1"
    def apply(x: String): ModuleID = "io.spray" %% s"spray-$x" % version
    val servlet = apply("servlet")
    val routing = apply("routing-shapeless2")
    val httpx = apply("httpx")
    val caching = "io.spray" % "spray-caching_2.11" % "1.3.2"
    val testkit = apply("testkit") % "test"
    val json = "io.spray" %% "spray-json" % "1.2.6"
    val websocket = "com.wandoulabs.akka" %% "spray-websocket" % "0.1.4"
  }

  object joda {
    val time = "joda-time" % "joda-time" % "2.6"
    val convert = "org.joda" % "joda-convert" % "1.7"
    val money = "org.joda" % "joda-money" % "0.10.0"
  }

  object webContainer {
    val servlet = "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided"
    val tomcatCore = "org.apache.tomcat.embed" % "tomcat-embed-core" % "7.0.57" % "container"
    val jettyWebApp = "org.eclipse.jetty" % "jetty-webapp" % "8.1.16.v20140903" % "container"
  }

  object tests {
    def lib(name: String) = "org.specs2" %% s"specs2-$name" % "2.4.15" % "test"
    val mockito = lib("mock")
    val specs2 = lib("junit")
  }

  val scalaz = "org.scalaz" %% "scalaz-core" % "7.1.0"
  val bcrypt = "com.github.t3hnar" %% "scala-bcrypt" % "2.4"
}
