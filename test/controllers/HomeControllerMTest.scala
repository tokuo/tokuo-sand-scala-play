package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.Play.materializer
import play.api.test._
import play.api.test.Helpers._
import play.api.test.CSRFTokenHelper._

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 *
 * For more information, see https://www.playframework.com/documentation/latest/ScalaTestingWithScalaTest
 */
class HomeControllerMTest extends PlaySpec with GuiceOneAppPerTest with Injecting {

  "HomeController GET" should {

    "render the index page from the application" in {
      //given
      val controller = inject[HomeController]
      //when
      val home = controller.index().apply(FakeRequest(GET, "/").withCSRFToken)
      //then
      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("ログイン")
    }

    "render the index page from the router" in {
      //given
      val request = FakeRequest(GET, "/")
      //when
      val home = route(app, request).get
      //then
      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("ログイン")
    }
  }
}
