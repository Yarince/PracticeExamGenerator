package nl.yarince.practiceexamgenerator.calculation

import nl.yarince.practiceexamgenerator.{ExamResult, Question, ReviewedQuestion}

//import scala.collection.immutable.IntMap

/**
  * Created by yarince on 06/07/2018.
  */
class QuestionRelevanceCalculation {
  def getAllQuestionRelevance(results: List[ExamResult]): Map[Question, Double] = {
    val studentResults = results.groupBy(_.studentId)

    // Get all questions with studentID per questionId
    val questionResult = results
      .foldLeft(Map[Int /* questionId */ , Map[Int /*studentId*/ , ReviewedQuestion]]()) { (mapQuestionIdWithMapStudentToQuestion, examResult) =>
        val newStudentQuestion = mapQuestionIdWithMapStudentToQuestion ++ examResult.questions
          .foldLeft(Map[Int /* questionId */ , Map[Int /*studentId*/ , ReviewedQuestion]]()) { (product, question) =>
            product + (question.questionId -> examResult.questions
              .foldLeft(Map[Int /*studentId*/ , ReviewedQuestion]())((studentMap, acc) => studentMap + (examResult.studentId -> acc)))
          }
        // Combine duplicate entries into 1 map
        newStudentQuestion ++ mapQuestionIdWithMapStudentToQuestion.collect { case (k, v) if newStudentQuestion.contains(k) => (k, v ++ newStudentQuestion(k)) }
      }

    // TODO Rewrite to use questionResult
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
