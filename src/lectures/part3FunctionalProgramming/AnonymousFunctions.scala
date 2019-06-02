package lectures.part3FunctionalProgramming

object AnonymousFunctions extends App{

   // 1st way Anonymous Functions
    val doubler = new Function[Int, Int] {
      override def apply(v1: Int): Int = v1 * 2
    }


    // 2 way Anonymous Functions(LAMBDA)
    val doubler2: Int => Int = (x: Int) => x * 2

    // multiple params in lambda
    val adder: (Int, Int) => Int = (a: Int, b: Int) => a + b

    // no params
    val noParams: () => Int = () => 2

    println(noParams) // function itself

    println(noParams()) // call

   // curly braces with lambdas
   val stringToInt: String => Int = {
     (a: String) => a.toInt
   }

   // Syntactic Sugar

   val niceIncrementer: Int => Int = (num: Int) => num + 1
   // 2nd way of above
   val niceIncrementer2: Int => Int =  _ + 1

   val niceAdder: (Int, Int) => Int = (a: Int, b: Int) => a + b
   // 2nd way of above
   val niceAdder2: (Int, Int) => Int = _ + _
   // Each underscore stands for different parameters

  // Exercises
  // 1. replace all FunctionX calls with Lambdas
      // Done in MyListWithAnonymousFunction

  // 2. Define or rewrite specialAdder as Anonymous Function
     val superAdder = new Function[Int, Function[Int, Int]]{
       override def apply(x: Int): Function[Int, Int] = new Function[Int, Int]{
         override def apply(y: Int): Int = x + y
       }
      }
  // with lambdas
    val superAdder2 = (x: Int) => (y: Int) => x + y


}
