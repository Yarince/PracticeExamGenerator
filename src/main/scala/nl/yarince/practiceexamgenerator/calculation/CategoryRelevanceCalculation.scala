package nl.yarince.practiceexamgenerator.calculation

import nl.yarince.practiceexamgenerator.{CategoryPercentage, ExamResult, WeightedExam}

/**
  * Created by yarince on 26/06/2018.
  */
class CategoryRelevanceCalculation {

  val MAGIC_NUMBER: Double = 10

  def getAllCategoryRelevance(examResults: List[ExamResult], categoryPercentageList: List[CategoryPercentage]): List[CategoryPercentage] = {
    categoryPercentageList.foreach { category =>
      category.percentage = calculateCategoryRelevance(examResults, getWeightedExams(examResults), category)
    }
    categoryPercentageList
  }

  def calculateCategoryRelevance(examResults: List[ExamResult], weightedExams: List[WeightedExam], categoryPercentage: CategoryPercentage): Double = {
    // If the category isn't answered by the current student give it a 100% relevance
    if (examResults.map(_.questions.filter(_.categories.exists(_ == categoryPercentage.category))).reduce(_ ++ _).isEmpty) return 100.0
    // If the category is answered perfectly give it a set relevance
    if (getPerfectScore(examResults, categoryPercentage)) return MAGIC_NUMBER

    categoryPercentage.percentage = weightedExams.foldLeft(0.0) { (percentage, exam) =>
      // Get all questions for the current category
      val questionsForCategory = examResults.map(_.questions.filter(_.categories.contains(categoryPercentage.category))).reduce(_ ++ _)
      if (questionsForCategory.isEmpty)
        percentage + 0.0
      else
      // Multiply the percentage of questions wrongly answered by the relevance of the exam
        percentage + exam.weight * (questionsForCategory.count(!_.resultWasGood).toDouble / questionsForCategory.size)

      // Divide the relevance by the total amount of points to be distributed.
      // Multiply by 100 to get correct percentage.
    } / weightedExams.foldLeft(0.0) { (a, b) => a + b.weight } * 100

    categoryPercentage.percentage
  }

  def getPerfectScore(examResults: List[ExamResult], categoryPercentage: CategoryPercentage): Boolean = {
    var perfectScore: Boolean = true
    examResults.foldLeft() { (_, exam) =>
      // If the category is already imperfect skip it
      if (!perfectScore) return perfectScore
      perfectScore = exam.questions
        .find(_.categories.contains(categoryPercentage.category))
        .count(!_.resultWasGood) == 0
    }
    perfectScore
  }

  def getWeightedExams(examResults: List[ExamResult]): List[WeightedExam] =
    examResults.zipWithIndex.map { case (it, current) => WeightedExam(it.examId, calculateWeight(examResults, current)) }

  private def calculateWeight(examResults: List[ExamResult], current: Int) =
    100 / examResults.zipWithIndex.map { case (_, index) => Math.pow(2.0, index) }.sum * Math.pow(2, current)
}
