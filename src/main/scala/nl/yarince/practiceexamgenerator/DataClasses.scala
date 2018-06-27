package nl.yarince.practiceexamgenerator

/**
  * Created by yarince on 26/06/2018.
  */

import java.sql.Date

case class AnsweredQuestion(
                             questionId: Int,
                             questionText: String,
                             categories: List[String],
                             questionType: String,
                             answeredOn: Option[java.util.Date]
                           )

case class ExamResult(
                       examId: Int,
                       examDate: Option[java.util.Date],
                       questions: List[ReviewedQuestion]
                     )

case class WeightedExam(
                         examId: Int,
                         weight: Double,
                         groupedQuestions: Map[List[String], List[ReviewedQuestion]]
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
