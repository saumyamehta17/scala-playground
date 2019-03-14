package lectures.part1Basics

import scala.annotation.tailrec

object Functions extends App {

  //  With parameters
  def aFunction(a: String, b: Int): String = a + ' ' + b

  println(aFunction("Hello", 4))

  //  Without parameters
  def aParameterlessFunction(): String = "Hello Scala"
  println(aParameterlessFunction())
  println(aParameterlessFunction)

  // Recursive Function
  def aRepeatedFunction(a: String, b: Int): String = {
    if (b == 1) a
    else a + " -- " +aRepeatedFunction(a, b-1)
  }

  println(aRepeatedFunction("Hello", 3))

  // Greeting
  def greetingFunction(name: String, age: Int): String = {
    "Hi, my name is " + name + " and I am " + age + " years old."
  }
  println(greetingFunction("John", 20))

  // Factorial
  def factorialFunction(n: Int): Int = {
    if (n == 1) n
    else n * factorialFunction(n-1)
  }
  println("Factoral of 3 is " + factorialFunction(5))

  // Fibonacci
  def aFibonacci(n: Int): Int = {
    if (n == 0 || n == 1) n
    else aFibonacci(n-1) + aFibonacci(n-2)
  }
  // 1 1 2 3 5 8 13 21 34
  println("Fibonacci --  " + aFibonacci(8))

  // Tests if a number is prime

  def isPrime(n :Int): Boolean = {

    def isPrimeUntil(t: Int): Boolean = {
      if (t <= 1) true
      else (n % t != 0) && isPrimeUntil(t-1)
    }

    isPrimeUntil(n/2)
  }

  println("Check Prime   " + isPrime(37))
}
