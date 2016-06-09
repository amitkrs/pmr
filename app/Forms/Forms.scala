package Forms

import java.util

import Models.Room
import play.api.Logger
import play.api.data._
import play.api.data.Forms._
import scala.collection.JavaConversions._

object Forms {

  case class RoomFull(name:String,number:Int,cost:BigDecimal,items:Option[String]){
    def getRoom = {
      val room = Room(name,number,cost)
      this.items match {
        case Some(x) =>var arr = new util.ArrayList[String]()
          arr.appendAll(x.split(",").map(_.trim))
          Logger.info(arr.toString)
          room.items = arr
          Logger.info(room.items.toString)
      }
      Logger.info(room.toString)
      room
    }
  }

  val editForm = Form(
    mapping(
      "name" -> text,
      "number" -> number,
      "cost" -> bigDecimal,
      "items" -> optional(text)
    )(RoomFull.apply)(RoomFull.unapply)
  )

}
