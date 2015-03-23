package com.github.sonenko.wsm.route.propocol

object Answers {
  case class ProcessInfoA(
    taskName: String,
    pid: Int,
    user: String,
    cpu: Double,
    memory: Double,
    description: String
  )
}