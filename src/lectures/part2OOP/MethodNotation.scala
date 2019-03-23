package src.lectures.part2OOP

object MethodNotation extends App {

  class Person(val name: String, favouriteMovie: String) {

    def likes(movie: String) = movie == favouriteMovie
    def +(person: Person) = s"${this.name} hang outs with ${person.name}"

    def unary_! : String = "Hey Practicing Unary Operators...."

    def isAlive = ""
  }

  val mary = new Person("Mary", "Inception")
  println(mary.likes("Bad Bang"))
  println(mary likes "Bad Bang")

  //  Operators in Scala
  val jamie = new Person("Jamie", "Iron Man")
  println(mary + jamie)
  println(mary.+(jamie))

  println(1 + 2)
  println(1.+(2))

  //  All operators are methods

  // Prefix Notation
  // unary_ prefix only works with -, +, !, ~
  val x = -1 // equivalent to 1.unary_-
  val y = 1.unary_-
  println(mary.unary_!)
  println(!mary)

  // Postfix Notation

  // Apply()
}
