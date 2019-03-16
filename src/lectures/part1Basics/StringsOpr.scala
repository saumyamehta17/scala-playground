package lectures.part1Basics

object StringsOpr extends App {

//  Basic operation of strings
  val s = "Hello, How are you?"
  println("Char at " + s.charAt(0))
  println("SubString " + s.substring(0,3))
  println("Split function" + s.split(" ").toList)
  println("Reverse " + s.reverse)
  println("Take 3 " + s.take(3))
  println("Lowercase " + s.toLowerCase)

  val n = "12"
  println("Str to number " + n.toInt)

// s Interpolation
  val name = "David"
  val age = 13
  println(s"My name is $name and age is $age")
  println(s"My name is $name and age is ${age+1}")

// f Interpolation - formatted strings
  val marks = 12.11f
  println(f"My name is $name and marks are $marks%2.2f")
  println(f"My name is $name and marks are $marks%.1f")

// raw Interpolation
   println("Hello \n world")
   println(raw"Hello \n world")
   val str = "Hello \n world"
   println(raw"$str")
}
