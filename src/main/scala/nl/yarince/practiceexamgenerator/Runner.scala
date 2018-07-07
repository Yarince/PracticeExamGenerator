package nl.yarince.practiceexamgenerator

import nl.yarince.practiceexamgenerator.calculation.{CategoryRelevanceCalculation, QuestionRelevanceCalculation}

/**
  * Created by yarince on 26/06/2018.
  */
object Runner {
  def main(args: Array[String]): Unit = {
    val categoryRelevanceCalculation = new CategoryRelevanceCalculation()
    val questionRelevanceCalculation = new QuestionRelevanceCalculation()
    val dataAccess = new DataAccess()
    val courseId = 1
    val studentId = 1

    val categoryRelevance = categoryRelevanceCalculation.getAllCategoryRelevance(
      dataAccess.getAssessedExams(courseId).filter(_.studentId == studentId),
      dataAccess.getCategories(courseId)
    )
    val questionRelevance = questionRelevanceCalculation.getAllQuestionRelevance(
      dataAccess.getAssessedExams(courseId).filter(_.studentId != studentId)
    )

//    questionRelevance.foreach(println)
//    categoryRelevance.foreach(println)
  }
}
