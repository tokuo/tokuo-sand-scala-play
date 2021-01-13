package models

import play.api.libs.json._

case class Issue(id: Int, idKey: Int, issueKey: String, summary: String, point: Float)

object Issue {
  implicit val issueFormat = Json.format[Issue]
}
