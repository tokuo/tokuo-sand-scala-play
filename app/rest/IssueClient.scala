package rest

import javax.inject._
import play.api.libs.ws._
import utils.AppProperty

import scala.concurrent.duration.Duration
import scala.concurrent.Await

@Singleton
class IssueClient @Inject()(ws: WSClient, property: AppProperty) {

  private val API_PATH = "/api/v2/issues"
  private val API_KEY = "?apiKey=" + property.getApiKey()
  private val API_URL = property.getApiOrigin() + API_PATH + API_KEY


  def getTaskList: String = {
    val API_QUERY = "&sort=customField_keyId_max"
    val FIN_API_URL = API_URL + API_QUERY

    val req: WSRequest = ws.url(FIN_API_URL)
    val result = Await.result(req.get(), Duration.Inf)
    result.body
  }

  def getMaxKeyIdTask: String = {
    val API_QUERY = "&sort=customField_keyId_max" + "&count=1"
    val FIN_API_URL = API_URL + API_QUERY

    val req: WSRequest = ws.url(FIN_API_URL)
    val result = Await.result(req.get(), Duration.Inf)
    result.body
  }

}
