package src.lectures.part2OOP

object MethodNotation extends App {

  class Person(val name: String, favouriteMovie: String, val age: Int) {

    def likes(movie: String) = movie == favouriteMovie
    def +(person: Person) = s"${this.name} hang outs with ${person.name}"
    def +(str: String) = s"${name} (${str})"

    def unary_! : String = "Hey Practicing Unary Operators...."

    def isAlive = s"${name} is Alive!!"

    def apply() = s"Hi My name is ${name} and I like ${favouriteMovie}"
    def apply(n: Int) = s"${name} watched ${favouriteMovie} ${n} times."

    def unary_+ = s"${name} is ${age+1} year old"

    def learns(subject: String) = s"${name} learns ${subject}"
    def learnsScala = this.learns("Scala")
  }

  val mary = new Person("Mary", "Inception", 20)
  println(mary.likes("Bad Bang"))
  println(mary likes "Bad Bang")

  //  Operators in Scala
  val jamie = new Person("Jamie", "Iron Man", 21)
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
  println(mary isAlive)
  // Apply()
  println(mary())

  //  Exercise
  //  1. overload + operator
  println(mary.+("the Rockstar"))
  println(mary + "the Rockstar")

  //  2. Add an age to Person class
  //     - Add a unary + operator => new person with the age+1
  //     - +mary => mary with age incrementer
  println(+mary)

  // 3. Add learns method in Person class => "Mary learns Scala"
  //    Add method learnsScala => will call learns('scala')
  //    Use Postfix Notation
  println(mary.learns("Ruby"))
  println(mary learns "Python")
  println(mary learnsScala)

  // 4. Overload the apply method
  //    mary.apply(2) => "Mary watched inception 2 times"
  println(mary.apply(2))
  println(mary(2))




}
