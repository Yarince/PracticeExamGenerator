package nl.yarince.practiceexamgenerator

/**
  * Created by yarince on 26/06/2018.
  */
class Calculation {

  val MAGIC_NUMBER: Double = 10

  def getAllCategoryRelevance(examResults: List[ExamResult], categoryPercentageList: List[CategoryPercentage]): List[WeightedExam] = {
    val weightedExams = getWeightedExams(examResults)

    categoryPercentageList.foreach { category =>
      category.percentage = calculateCategoryRelevance(examResults, weightedExams, category)
      println(category)
    }
    weightedExams
  }

  def calculateCategoryRelevance(examResults: List[ExamResult], weightedExams: List[WeightedExam], categoryPercentage: CategoryPercentage): Double = {
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
      // If the questionType is already imperfect skip it
      if (!perfectScore) return perfectScore
      perfectScore = exam.questions
        .find(_.categories.contains(categoryPercentage.category))
        .count(!_.resultWasGood) == 0
    }
    perfectScore
  }

  def getWeightedExams(examResults: List[ExamResult]): List[WeightedExam] = {
    examResults.zipWithIndex.map { case (it, current) =>
      WeightedExam(
        it.examId,
        calculateWeight(examResults, current)
      )
    }
  }

  private def calculateWeight(examResults: List[ExamResult], current: Int) =
    100 / examResults.zipWithIndex.map { case (_, index) => Math.pow(2.0, index) }.sum * Math.pow(2, current)
}
