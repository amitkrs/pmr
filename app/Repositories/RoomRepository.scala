package Repositories

import Models.{Room, MorphStore}
import org.bson.types.ObjectId

//import scala.concurrent.Future
//import scala.concurrent.ExecutionContext.Implicits.global
import scala.collection.JavaConversions._


class RoomRepository {

  val ins = MorphStore.getInstance

  val createQuery = ins.createQuery[Room](classOf[Room])

  def saveRoom(room:Room)={

    ins.save[Room](room)

  }

  def getRooms(page:Int) = {
    createQuery.offset((page-1)*4).limit(4).toList
  }

  def deleteRoom(id:String)={
    ins.delete[Room](createQuery.field("id").equal(new ObjectId(id)))
  }

  def getEditRoom(id:String)={
    Option(createQuery.field("id").equal(new ObjectId(id)).get())
  }

  def postEditRoom(id:String,newRoom:Room) = {

    val room = this.getEditRoom(id)
    room match {
      case Some(room) => ins.update(room,ins.createUpdateOperations[Room](classOf[Room]).set("name",newRoom.name).set("number",newRoom.number).set("cost",newRoom.cost).set("items",newRoom.items))
      case _ =>
    }

  }

  def addRoom(roomData:Room)={
    ins.save[Room](roomData)
  }

}
