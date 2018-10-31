package nl.yarince.practiceexamgenerator

import java.util.{Calendar, Date}

import org.json4s.DefaultFormats
/**
  * Created by yarince on 26/06/2018.
  */
class DataAccess {
  def getCategories(courseId: Int): List[CategoryPercentage] = {
    // Get all categories from the assessed exams
    getAssessedExams(courseId)
      .map(_.questions.map(_.categories).reduce(_ ++ _)).reduce(_ ++ _).distinct
      .foldLeft(List[CategoryPercentage]()) { (category, catPercentage) => category ++ List(CategoryPercentage(catPercentage))}
  }

  import java.util.Calendar

  def addHoursToJavaUtilDate(date: Date, days: Int): Date = {
    val calendar = Calendar.getInstance
    calendar.setTime(date)
    calendar.add(Calendar.DAY_OF_MONTH, days)
    calendar.getTime
  }

  implicit val formats = DefaultFormats

  def getAssessedExams(courseId: Int): List[ExamResult] = {
    val calendar = Calendar.getInstance
    List(
      ExamResult(1, 1, List(
                      ReviewedQuestion(1, resultWasGood = true, "Vraag over DCAR", List("ASD", "ASD"), "DrawQuestion"),
                      ReviewedQuestion(2, resultWasGood = true, "Vraag over ASR", List("Graph", "Algorithms"), "TreeQuestion"),
                      ReviewedQuestion(3, resultWasGood = false, "Vraag over ATAM", List("Bomen", "Bomen"), "MultipleChoiceQuestion"),
                      ReviewedQuestion(4, resultWasGood = true, "Vraag over DCAR", List("Trees", "Bomen"), "DrawQuestion"),
                      ReviewedQuestion(5, resultWasGood = true, "Vraag over ATAM", List("Bomen", "Fietsen"), "MultipleChoiceQuestion"),
                      ReviewedQuestion(33, resultWasGood = true, "Vraag over ATAM", List("Fietsen", "Bomen"), "MultipleChoiceQuestion"),
                      ReviewedQuestion(6, resultWasGood = false, "Vraag over dingen", List("Graph", "ASD"), "MultipleChoiceQuestion"),
                      ReviewedQuestion(7, resultWasGood = true, "Vraag over ATAM", List("Graph", "Graph"), "TreeQuestion"),
                      ReviewedQuestion(8, resultWasGood = false, "Vraag over dingen", List("Fietsen", "Fietsen"), "MultipleChoiceQuestion")), calendar.getTime ),
      ExamResult(2, 1, List(
                      ReviewedQuestion(9, resultWasGood = false, "Vraag over DCAR", List("Trees", "Algorithms"), "BigOhQuestion"),
                      ReviewedQuestion(10, resultWasGood = true, "Vraag over DCAR", List("Bomen", "Fietsen"), "GraphQuestion"),
                      ReviewedQuestion(11, resultWasGood = false, "Vraag over ASR", List("Trees", "Algorithms"), "OpenQuestion"),
                      ReviewedQuestion(12, resultWasGood = false, "Vraag over ATAM", List("AVL", "Bomen"), "MultipleChoiceQuestion"),
                      ReviewedQuestion(13, resultWasGood = false, "Vraag over ATAM", List("Algorithms", "Trees"), "DrawQuestion"),
                      ReviewedQuestion(14, resultWasGood = false, "Vraag over ATAM", List("Graph", "Bomen"), "MultipleChoiceQuestion"),
                      ReviewedQuestion(15, resultWasGood = false, "Vraag over DCAR", List("ASD", "ASD"), "GraphQuestion"),
                      ReviewedQuestion(16, resultWasGood = true, "Vraag over DCAR", List("AVL", "Algorithms"), "MultipleChoiceQuestion")), addHoursToJavaUtilDate(calendar.getTime,1)),
      ExamResult(3, 2, List(
                      ReviewedQuestion(17, resultWasGood = false, "Vraag over DCAR", List("Bomen", "AVL"), "TreeQuestion"),
                      ReviewedQuestion(34, resultWasGood = true, "Vraag over DCAR", List("Bomen", "True"), "TreeQuestion"),
                      ReviewedQuestion(18, resultWasGood = true, "Vraag over ASR", List("Graph", "Trees"), "BigOhQuestion"),
                      ReviewedQuestion(19, resultWasGood = true, "Vraag over ASR", List("Graph", "ASD"), "BigOhQuestion"),
                      ReviewedQuestion(20, resultWasGood = false, "Vraag over dingen", List("Fietsen", "Algorithms"), "GraphQuestion"),
                      ReviewedQuestion(21, resultWasGood = true, "Vraag over ASR", List("Trees", "AVL"), "DrawQuestion"),
                      ReviewedQuestion(22, resultWasGood = false, "Vraag over ASR", List("Fietsen", "Bomen"), "GraphQuestion"),
                      ReviewedQuestion(23, resultWasGood = true, "Vraag over ASR", List("Bomen", "Fietsen"), "GraphQuestion"),
                      ReviewedQuestion(24, resultWasGood = true, "Vraag over ASR", List("AVL", "Algorithms"), "GraphQuestion")), calendar.getTime),
      ExamResult(4, 2, List(
                      ReviewedQuestion(25, resultWasGood = true, "Vraag over ASR", List("ASD", "Algorithms"), "GraphQuestion"),
                      ReviewedQuestion(26, resultWasGood = false, "Vraag over DCAR", List("Bomen", "AVL"), "TreeQuestion"),
                      ReviewedQuestion(27, resultWasGood = true, "Vraag over ATAM", List("Graph", "Bomen"), "OpenQuestion"),
                      ReviewedQuestion(28, resultWasGood = false, "Vraag over ASR", List("AVL", "Fietsen"), "TreeQuestion"),
                      ReviewedQuestion(29, resultWasGood = false, "Vraag over DCAR", List("ASD", "Bomen"), "MultipleChoiceQuestion"),
                      ReviewedQuestion(30, resultWasGood = true, "Vraag over ATAM", List("True", "Algorithms"), "MultipleChoiceQuestion"),
                      ReviewedQuestion(31, resultWasGood = false, "Vraag over ASR", List("Graph", "Stuff"), "TreeQuestion"),
                      ReviewedQuestion(32, resultWasGood = true, "Vraag over ATAM", List("Trees", "ASD"), "DrawQuestion")), addHoursToJavaUtilDate(calendar.getTime,1)),
      ExamResult(5, 3, List(
                      ReviewedQuestion(35, resultWasGood = true, "Vraag over ASR", List("ASD", "Algorithms"), "GraphQuestion"),
                      ReviewedQuestion(36, resultWasGood = false, "Vraag over DCAR", List("Bomen", "AVL"), "TreeQuestion"),
                      ReviewedQuestion(37, resultWasGood = true, "Vraag over ATAM", List("Graph", "Bomen"), "OpenQuestion"),
                      ReviewedQuestion(38, resultWasGood = false, "Vraag over ASR", List("AVL", "Fietsen"), "TreeQuestion"),
                      ReviewedQuestion(39, resultWasGood = false, "Vraag over DCAR", List("ASD", "Bomen"), "MultipleChoiceQuestion"),
                      ReviewedQuestion(40, resultWasGood = true, "Vraag over ATAM", List("True", "Algorithms"), "MultipleChoiceQuestion"),
                      ReviewedQuestion(41, resultWasGood = false, "Vraag over ASR", List("Graph", "Stuff"), "TreeQuestion"),
                      ReviewedQuestion(42, resultWasGood = true, "Vraag over ATAM", List("Trees", "ASD"), "DrawQuestion")), addHoursToJavaUtilDate(calendar.getTime,1)),
      ExamResult(6, 4, List(
                      ReviewedQuestion(43, resultWasGood = true, "Vraag over ASR", List("ASD", "Algorithms"), "GraphQuestion"),
                      ReviewedQuestion(44, resultWasGood = false, "Vraag over DCAR", List("Bomen", "AVL"), "TreeQuestion"),
                      ReviewedQuestion(45, resultWasGood = true, "Vraag over ATAM", List("Graph", "Bomen"), "OpenQuestion"),
                      ReviewedQuestion(46, resultWasGood = false, "Vraag over ASR", List("AVL", "Fietsen"), "TreeQuestion"),
                      ReviewedQuestion(47, resultWasGood = false, "Vraag over DCAR", List("ASD", "Bomen"), "MultipleChoiceQuestion"),
                      ReviewedQuestion(48, resultWasGood = true, "Vraag over ATAM", List("True", "Algorithms"), "MultipleChoiceQuestion"),
                      ReviewedQuestion(49, resultWasGood = false, "Vraag over ASR", List("Graph", "Stuff"), "TreeQuestion"),
                      ReviewedQuestion(50, resultWasGood = true, "Vraag over ATAM", List("Trees", "ASD"), "DrawQuestion")), calendar.getTime),
      ExamResult(7, 5, List(
                      ReviewedQuestion(51, resultWasGood = true, "Vraag over ASR", List("ASD", "Algorithms"), "GraphQuestion"),
                      ReviewedQuestion(52, resultWasGood = false, "Vraag over DCAR", List("Bomen", "AVL"), "TreeQuestion"),
                      ReviewedQuestion(53, resultWasGood = false, "Vraag over ATAM", List("Graph", "Bomen"), "OpenQuestion"),
                      ReviewedQuestion(54, resultWasGood = false, "Vraag over ASR", List("AVL", "Fietsen"), "TreeQuestion"),
                      ReviewedQuestion(55, resultWasGood = true, "Vraag over DCAR", List("ASD", "Bomen"), "MultipleChoiceQuestion"),
                      ReviewedQuestion(56, resultWasGood = true, "Vraag over ATAM", List("True", "Algorithms"), "MultipleChoiceQuestion"),
                      ReviewedQuestion(57, resultWasGood = false, "Vraag over ASR", List("Graph", "Stuff"), "TreeQuestion"),
                      ReviewedQuestion(58, resultWasGood = false, "Vraag over ATAM", List("Trees", "ASD"), "DrawQuestion")), addHoursToJavaUtilDate(calendar.getTime,1)),
      ExamResult(8, 4, List(
                      ReviewedQuestion(51, resultWasGood = true, "Vraag over ASR", List("ASD", "Algorithms"), "GraphQuestion"),
                      ReviewedQuestion(52, resultWasGood = false, "Vraag over DCAR", List("Bomen", "AVL"), "TreeQuestion"),
                      ReviewedQuestion(53, resultWasGood = false, "Vraag over ATAM", List("Graph", "Bomen"), "OpenQuestion"),
                      ReviewedQuestion(54, resultWasGood = false, "Vraag over ASR", List("AVL", "Fietsen"), "TreeQuestion"),
                      ReviewedQuestion(55, resultWasGood = true, "Vraag over DCAR", List("ASD", "Bomen"), "MultipleChoiceQuestion"),
                      ReviewedQuestion(56, resultWasGood = true, "Vraag over ATAM", List("True", "Algorithms"), "MultipleChoiceQuestion"),
                      ReviewedQuestion(57, resultWasGood = false, "Vraag over ASR", List("Graph", "Stuff"), "TreeQuestion"),
                      ReviewedQuestion(58, resultWasGood = false, "Vraag over ATAM", List("Trees", "ASD"), "DrawQuestion")), addHoursToJavaUtilDate(calendar.getTime,2))
    )
  }
}