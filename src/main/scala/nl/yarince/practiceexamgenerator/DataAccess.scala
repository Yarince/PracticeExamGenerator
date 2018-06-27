package nl.yarince.practiceexamgenerator

import org.json4s.DefaultFormats
import org.json4s.jackson.JsonMethods.parse
import scala.io.Source

/**
  * Created by yarince on 26/06/2018.
  */
class DataAccess {
  def getCategories(courseId: Int): List[CategoryPercentage] = {
    List(
      CategoryPercentage("ATAM"),
      CategoryPercentage("QA"),
      CategoryPercentage("Algorithms"),
      CategoryPercentage("Paradigma's"),
      CategoryPercentage("Big Oh"),
      CategoryPercentage("Programming"),
      CategoryPercentage("ASR"),
      CategoryPercentage("ASD"),
      CategoryPercentage("DCAR")
    )
  }

  implicit val formats = DefaultFormats

  def getAssessedExams(courseId: Int, studentNr: Int): List[ExamResult] = {
//    parse(Source.fromFile(
//      "C:\\Users\\yarin\\IntelliJProjects\\PracticeExamGenerator" +
//        "\\src\\main\\scala\\nl\\yarince\\practiceexamgenerator\\" +
//        "resources\\question+result.json").mkString).extract[List[ExamResult]]

    List(
      ExamResult(1, None,
        List(
          ReviewedQuestion(1, resultWasGood = true, "Vraag over DCAR", List("ASD", "ASD"), "DrawQuestion"),
          ReviewedQuestion(2, resultWasGood = true, "Vraag over ASR", List("Graph", "Algorithms"), "TreeQuestion"),
          ReviewedQuestion(3, resultWasGood = false, "Vraag over ATAM", List("Bomen", "Bomen"), "MultipleChoiceQuestion"),
          ReviewedQuestion(4, resultWasGood = true, "Vraag over DCAR", List("Trees", "Bomen"), "DrawQuestion"),
          ReviewedQuestion(5, resultWasGood = true, "Vraag over ATAM", List("Bomen", "Fietsen"), "MultipleChoiceQuestion"),
          ReviewedQuestion(33, resultWasGood = true, "Vraag over ATAM", List("Fietsen", "Bomen"), "MultipleChoiceQuestion"),
          ReviewedQuestion(6, resultWasGood = false, "Vraag over dingen", List("Graph", "ASD"), "MultipleChoiceQuestion"),
          ReviewedQuestion(7, resultWasGood = true, "Vraag over ATAM", List("Graph", "Graph"), "TreeQuestion"),
          ReviewedQuestion(8, resultWasGood = false, "Vraag over dingen", List("Fietsen", "Fietsen"), "MultipleChoiceQuestion"))),
      ExamResult(2, None,
        List(
          ReviewedQuestion(9, resultWasGood = false, "Vraag over DCAR", List("Trees", "Algorithms"), "BigOhQuestion"),
          ReviewedQuestion(10, resultWasGood = false, "Vraag over DCAR", List("Bomen", "Fietsen"), "GraphQuestion"),
          ReviewedQuestion(11, resultWasGood = false, "Vraag over ASR", List("Trees", "Algorithms"), "OpenQuestion"),
          ReviewedQuestion(12, resultWasGood = false, "Vraag over ATAM", List("AVL", "Bomen"), "MultipleChoiceQuestion"),
          ReviewedQuestion(13, resultWasGood = true, "Vraag over ATAM", List("Algorithms", "Trees"), "DrawQuestion"),
          ReviewedQuestion(14, resultWasGood = true, "Vraag over ATAM", List("Graph", "Bomen"), "MultipleChoiceQuestion"),
          ReviewedQuestion(15, resultWasGood = false, "Vraag over DCAR", List("ASD", "ASD"), "GraphQuestion"),
          ReviewedQuestion(16, resultWasGood = true, "Vraag over DCAR", List("AVL", "Algorithms"), "MultipleChoiceQuestion"))),
      ExamResult(3, None,
        List(
          ReviewedQuestion(17, resultWasGood = false, "Vraag over DCAR", List("Bomen", "AVL"), "TreeQuestion"),
          ReviewedQuestion(18, resultWasGood = true, "Vraag over ASR", List("Graph", "Trees"), "BigOhQuestion"),
          ReviewedQuestion(19, resultWasGood = false, "Vraag over ASR", List("Graph", "ASD"), "BigOhQuestion"),
          ReviewedQuestion(20, resultWasGood = false, "Vraag over dingen", List("Fietsen", "Algorithms"), "GraphQuestion"),
          ReviewedQuestion(21, resultWasGood = true, "Vraag over ASR", List("Trees", "AVL"), "DrawQuestion"),
          ReviewedQuestion(22, resultWasGood = false, "Vraag over ASR", List("Fietsen", "Bomen"), "GraphQuestion"),
          ReviewedQuestion(23, resultWasGood = true, "Vraag over ASR", List("Bomen", "Fietsen"), "GraphQuestion"),
          ReviewedQuestion(24, resultWasGood = true, "Vraag over ASR", List("AVL", "Algorithms"), "GraphQuestion"))),
      ExamResult(4, None,
        List(
          ReviewedQuestion(25, resultWasGood = true, "Vraag over ASR", List("ASD", "Algorithms"), "GraphQuestion"),
          ReviewedQuestion(26, resultWasGood = false, "Vraag over DCAR", List("Bomen", "AVL"), "TreeQuestion"),
          ReviewedQuestion(27, resultWasGood = false, "Vraag over ATAM", List("Graph", "Bomen"), "OpenQuestion"),
          ReviewedQuestion(28, resultWasGood = false, "Vraag over ASR", List("AVL", "Fietsen"), "TreeQuestion"),
          ReviewedQuestion(29, resultWasGood = true, "Vraag over DCAR", List("ASD", "Bomen"), "MultipleChoiceQuestion"),
          ReviewedQuestion(30, resultWasGood = true, "Vraag over ATAM", List("Bomen", "Algorithms"), "MultipleChoiceQuestion"),
          ReviewedQuestion(31, resultWasGood = false, "Vraag over ASR", List("Graph", "Graph"), "TreeQuestion"),
          ReviewedQuestion(32, resultWasGood = true, "Vraag over ATAM", List("Trees", "ASD"), "DrawQuestion")
        )
      )
    )
  }
}