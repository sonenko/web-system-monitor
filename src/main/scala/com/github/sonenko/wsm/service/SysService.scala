package com.github.sonenko.wsm.service

import scala.sys.process._
import scala.util.Try
import com.github.sonenko.wsm.route.propocol.Answers.ProcessInfoA


class SysService {

  def getInfo: List[ProcessInfoA] =
    Seq("ps", "aux").lineStream.toList.flatMap { str =>
      val processUntyped: List[String] = str.replaceAll("\\s+", " ").trim().split(' ').toList
      seqToInfo(processUntyped)
    }

  // USER, PID, %CPU, %MEM, VSZ, RSS, TT, STAT, STARTED, TIME, COMMAND
  private def seqToInfo(processData: List[String]): Option[ProcessInfoA] = processData match {
    case all @ user :: pid :: cpuStr :: memStr :: _ :: _ :: _ :: _ :: _ :: _ :: name :: xs =>
      Try(ProcessInfoA(name, user, cpuStr.toDouble, memStr.toDouble, name + xs.mkString(" "))).toOption
    case _ => None
  }

}
