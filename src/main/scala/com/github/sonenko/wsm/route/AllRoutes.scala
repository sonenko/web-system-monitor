package com.github.sonenko.wsm.route

import akka.actor.ActorRefFactory
import com.github.sonenko.wsm.service.SysService
import spray.routing.{Route, HttpServiceActor}

trait AllRoutes
  extends ResourcesRoute
  with SysRoute
{
  implicit def actorRefFactory: ActorRefFactory

  override def sysService: SysService

  val allRoutes =
    resourcesRoute ~
    sysRoute
}

class AllRoutesImpl extends HttpServiceActor with AllRoutes {
  val sysService = new SysService
  val receive: Receive = runRoute(allRoutes)
}
