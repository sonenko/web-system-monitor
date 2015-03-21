package com.github.sonenko.wsm.route.propocol

import spray.json._

import com.github.sonenko.wsm.route.propocol.Answers._


object JsonProtocol extends DefaultJsonProtocol{
  implicit val ProcessInfoAF = jsonFormat5(ProcessInfoA)
}
