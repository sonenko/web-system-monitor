package project

import sbt._
import sbt.Keys._

object ProjectBuild extends Build {
  lazy val projectBuild = project.in(file(".")).settings(List(
    addSbtPlugin("com.github.gseitz" % "sbt-release" % "0.8.3"),
    addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.6.0"),
    addSbtPlugin("com.earldouglas" % "xsbt-web-plugin" % "0.9.0"),
    credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")
  ): _*)
}
