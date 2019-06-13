package lectures.part3FunctionalProgramming

object HofsAndCurries extends App{
  // Higher Order Functions(HOFs) which has function has parameter
  // example: map, flatMap, filter in List

  // function that applies a function n times over a value x
  // nTimes(f, n, x)
  // nTimes(f, 3, x) = f(f(f(x)))

  def nTimes(f: Int => Int, n: Int, x: Int): Int =
    if(n <= 0 ) x
    else nTimes(f, n-1, f(x))

  val plusOne = (x: Int) => x + 1
  println(nTimes(plusOne, 10, 1))

  // better way to implement above function
  // ntb(f, 10) = ntb(plusOne, 10) = plusOne(plusOne....(x))
  // increment10 = ntb(plusOne, 10)
  // increment10(1)
  // increment10(2)

  def nTimesBetter(f: Int => Int, n: Int): (Int => Int) =
    if(n <= 0) (x: Int) => x
    else (x: Int) => nTimesBetter(f, n-1)(f(x))

  val plus10 = nTimesBetter(plusOne, 10)
  println(plus10(1))

  // curried Functions
  val superAdder: Int => (Int => Int) = (x: Int) => (y: Int) => x + y
  println(superAdder(3)(10))

  // functions with multiple parameter lists, look like curried function
  def curriedFormatter(c: String)(d: Double): String = c.format(d)

  val standardFormatter: (Double => String) = curriedFormatter("%4.2f")
  val preciseFormatter: (Double => String) = curriedFormatter("%4.8f")
  println(standardFormatter(Math.PI))
  println(preciseFormatter(Math.PI))

  /* Exercise: Expand MyList
    1. forEach A => unit
       [1,2,3].forEach(x => println)
    2. sort
       [1,2,3].sort((x,y) => y-x) => [3,2,1]
    3. zipWith(list, (A,A) => B) => List[B]
       [1,2,3].zipWith([4,5,6]) => [1*4, 2*5, 3*6] => [4, 10, 18]
    4. fold(start)(function) => a
        [1,2,3].fold(0)(x+y) = 6
    5. convert functions into curried and uncurried functions
       toCurry(f: (Int => Int) => Int) => (Int => Int => Int)
       fromCurry(f: (Int => Int => Int)) => (Int => Int) => Int

    6. compose(f,g) => x => f(g(x))
       andthen(f,g) => x => g(f(x))
   */





}
