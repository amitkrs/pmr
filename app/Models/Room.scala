package Models

import java.util

import org.bson.types.ObjectId
import org.mongodb.morphia.annotations.{Entity, Id}

/**
  * Created by gautam on 9/6/16.
  */

@Entity("Rooms")
case class Room(name:String,number:Int,cost:BigDecimal){
@Id val id = new ObjectId()
  var items:java.util.List[String] = new util.ArrayList[String]()
  protected def this() {this("asdf",0,BigDecimal(1000))}
}
