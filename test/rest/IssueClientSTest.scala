package rest

import org.scalatest.Matchers.be
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.test.Injecting
import utils.AppProperty

class IssueClientSTest extends PlaySpec with GuiceOneAppPerTest with Injecting {

  "IssueClientテスト（通信は行わない）" should {
    "propertyチェック" in {
      //given
      val appProperty = inject[AppProperty]
      //when
      val apiOrigin = appProperty.getApiOrigin()
      //then
      apiOrigin must not be null
    }
  }

}
