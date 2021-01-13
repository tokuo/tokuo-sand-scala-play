package rest

import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.libs.json.Json
import play.api.test.Injecting

class IssueClientMTest extends PlaySpec with GuiceOneAppPerTest with Injecting {

  // sbt run で無意識下で実行されないためにコメントアウトしている
//  "IssueClientテスト（通信を伴う）" should {
//    "getMaxKeyIdTaskテスト" in {
//      //given
//      val issueClient = inject[IssueClient]
//      //when
//      val maxKeyIdTask = Json.parse(issueClient.getMaxKeyIdTask) \ 0
//      //then
//      //TODO ハードコーディングされている
//      (maxKeyIdTask \ "issueKey").as[String] must include ("TOKUO")
//    }
//  }
}
