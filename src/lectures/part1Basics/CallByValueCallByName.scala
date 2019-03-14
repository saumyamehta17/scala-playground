package lectures.part1Basics

object CallByValueCallByName extends App {

//  Call By Value - it evaluates expression before call and output(actual value) of expression is passed to function and
//  same value is used everywhere
//  callByValue(x: Int)
  def callByValue(x: Long) = {
    println("by Value " + x)
    println("by Value " + x)
  }

//  Call By Name - will delay the evaluation of expression and take expression to the function and expression is
//  evaluated at every use within
//  callByName(x: => Int)
  def callByName(x: => Long) = {
    println("by Name " + x)
    println("by Name " + x)
  }

  callByValue(System.nanoTime())
  callByValue(System.nanoTime())
}
