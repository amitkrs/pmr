package controllers

import Models.{Room, MorphStore}
import Services.RoomService
import com.google.inject.Inject
import play.api.mvc.{Action, Controller}
import scala.concurrent.ExecutionContext.Implicits.global

class RoomController @Inject()(roomService:RoomService) extends Controller{

  val ins = MorphStore.getInstance

//  val query = ins.createQuery[Room](classOf[Room])

  def saveRoom = Action{
    implicit request =>
    val name= request.getQueryString("name").getOrElse("default")
      val num = request.getQueryString("num").map(_.toInt).getOrElse(0)
    val cost = request.getQueryString("cost").map(BigDecimal(_)).getOrElse(BigDecimal(1000))
      val room = Room(name,num,cost)

      roomService.saveRoom(room)

//      ins.save[Room](room)

      Ok("saved")
  }

  def getRooms(page:Int) = Action.async{
implicit request =>
    val results = roomService.getRooms(page)

//    val results = ins.createQuery[Room](classOf[Room]).asList()

  results.map(rooms =>
  Ok(views.html.Rooms.viewAll(rooms)))
  }

  def deleteRoom(id:String) = Action{

//    ins.delete[Room](ins.createQuery[Room](classOf[Room]).field("id").equal(new ObjectId(id)))

    roomService.deleteRoom(id)

    Redirect("/getRooms")
  }

  def getRoom(id:String) = Action{
//   val query = ins.createQuery[Room](classOf[Room]).field("id").equal(new ObjectId(id))

    val room = roomService.getEditRoom(id)

    Ok(views.html.Rooms.editRoom(room))
  }


  def postRoom(id:String)=Action{
    implicit request =>
    val room = Forms.Forms.editForm.bindFromRequest.get

      val newRoom = room.getRoom
//     val room = ins.createQuery[Room](classOf[Room]).field("id").equal(new ObjectId(id)).get()

//      ins.update[Room](room,ins.createUpdateOperations[Room](classOf[Room]).set("name",newRoom.name).set("number",newRoom.number))

      roomService.postEditRoom(id,newRoom)

      Redirect("/getRooms")
  }

  def getAddRoom =Action{
    Ok(views.html.Rooms.addRoom())
  }

  def postAddRoom = Action{
    implicit request =>

    val room = Forms.Forms.editForm.bindFromRequest.get

      val newRoom = room.getRoom

      roomService.addRoom(newRoom)

//    ins.save[Room](newRoom)

    Redirect("/getRooms")

  }
}
