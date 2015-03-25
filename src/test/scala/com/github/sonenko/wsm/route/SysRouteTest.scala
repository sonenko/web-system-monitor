package com.github.sonenko.wsm.route

import spray.json._
import spray.http.StatusCodes
import spray.httpx.SprayJsonSupport._
import scala.util.{Success, Failure}

import com.github.sonenko.wsm.route.propocol.JsonProtocol._
import com.github.sonenko.wsm.route.propocol.Answers._


class SysRouteTest extends RestSpec {

  "GET /api/info" should {
    "respond with status code 200 and contain correct JSON" in new Scope {
      val answer = ProcessInfoA(
        taskName = "taskName",
        pid = 1,
        user = "user",
        cpu = 0.1,
        memory = 0.1,
        description = "desc"
      )

      val resJSON =
        s"""
           |[{
           |  "taskName": "${answer.taskName}",
           |  "pid": 1,
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

  "POST /api/kill/{processId}" should {
    "respond with status code 201" in new Scope {
      sysServiceMock.kill(1) returns Success(())
      Post("/api/kill/1") ~> testRoute ~> check {
        status mustEqual StatusCodes.Accepted
      }
    }
    "respond with status code 500 if service return failure" in new Scope {
      sysServiceMock.kill(1) returns Failure(new Exception(""))
      Post("/api/kill/1") ~> testRoute ~> check {
        status mustEqual StatusCodes.InternalServerError
      }
    }
  }
}
