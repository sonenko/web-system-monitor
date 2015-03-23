package com.github.sonenko.wsm.route

import spray.json._
import spray.http.StatusCodes
import spray.httpx.SprayJsonSupport._

import com.github.sonenko.wsm.route.propocol.JsonProtocol._
import com.github.sonenko.wsm.route.propocol.Answers._


class SysRouteTest extends RestSpec {

  "GET /api/info" should {
    "respond with status code 200 and contain correct JSON" in new Scope {
      val answer = ProcessInfoA(
        taskName = "taskName",
        user = "user",
        cpu = 0.1,
        memory = 0.1,
        description = "desc"
      )

      val resJSON =
        s"""
           |[{
           |  "taskName": "${answer.taskName}",
           |  "user": "${answer.user}",
           |  "cpu": ${answer.cpu},
           |  "memory": ${answer.memory},
           |  "description": "${answer.description}"
           |}]
         """.stripMargin.parseJson

      sysServiceMock.getInfo returns List(answer)

      Get(s"/api/info") ~> testRoute ~> check {
        status mustEqual StatusCodes.OK
        responseAs[JsArray] mustEqual resJSON
      }
    }
  }
}
