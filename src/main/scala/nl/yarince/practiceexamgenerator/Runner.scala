package nl.yarince.practiceexamgenerator

import nl.yarince.practiceexamgenerator.calculation.CategoryRelevanceCalculation

/**
  * Created by yarince on 26/06/2018.
  */
object Runner {
  def main(args: Array[String]): Unit = {
    val calculation = new CategoryRelevanceCalculation()

    val dataAccess = new DataAccess()
    val courseId = 1
    val studentNr = 1

    val categoryRelevance = calculation.getAllCategoryRelevance(
      dataAccess.getAssessedExams(courseId).filter(_.studentId == studentNr),
      dataAccess.getCategories(courseId)
    )


//    weightedExams.foreach(it => println(it.weight))
    categoryRelevance.foreach(println)
//    weightedExams.foreach(it => {
//      it.groupedQuestions.foreach(question => {
//        println(question._1 + " -> " + question._2)
//      })
//      println
//    })
  }
}
