package com.github.sonenko.sysmonsim
package route

import spray.httpx.SprayJsonSupport._
import spray.routing.{HttpService, Route}
import com.github.sonenko.sysmonsim.service.SysService
import com.github.sonenko.sysmonsim.route.propocol.JsonProtocol._

trait SysRoute extends HttpService {

  def sysService: SysService

  val sysRoute: Route =
    (path("api" / "info") & get){
      complete {sysService.getInfo}
    } ~
    (path("api" / "userinfo") & get){
      complete {sysService.getInfoForCurrentUser}
    }
}
