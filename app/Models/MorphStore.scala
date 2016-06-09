package Models

import com.mongodb.{MongoClientURI, MongoClient}
import org.mongodb.morphia.mapping.MapperOptions
import org.mongodb.morphia.{Datastore, Morphia}
import play.api.Play
import play.api.Play.current

object MorphStore {

  private val _mongoClient = new MongoClient(new MongoClientURI(Play.configuration.getString("mongo.url").getOrElse("")))

val morphia : Morphia = new Morphia()

  val mapper = morphia.getMapper.getOptions

  mapper.setStoreNulls(true)

  mapper.setStoreEmpties(true)

  morphia.getMapper.setOptions(mapper)

  morphia.getMapper.getConverters.addConverter(classOf[BigDecimalConverter])

  morphia.getMapper.getConverters.addConverter(classOf[SeqConverter])

//  morphia.getMapper.getConverters.addConverter(classOf[SeqConverter])

  //  private var _datastore: Datastore = null

 private val _datastore = morphia.createDatastore(
    _mongoClient,
    Play.configuration.getString("mongo.dbName").getOrElse("")
  )

def getInstance = {
  _datastore.ensureIndexes()
  _datastore
}
}


//final Datastore datastore = morphia.createDatastore(new MongoClient(), "morphia_example");
//datastore.ensureIndexes();