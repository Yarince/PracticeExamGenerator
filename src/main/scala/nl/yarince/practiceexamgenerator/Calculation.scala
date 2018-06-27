package nl.yarince.practiceexamgenerator

/**
  * Created by yarince on 26/06/2018.
  */
class Calculation {
  def calculateCategoryRelevance(examResults: List[ExamResult], categoryPercentageList: List[CategoryPercentage]): List[WeightedExam] = {
    val weightedExams = getWeightedExams(examResults)

//    categoryPercentageList.foreach { category =>
//      val perfectScore = getPerfectScore(weightedExams, category)
//
//      println(perfectScore)
//
//    }

    weightedExams
  }

  // Todo: make it work at all
  def getPerfectScore(weightedExams: List[WeightedExam], categoryPercentage: CategoryPercentage): Boolean = {
    var perfectScore: Boolean = true

    weightedExams.foreach { exam =>
      // If the questionType is already imperfect skip it
      if (!perfectScore) return perfectScore

      // If there is a result with more than 0 resultWasGood == false set perfectScore to false
//      val questions = exam.groupedQuestions.getOrElse(categoryPercentage.category, return perfectScore)
      perfectScore = exam.groupedQuestions.exists(question => question._2.exists(reviewQuestion => reviewQuestion.resultWasGood))
//      if (questions == null) return perfectScore
//
//      perfectScore = questions.count(question => question.resultWasGood)// { question => !question.resultWasGood } == 0
    }

    /// Awesome hopelijk werkt het
    //    weightedExams.foldLeft(true) { (other, exam) =>
    //      if (!other) return other
    //
    //      exam.groupedQuestions
    //        .getOrElse(categoryPercentage.category, return true)
    //        .count { question => !question.resultWasGood } == 0
    //    }

    perfectScore
  }

  // TODO Hier doe ik het fout met het groupen by questionType. Dit moet natuurlijk Category zijn. :/
  def getWeightedExams(examResults: List[ExamResult]): List[WeightedExam] = {
    examResults.zipWithIndex.map { case (it, current) =>
//      it.questions.foreach {question => question.categories.sortBy(string => string)}
      WeightedExam(
        it.examId,
        calculateWeight(examResults, current),
        it.questions.groupBy(question => question.categories.sortBy(string => string))
      )
    }
  }

  private def calculateWeight(examResults: List[ExamResult], current: Int) = {
    100 / examResults.zipWithIndex.map { case (_, index) => Math.pow(2.0, index) }.sum * Math.pow(2, current)
  }
}
