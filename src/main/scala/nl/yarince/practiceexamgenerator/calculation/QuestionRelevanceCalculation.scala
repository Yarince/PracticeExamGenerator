package nl.yarince.practiceexamgenerator.calculation

import nl.yarince.practiceexamgenerator.{ExamResult, Question}

/**
  * Created by yarince on 06/07/2018.
  */
class QuestionRelevanceCalculation {
  def getAllQuestionRelevance(results: List[ExamResult]): Map[Question, Double] = {
    val studentResults = results.groupBy(_.studentId)
    // TODO make a list of questions by student id and question id
    val questionResult = results.groupBy(_.studentId).values.groupBy(_.map(_.questions))

    val questionsTotal = studentResults.foldLeft(0)((a, b) => a + b._2.foldLeft(0)((c, d) => c + d.questions.size))
    studentResults.foreach { (student) =>
      val resultCals = student._2.foldLeft(0) { (rxd, exam) =>
        rxd + exam.questions.foldLeft(0) {
          (rdxq, exam) => if (exam.resultWasGood) rdxq + 1 else rdxq - 1
        }
      }.toDouble
      val avgResult = resultCals / questionsTotal
      println(avgResult)
    }

    val questionRelevance = studentResults.map(_._2.map(_.questions.foldLeft(Map[Question, Double]()) {
      (map, question) => map + (Question(question.questionId, question.questionText, question.categories) -> 0.0d)
    }).reduce(_ ++ _)).reduce(_ ++ _)

    questionRelevance
  }
}
