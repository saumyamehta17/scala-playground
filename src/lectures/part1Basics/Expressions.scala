package lectures.part1Basics

object Expressions extends App {

  val x = 1 + 2 // Expressions
  println(x)
  println(1+2+1)
  // + - * / & | ^  << >> >>>

  val aString = "Hello World"
  println(aString)
  println("Hello World")

  var i = 0
  while(i < 10){
    println(i)
    i += 1
  } // Never write this again

  var aVariable = 10
  val aWeiredValue = (aVariable = 100)
  println(aVariable)
  println(aWeiredValue)
}
