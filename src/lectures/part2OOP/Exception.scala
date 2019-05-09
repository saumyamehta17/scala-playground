package lectures.part2OOP

import scala.annotation.tailrec

object Exception extends App{
  val x: String = null

  //print(x.length) // JVM will thro exception here and noone is there to catch it

  // throwing and
//  throw new NullPointerException
//  val weried: String = throw new NullPointerException

  // catching exceptions
  def getValue(withException: Boolean): Int = {
    if(withException) throw new RuntimeException("")
    else 21
  }

  try{
//    getValue(true)
    getValue(false)
  } catch {
    case e: RuntimeException => println("caught an Exception")
  } finally {
//    println("Finally")
  }

  // define your own exceptions
  class MyException extends Exception

  val ex = new MyException
//  throw ex

  /*
  1. Crash program with OutOfMemory Error
  2. Crash with StackOverflowError
  3. Pocket Calculator
    - add(x,y)
    - subtract(x,y)
    - multiply(x,y)
    - divide(x,y)

    Throw
    - OverFlowException if add exceeds Int.MAX_VALUE
    - UnderFlowException if subtract exceeds Int.MIN_VALUE
    - MathCalculationException for division by 0

   */

  //1. Crash program with OutOfMemory Error
  //  val array = Array.ofDim(Int.MaxValue)

  // 2. SO Error
  //  @tailrec
  def crashWithOutOfMemory(num: Int): String = {
    if(num <= 1) "A" + " "
    else "A" + " " + crashWithOutOfMemory(num-1)
  }
  //  println(crashWithOutOfMemory(110000))

  class OverflowException extends RuntimeException
  class UnderflowException extends RuntimeException
  class MathCalculationException extends RuntimeException("Divide By 0")

  // 3. Pocket Calculator
  object PC {
    def add(x: Int, y: Int): Int = {
      val res: Int = x + y

      if(x > 0 && y > 0 && res < 0) throw new OverflowException
      else if(x < 0 && y < 0 && res > 0) throw new UnderflowException
      else res
    }

    def substract(x: Int, y: Int): Int = {
      val res = x - y
      if (x > 0 && y < 0 && res < 0 ) throw new OverflowException
      else if(x < 0 && y < 0 && res > 0) throw new UnderflowException
      else res
    }

    def multiple(x: Int, y: Int): Int = {
      val res = x * y
      if(x < 0 && y < 0 && res < 0) throw new OverflowException
      else if(x > 0 && y > 0 && res < 0) throw new OverflowException
      else if(x > 0 && y < 0 && res > 0) throw new UnderflowException
      else if(x < 0 && y > 0 && res > 0) throw new UnderflowException
      else res
    }

    def divide(x: Int, y: Int): Int = {
      if (y == 0) throw new MathCalculationException
      else x/y
    }
  }


  println("Adding.. " + PC.add(2,3))
//  println("OverFlow: Adding.. " + PC.add(Int.MaxValue, 1))
//  println("UnderFlow: Adding.. " + PC.add(Int.MinValue, -10))
  println("Int MinValue: ", Int.MinValue)
  println("Int MaxValue: ", Int.MaxValue)
//  println("Int Min: ", Int.MinValue - 1)
  println("Substracting.. " + PC.substract(Int.MinValue,-3))
  println("Divide by 0.." + PC.divide(12, 0))







}
