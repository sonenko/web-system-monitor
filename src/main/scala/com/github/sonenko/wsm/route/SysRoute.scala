package com.github.sonenko.wsm
package route

import spray.http.StatusCodes
import spray.httpx.SprayJsonSupport._
import spray.routing.{HttpService, Route}

import com.github.sonenko.wsm.service.SysService
import com.github.sonenko.wsm.route.propocol.JsonProtocol._


trait SysRoute extends HttpService {

  def sysService: SysService

  val sysRoute: Route = pathPrefix("api") {
    path("info") {
      get {
        complete {
          sysService.getInfo
        }
      }
    } ~
    path("kill" / IntNumber) { processId =>
      complete {
        sysService.kill(processId).map{ _ =>
          StatusCodes.Accepted
        }
      }
    }
  }
}
