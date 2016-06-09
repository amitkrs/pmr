package controllers

import Models.{Room, MorphStore}
import Services.RoomService
import com.google.inject.Inject
import play.api.mvc.{Action, Controller}

import scala.concurrent.ExecutionContext.Implicits.global

class RoomController @Inject()(roomService: RoomService) extends Controller {

  val ins = MorphStore.getInstance

  def getRooms(page: Int) = Action.async{
    implicit request =>
      val results = roomService.getRooms(page)
      results.map(rooms =>
      Ok(views.html.Rooms.viewAll(rooms)))
  }

  def deleteRoom(id: String) = Action {


    roomService.deleteRoom(id)

    Redirect("/getRooms")
  }

  def getRoom(id: String) = Action {

    val room = roomService.getEditRoom(id)

    Ok(views.html.Rooms.editRoom(room))
  }


  def postRoom(id: String) = Action {
    implicit request =>
      val room = Forms.Forms.editForm.bindFromRequest.get

      val newRoom = room.getRoom

      roomService.postEditRoom(id, newRoom)

      Redirect("/getRooms")
  }

  def getAddRoom = Action {
    Ok(views.html.Rooms.addRoom())
  }

  def postAddRoom = Action {
    implicit request =>

      val room = Forms.Forms.editForm.bindFromRequest.get

      val newRoom = room.getRoom

      roomService.addRoom(newRoom)

      Redirect("/getRooms")

  }
}
