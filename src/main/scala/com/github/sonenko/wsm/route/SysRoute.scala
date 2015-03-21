package com.github.sonenko.wsm
package route

import spray.httpx.SprayJsonSupport._
import spray.routing.{HttpService, Route}

import com.github.sonenko.wsm.service.SysService
import com.github.sonenko.wsm.route.propocol.JsonProtocol._


trait SysRoute extends HttpService {

  def sysService: SysService

  val sysRoute: Route =
    (path("api" / "info") & get){
      complete {sysService.getInfo}
    }
}
