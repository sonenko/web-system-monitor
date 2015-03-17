package com.github.sonenko.sysmonsim

import com.typesafe.config.ConfigFactory

object Config {
  val reader = ConfigFactory.load()

  object resources {
    def init() =()

    val pathRealToResources = reader.getString("sysmonsim.resource.path-real-to-resources")
    val pathRelativeToWebApp = reader.getString("sysmonsim.resource.path-relative-to-web-app")
    val pathRealToWebApp = s"$pathRealToResources/$pathRelativeToWebApp"
    val cacheResources = reader.getBoolean("sysmonsim.resource.cache-resources")
  }

  // to crash just after if config is broken
  def init(): Unit = {
    resources.init()
  }
}