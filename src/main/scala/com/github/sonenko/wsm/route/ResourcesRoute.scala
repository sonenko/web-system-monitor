package com.github.sonenko.wsm
package route

import spray.routing.{HttpService, Route}
import spray.routing.directives.CachingDirectives._


trait ResourcesRoute extends HttpService {

  private val cacheable = Config.resources.cacheResources
  private val filesDir = if (cacheable) Config.resources.pathRelativeToWebApp else s"${Config.resources.pathRealToWebApp}"

  val resourcesRoute: Route = cacheIfNeeded {
    pathEndOrSingleSlash {fromFile("index.html")} ~
    serveFiles("admin", "register") ~
    pathPrefix("resources") {fromDirectory("resources")} ~
    pathPrefix("src") {fromDirectory("src")}
  }

  private def serveFiles(ways: String *): Route =
    ways.map{ way =>
      path(way)(fromFile(s"$way.html"))
    }.reduce (_ ~ _)

  private def fromFile(way: String): Route =
    if (cacheable) getFromResource(s"$filesDir/$way")
    else getFromFile(s"$filesDir/$way")

  private def fromDirectory(way: String): Route =
    if (cacheable) getFromResourceDirectory(s"$filesDir/$way")
    else getFromBrowseableDirectory(s"$filesDir/$way")

  private def cacheIfNeeded(next: Route): Route =
    if (cacheable) cache(routeCache(maxCapacity = 3000, initialCapacity = 1000))(next) else next
}












