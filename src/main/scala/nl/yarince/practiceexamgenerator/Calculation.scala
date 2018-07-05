package nl.yarince.practiceexamgenerator

/**
  * Created by yarince on 26/06/2018.
  */
class Calculation {

  val MAGIC_NUMBER: Double = 10

  def getCategoryRelevances(examResults: List[ExamResult], categoryPercentageList: List[CategoryPercentage]): List[WeightedExam] = {
    val weightedExams = getWeightedExams(examResults)

    categoryPercentageList.foreach { category =>
      category.percentage = calculateCategoryRelevance(examResults, weightedExams, category).percentage
    }
    weightedExams

  }

  def calculateCategoryRelevance(examResults: List[ExamResult], weightedExams: List[WeightedExam] , categoryPercentage: CategoryPercentage): Double = {
    val perfectScore = getPerfectScore(examResults, categoryPercentage)
    if (perfectScore) return MAGIC_NUMBER

    categoryPercentage.percentage = weightedExams.sum { exam =>

            // Get all questions for the current questionTypes
            val questionsForType = (exam.groupedQuestions[typePercentage.questionType]
              ?: return@sumByDouble 0.0)
//
//            // Multiply the percentage of questions wrongly answered by the relevance of the exam
//            return@sumByDouble exam.weight * (questionsForType.filter { !it.resultWasGood }
//              .size.toDouble() / questionsForType.size)
          }
//            // Divide the relevance by the total amount of points to be distributed.
//            // Multiply by 100 to get correct percentage.
//            .div(weightedExams.sumByDouble { it.weight }) * 100

    println(perfectScore, categoryPercentage)

    categoryPercentage.percentage
  }


  // Todo: make it work at all
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

  private def calculateWeight(examResults: List[ExamResult], current: Int)

  =
    100 / examResults.zipWithIndex.map { case (_, index) => Math.pow(2.0, index) }.sum * Math.pow(2, current)
}
