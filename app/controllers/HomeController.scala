package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.data._
import play.api.libs.json.Json
import org.slf4j.LoggerFactory
import models.{Issue, IssueRepository}
import service.IssueService

import scala.concurrent.ExecutionContext.Implicits.global

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(
                                val cc: MessagesControllerComponents,
                                issueService: IssueService,
                                issueRepository: IssueRepository,
                              ) extends MessagesAbstractController(cc) {

  import PointForm._

  private val logger = LoggerFactory.getLogger(getClass)
  private val updatePointUrl = routes.HomeController.updatePoint()
  private val initUrl = routes.HomeController.init()



  def index = Action { implicit request =>
    Ok(views.html.index(initUrl))
  }

  def home = Action.async { implicit request =>
    issueService.getIssueListForHome().map { issueList =>
      Ok(views.html.home(issueList.toSeq, updatePointUrl, form))
    }
  }

  def init = Action { implicit request =>
    issueService.issueInitialize()
    Redirect(routes.HomeController.home())
  }

  def updatePoint = Action { implicit request =>
    val errorFunction = { formWithErrors: Form[Data] =>
      logger.error(formWithErrors.toString)
      BadRequest(views.html.home(Seq[Issue](), updatePointUrl, form))
    }

    val successFunction = { data: Data =>
      issueRepository.updatePoint(data.issueKey, data.point.toFloat)
      Redirect(routes.HomeController.home()).flashing()
    }

    val formValidationResult = form.bindFromRequest()
    formValidationResult.fold(errorFunction, successFunction)
  }


  //デバッグ用
  def check = Action.async { implicit request: MessagesRequest[AnyContent] =>
    issueRepository.findAll().map { issue =>
      Ok(Json.toJson(issue))
    }
  }
}
