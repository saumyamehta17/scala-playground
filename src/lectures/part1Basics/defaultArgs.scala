package lectures.part1Basics

object defaultArgs extends App {

//  Default Args accumulator passed as 1
// case: 99% when we call function with same params
  def tailFactRecusrionWithDefaultArg(n: Int, accumulator: Int = 1): Int = {
    if (n <= 1) accumulator
    else tailFactRecusrionWithDefaultArg(n-1, n * accumulator)
  }

  println(tailFactRecusrionWithDefaultArg(10))

//  Named Args
  def greet(name: String = "John", age: Int = 12) = println("Name is " + name + "and age is "+ age)

  greet()
  greet(name = "Mary")


}
