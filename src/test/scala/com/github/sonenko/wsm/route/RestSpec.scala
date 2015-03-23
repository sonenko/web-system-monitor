package com.github.sonenko.wsm.route

import akka.actor.ActorRefFactory
import com.github.sonenko.wsm.service.SysService
import org.specs2.mock.Mockito
import org.specs2.mutable.Specification
import spray.routing.{HttpService, _}
import spray.testkit.Specs2RouteTest

trait RestSpec extends Specification with Specs2RouteTest with Mockito {

  trait Scope extends org.specs2.specification.Scope {
    val sysServiceMock = mock[SysService]

    val testRoute = {
      val allRoutes = new AllRoutes {
        override implicit def actorRefFactory: ActorRefFactory = system
        override def sysService: SysService = sysServiceMock
      }
      allRoutes.allRoutes
    }
  }
}
