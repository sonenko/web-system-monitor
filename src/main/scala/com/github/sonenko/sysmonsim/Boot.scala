package com.github.sonenko.sysmonsim

import akka.actor._
import akka.actor.ActorRef
import scala.language.postfixOps
import spray.servlet.WebBoot

import com.github.sonenko.sysmonsim.route.AllRoutesImpl


class Boot extends WebBoot {
  Config.init()
  val system: ActorSystem = ActorSystem("system")
  val serviceActor: ActorRef = system.actorOf(Props(classOf[AllRoutesImpl]),"router-actor")
}