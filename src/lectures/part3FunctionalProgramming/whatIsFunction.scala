package lectures.part3FunctionalProgramming

object whatIsFunction extends App{
  // work with function as with plain values

  // Problem: OOP

  val doubler = new MyFunction[Int, Int]{
    override def apply(elem: Int): Int = elem * 2
  }
  println(doubler(2))

  // Scala support inbuilt function types upto 22 parameters
  val adder = new Function2[Int, Int, Int] {
    override def apply(v1: Int, v2: Int): Int = v1 + v2
  }
  println(adder(2,4))

  // syntatic sugar
  // [A, B, R] === (A,B) => R

  // Assignments
  // 1. a function which takes 2 strings and concatenate them
  val concate: (String, String) => String = new Function2[String, String, String] {
    override def apply(v1: String, v2: String): String = v1 + " " + v2
  }
  println(concate("Saumya", "Mehta"))

  // 2. transform the MyPredicate and MyTransformer into function types

  // 3. define a function which takes an int and returns another function which takes an int and returns an int
     //- whats the type of function
     //- how to do it..
     // Function[Int, Function[Int, Int])
     val superAdder = new Function[Int, Function[Int, Int]]{
       override def apply(x: Int): Function[Int, Int] = new Function[Int, Int]{
         override def apply(y: Int): Int = x + y
       }
     }

    println(superAdder(3)(4))
    val superadder3 = superAdder(3)
    println(superadder3(5))
}

//class Action{
//  def execute(elem: Int): String = ???
//}

trait MyFunction[A,B]{
  def apply(elem: A): B
}
