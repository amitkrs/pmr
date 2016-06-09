package Models

import java.util

import org.bson.types.ObjectId
import org.mongodb.morphia.annotations.{Entity, Id}


@Entity("Rooms")
case class Room(name:String,number:Int,cost:BigDecimal){
@Id val id = new ObjectId()
  var items:java.util.List[String] = new util.ArrayList[String]()
  protected def this() {this("asdf",0,BigDecimal(1000))}
}
