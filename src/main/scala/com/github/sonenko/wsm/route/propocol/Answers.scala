package com.github.sonenko.wsm.route.propocol

object Answers {
  case class ProcessInfoA(
    taskName: String,
    user: String,
    cpu: Double,
    memory: Double,
    description: String
  )
}