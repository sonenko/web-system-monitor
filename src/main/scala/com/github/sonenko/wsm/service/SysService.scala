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

  def kill(processId: Int): Try[Unit] = Try {
    Seq("kill", processId.toString).lineStream
  }

  // USER, PID, %CPU, %MEM, VSZ, RSS, TT, STAT, STARTED, TIME, COMMAND
  private def seqToInfo(processData: List[String]): Option[ProcessInfoA] = processData match {
    case all @ user :: pid :: cpuStr :: memStr :: _ :: _ :: _ :: _ :: _ :: _ :: name :: xs =>
      Try(ProcessInfoA(name.split("/").last, pid.toInt, user, cpuStr.toDouble, memStr.toDouble, name + xs.mkString(" "))).toOption
    case _ => None
  }
}
