package com.github.sonenko.sysmonsim.route.propocol

import com.github.sonenko.sysmonsim.route.propocol.Answers._
import spray.json._

object JsonProtocol extends DefaultJsonProtocol{
  implicit val ProcessInfoAF = jsonFormat5(ProcessInfoA)
}
