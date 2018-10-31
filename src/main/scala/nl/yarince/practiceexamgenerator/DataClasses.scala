package nl.yarince.practiceexamgenerator

/**
  * Created by yarince on 26/06/2018.
  */

import java.util.Date

case class AnsweredQuestion(
                             questionId: Int,
                             questionText: String,
                             categories: List[String],
                             questionType: String,
                             answeredOn: Option[Date]
                           )

case class ExamResult(
                       examId: Int,
                       studentId: Int,
                       questions: List[ReviewedQuestion],
                       examDate: Date
                     )

case class WeightedExam(
                         examId: Int,
                         weight: Double
                       )

case class CategoryPercentage(
                               category: String,
                               var percentage: Double = 0.0
                             )

case class ReviewedQuestion(
                             questionId: Int,
                             resultWasGood: Boolean,
                             questionText: String,
                             categories: List[String],
                             questionType: String
                           )

case class Question(
                     questionId: Int,
                     questionText: String,
                     category: List[String]
                   )
