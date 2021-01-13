package service

import models.IssueRepository
import play.api.libs.json.JsValue.jsValueToJsLookup
import play.api.libs.json.Json
import rest.IssueClient
import com.google.gson._
import org.slf4j.LoggerFactory

import javax.inject.{Inject, Singleton}

@Singleton
class IssueService @Inject()(
                              issueClient: IssueClient,
                              issueRepository: IssueRepository
                            ) {

  private val logger = LoggerFactory.getLogger(getClass)



  def getIssueListForHome() = {
    issueRepository.findAll()
  }


  //TODO ログインしつつ、差分のみ取得するようにする
  def issueInitialize() = {
    val taskJsArray = new JsonParser().parse(issueClient.getTaskList).getAsJsonArray
    for( i <- 1 to taskJsArray.size()) {
      val taskJsVal = Json.parse(taskJsArray.get(i-1).toString)
      val keyId = (taskJsVal \ "keyId").as[Int]
      val issuekey = (taskJsVal \ "issueKey").as[String]
      val summary = (taskJsVal \ "summary").as[String]
      issueRepository.add(keyId, issuekey, summary, 0)
    }
  }

    //TODO 作成途中
  def updateIssueFromBacklog(): Unit = {
    val maxKeyIdTask = Json.parse(issueClient.getMaxKeyIdTask) \ 0
    val realMaxKeyId = (maxKeyIdTask \ "keyId").get.toString().toInt

    val dbStoredMaxKeyId = issueRepository.findMaxKeyId()
    //
  }
}
