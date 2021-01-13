package models

import org.slf4j.LoggerFactory

import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class IssueRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {

  private val dbConfig = dbConfigProvider.get[JdbcProfile]
  import dbConfig.{db, profile}
  import profile.api._

  private val logger = LoggerFactory.getLogger(getClass)
  private val issue = TableQuery[IssueTable]


  def findAll(): Future[Seq[Issue]] = db.run {
    issue.result
  }

  // そもそもissueが存在しなければ「0」を返却
  def findMaxKeyId(): Int = {
    var id: Int = 0
    db.run {
      issue.map(_.id).max.result
    } onComplete {
      case optionData => optionData.get match {
        case Some(maxKeyId) => id = maxKeyId
        case None => logger.error("課題が一つも保存されておりません")
      }
    }
    id
  }

  def updatePoint(issueKey: String, point: Float) = db.run {
    issue.filter(_.issueKey === issueKey).map(_.point).update(point)
  }

  def add(idKey: Int, issueKey: String, summary: String, point: Float = 0): Future[Issue] = db.run {
    (
      issue.map(p => (p.idKey, p.issueKey, p.summary, p.point))
      returning issue.map(_.id)
      into ((pa, id) => Issue(id, pa._1, pa._2, pa._3, pa._4))
    ) += (idKey, issueKey, summary, point)
  }

  //TODO バルクインサートはデータの量が多い場合は必要だが、今回は後回しでよい
//  def bulkInsert(issueSeq: Seq[Issue]) = db.run{
//    issue ++= issueSeq
//  }


  private class IssueTable(tag: Tag) extends Table[Issue](tag, "ISSUE") {
    def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)
    def idKey = column[Int]("ID_KEY")
    def issueKey = column[String]("ISSUE_KEY")
    def summary = column[String]("SUMMARY")
    def point = column[Float]("POINT")

    def * = (id, idKey, issueKey, summary, point) <> ((Issue.apply _).tupled, Issue.unapply _)
  }

}
