package com.github.sonenko.sysmonsim.route.propocol

object Answers {
  case class ProcessInfoA(
    taskName: String,
    user: String,
    cpu: Double,
    memory: Double,
    description: String
  )
}