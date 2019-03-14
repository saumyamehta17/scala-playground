package lectures.part1Basics

import scala.annotation.tailrec

object Recursion extends App {

  //  Problem with Recursion - Scala is on top of JVM, and JVM keeps all the calls in its internal stack which has
  //  limited memory. Stack overflow error happen if recursive depth is too big.
  //  So, the solution is tail recursion, dont keep intermiadate results to be used later, use the recursive call as last statement
  //  ** use @tailrec to make sure that tail recursion has been used or not
  // Tail recursion of


  // 1. factorial
  def factUsingTailRecursion(n: Int, accumulator: Int): Int = {
    if (n <= 1) accumulator
    else {
      factUsingTailRecursion(n-1, accumulator * n)
    }
  }

  println(factUsingTailRecursion(3 , 1))

  // 2. concatenate a string n times
  @tailrec
  def concatenateUsingTailRecursion(str: String, n: Int, accumulator: String): String = {
    if (n == 0) accumulator
    else {
      concatenateUsingTailRecursion(str, n-1, accumulator + " " + str)
    }
  }

  println(concatenateUsingTailRecursion("Hello", 5, ""))

  // 3. Is Prime function
  def isPrime(n: Int, t: Int, accumulator: Boolean): Boolean = {
    if (!accumulator) false
    else if (t <= 1) accumulator
    else isPrime(n, t-1, (n % t != 0) && accumulator)
  }
  val n = 2003
  println("Is Prime -- " + isPrime(n, n/2, true))

  // 4. Fibonacci Function
  @tailrec
  def fibUsingTailRecursion(n: Int, accumulator: Int, prev: Int): Int = {
    if (n <= 1) accumulator
    else {
      fibUsingTailRecursion(n-1, accumulator + prev, accumulator)
    }
  }

  println(fibUsingTailRecursion(8, 1, 0))

}
