package controllers

import java.io.File
import Models.{MorphStore => jaaadu, Teacher, Student}
import play.api.Play.current
import play.api.i18n.Messages.Implicits._
import play.api._
import play.api.mvc._
import views.html

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.collection.JavaConversions._

class Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

//  def firstPage=LoggingAction{
////    Ok("first").withCookies(Cookie("attempt","first"))
//request =>
//
//  Ok(request.acceptLanguages.mkString(", "))
////    Ok("first").flashing("session" -> "firstS")
//
////  Redirect(routes.Application.second(Some("fromFirst")))
//  }
//
//  def second(x:Option[String])= Action{
//    request =>
//      Ok(request.flash.get("session").map(_.toString).getOrElse("No sess"))
////      Ok(request.cookies.get("attempt").map(_.toString).getOrElse("al"))
////    Ok(x.map(_.length.toString).getOrElse("0"))
//  }
//
//  def parseWork = Action(parse.file(new File("/home/tima/a.jpg"))){
//    request =>
//    Ok("file uploaded" + request.body)
//  }
//
//  def submitFile = Action{
//    Ok(views.html.formImage())
//  }
//
//  def getForm = Action{
//    Ok(views.html.formUser(UserData.userForm))
//  }
//
//  def postForm = Action{implicit  request =>
//    UserData.userForm.bindFromRequest.fold(
//   errors => {
//     BadRequest(views.html.formUser(errors))
//   },
//      success =>
//        {
//          Redirect("/first")
//        }
//    )
//    }

  def saveStudent = Action.async{
    implicit req =>
      Future {
        //  val name = req.getQueryString("name").getOrElse("amit")
        val name = req.getQueryString("name").orNull
        val num = req.getQueryString("num").map(_.toInt).getOrElse(0)

//        val student = Student(num, name)
        val student = Student(num,name)
//        student.list = Option(name).map(x => x.map(_.toString).toSeq).getOrElse(List("abcd","efgh"))
        student.list = List("asdf","dfgh")
        if(num !=0){
          student.inSchool = Some(true)
        }
        val teacher = Teacher(num + "teacher")
        student.teacher = teacher
        val iDao = jaaadu.getInstance

        iDao.save[Student](student)
        iDao.save[Teacher](teacher)

        val query = iDao.createQuery[Student](classOf[Student]).field("name").equal("asdf").asList()

         val first = query.head




        //        Redirect("/index")

        Ok(s"fetched ${first}")
      }
  }


//  def searchStudent()
}
