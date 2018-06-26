package nl.yarince.practiceexamgenerator

import org.json4s.JsonDSL
import org.json4s.jackson.JsonMethods._

/**
  * Created by yarince on 26/06/2018.
  */
object Runner {
  def main(args: Array[String]): Unit = {
    val calculation = new Calculation()

    val dataAccess = new DataAccess()
    val courseId = 1
    val studentNr = 1
    val weightedExams = calculation.calculateCategoryRelevance(
      dataAccess.getAssessedExams(courseId, studentNr),
      dataAccess.getCategories(courseId)
    )


    weightedExams.foreach(it => println(it.weight))
    weightedExams.foreach(println)
    weightedExams.foreach(it => {
      it.groupedQuestions.foreach(question => {
        println(question._1 + " -> " + question._2)
      })
      println
    })
  }
}
