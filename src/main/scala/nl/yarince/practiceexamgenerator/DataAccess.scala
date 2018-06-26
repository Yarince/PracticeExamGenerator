package nl.yarince.practiceexamgenerator

import org.json4s._
import org.json4s.jackson.JsonMethods._
import scala.io.Source

/**
  * Created by yarince on 26/06/2018.
  */
class DataAccess {
  def getCategories(courseId: Int): List[CategoryPercentage] = {
    List(
      CategoryPercentage("ATAM"),
      CategoryPercentage("QA"),
      CategoryPercentage("Algorithms"),
      CategoryPercentage("Paradigma's"),
      CategoryPercentage("Big Oh"),
      CategoryPercentage("Programming"),
      CategoryPercentage("ASR"),
      CategoryPercentage("DCAR")
    )
  }

  implicit val formats = DefaultFormats

  def getAssessedExams(courseId: Int, studentNr: Int): List[ExamResult] = {
    parse(Source.fromFile(
      "C:\\Users\\yarin\\IntelliJProjects\\PracticeExamGenerator" +
        "\\src\\main\\scala\\nl\\yarince\\practiceexamgenerator\\" +
        "resources\\question+result.json").mkString).extract[List[ExamResult]]
  }
}