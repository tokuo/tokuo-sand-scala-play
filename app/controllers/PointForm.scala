package controllers

object PointForm {
  import play.api.data.Forms._
  import play.api.data.Form


  //TODO 可能であればpointはFloatにする（なお小数点第一位まで）
  case class Data(issueKey: String, point: Int)

  val form = Form(
    mapping(
      "issueKey" -> nonEmptyText,
      "point" -> number
    )(Data.apply)(Data.unapply)
  )

}
