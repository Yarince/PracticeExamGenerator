package nl.yarince.practiceexamgenerator

import nl.yarince.practiceexamgenerator.calculation.{CategoryRelevanceCalculation, ExamRelevanceCalculation, QuestionRelevanceCalculation}

/**
  * Created by yarince on 26/06/2018.
  */
object Runner {
  def main(args: Array[String]): Unit = {
    val categoryRelevanceCalculation = new CategoryRelevanceCalculation()
    val questionRelevanceCalculation = new QuestionRelevanceCalculation()
    val examRelevanceCalculation = new ExamRelevanceCalculation()
    val dataAccess = new DataAccess()

    val courseId = 1
    val studentId = 1
    val examResults = dataAccess.getAssessedExams(courseId)
    val categoryPercentages = dataAccess.getCategories(courseId)

    val weightedExams = examRelevanceCalculation.getExamRelevance(examResults)
    val categoryRelevance = categoryRelevanceCalculation.getAllCategoryRelevance(
      examResults.filter(_.studentId == studentId),
      categoryPercentages,
      weightedExams
    )

    val questionRelevance = questionRelevanceCalculation.getAllQuestionRelevance(
      examResults.filter(_.studentId != studentId)
    )

    weightedExams.foreach(println)
    questionRelevance.foreach(println)
    categoryRelevance.foreach(println)


  }
}
