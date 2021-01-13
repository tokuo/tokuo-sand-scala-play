package controllers

import models.IssueRepository
import org.scalatestplus.mockito.MockitoSugar.mock
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.Play.materializer
import play.api.http.Status.OK
import play.api.test.Helpers.{GET, contentAsString, contentType, defaultAwaitTimeout, status, stubMessagesControllerComponents}
import play.api.test.{FakeRequest, Injecting}
import play.api.test.CSRFTokenHelper._
import service.IssueService



class HomeControllerSTest extends PlaySpec with GuiceOneAppPerTest with Injecting {

  "HomeController GET" should {

    "render the index page from a new instance of controller" in {
      // given
      val issueServicMock = mock[IssueService]
      val issueRepository = mock[IssueRepository]
      val controller = new HomeController(stubMessagesControllerComponents() , issueServicMock, issueRepository)
      // when
      val home = controller.index().apply(FakeRequest(GET, "/").withCSRFToken)
      // then
      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("ログイン")
    }
  }
}
