package Services

import Models.Room
import Repositories.RoomRepository
import com.google.inject.Inject


class RoomService @Inject()(roomRepository:RoomRepository) {

  def saveRoom(room:Room) = roomRepository.saveRoom(room)

  def getRooms(page:Int) = roomRepository.getRooms(page)

  def deleteRoom(id:String) = roomRepository.deleteRoom(id)

  def getEditRoom(id:String) = roomRepository.getEditRoom(id)

  def postEditRoom(id:String,newRoom:Room) = roomRepository.postEditRoom(id,newRoom)

  def addRoom(roomData:Room) = roomRepository.addRoom(roomData)

}
