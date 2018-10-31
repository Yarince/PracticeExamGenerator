package nl.yarince.practiceexamgenerator.calculation

import nl.yarince.practiceexamgenerator.{ExamResult, WeightedExam}

class ExamRelevanceCalculation {
  def getExamRelevance(examResults: List[ExamResult]): List[WeightedExam] =
    examResults.zipWithIndex.map { case (it, current) => WeightedExam(it.examId, calculateWeight(examResults, current)) }

  private def calculateWeight(examResults: List[ExamResult], current: Int) =
    100 / examResults.zipWithIndex.map { case (_, index) => Math.pow(2.0, index) }.sum * Math.pow(2, current)
}