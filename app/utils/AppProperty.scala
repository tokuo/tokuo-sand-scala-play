package utils

import play.api.Configuration

import javax.inject.{Inject, Singleton}

@Singleton
class AppProperty @Inject()(conf: Configuration) {

  def getApiKey() = {
    conf.get[String]("api.key")
  }

  def getApiOrigin(): String = {
    conf.get[String]("api.origin")
  }

}
