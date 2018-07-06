package nl.yarince.practiceexamgenerator

/**
  * Created by yarince on 26/06/2018.
  */
object Runner {
  def main(args: Array[String]): Unit = {
    val calculation = new Calculation()

    val dataAccess = new DataAccess()
    val courseId = 1
    val studentNr = 1
    val categoryPercentages = dataAccess.getCategories(courseId)
    println(categoryPercentages)

    val weightedExams = calculation.getAllCategoryRelevance(
      dataAccess.getAssessedExams(courseId).filter(_.studentId == studentNr),
      categoryPercentages
    )


//    weightedExams.foreach(it => println(it.weight))
//    weightedExams.foreach(println)
//    weightedExams.foreach(it => {
//      it.groupedQuestions.foreach(question => {
//        println(question._1 + " -> " + question._2)
//      })
//      println
//    })
  }
}
