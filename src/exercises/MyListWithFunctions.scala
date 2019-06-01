package exercises

abstract class MyListWithFunctions[+A] {

  // Higher Order Function -> which take function as an argument and return function

  def head: A
  def tail: MyListWithFunctions[A]
  def isEmpty: Boolean
  def printElements: String = ""
  override def toString: String = "[" + printElements + "]"

  //  20. OO Exercises, Expanding our collection
  def map[B](transformer: A => B): MyListWithFunctions[B]

  def filter(predicate: A => Boolean): MyListWithFunctions[A]

  // concatenation
  def ++[B >: A](list: MyListWithFunctions[B]): MyListWithFunctions[B]

  def flatMap[B](transformer: A => MyListWithFunctions[B]): MyListWithFunctions[B]
}


class EmptyWithFunction extends MyListWithFunctions[Nothing]{

  def head: Nothing = throw new NoSuchElementException
  def tail: MyListWithFunctions[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true

  def map[B](transformer: Nothing => B): MyListWithFunctions[B] = new EmptyWithFunction

  def filter(predicate: Nothing => Boolean): MyListWithFunctions[Nothing] = new EmptyWithFunction

  def ++[B >: Nothing](list: MyListWithFunctions[B]): MyListWithFunctions[B] = list
  def flatMap[B](transformer: Nothing => MyListWithFunctions[B]): MyListWithFunctions[B] = new EmptyWithFunction
}

class NonEmptyListWithFunction[+A](element: A, t: MyListWithFunctions[A]) extends MyListWithFunctions[A]{

  def head: A = element
  def tail: MyListWithFunctions[A] = t
  def isEmpty = false
  def add[B >: A](element: B): MyListWithFunctions[B] = new NonEmptyListWithFunction(element, this)


  /*
    [1,2,3].filter(n%2 == 0)
    = [2,3].filter(n%2 == 0)
    = new NonEmptyList(2, [3].filter(n%2 == 0)
    = new NonEmptyList(2, Empty.filter(n%2 == 0))
    = new NonEmptyList(2, new Empty)
  */
  def filter(predicate: A => Boolean): MyListWithFunctions[A] = {
    if (predicate(element)) { new NonEmptyListWithFunction(element, t.filter(predicate)) }
    else t.filter(predicate)
  }

  /*
    [1,2,3].map(n*2)
      = new NonEmptyList(2, [2,3].map(n*2))
      = new NonEmptyList(2, new NonEmptyList(4, [3].map(n*2))
      = new NonEmptyList(2, new NonEmptyList(4, new NonEmptyList(6, new Empty.map(n*2))
      = new NonEmptyList(2, new NonEmptyList(4, new NonEmptyList(6, new Empty)
   */
  def map[B](transformer: A => B): MyListWithFunctions[B] = {
    new NonEmptyListWithFunction(transformer(element), t.map(transformer))
  }

  /*
    [1,2] ++ [3,4]
    = new NonEmptyList(1, [2] ++ [3,4])
    = new NonEmptyList(1, new NonEmptyList(2, new Empty ++ [3,4]))
       = new NonEmptyList(1, new NonEmptyList(2, [3,4]))  = new NonEmptyList(1, new NonEmptyList(2, new NonEmptyList(3, [4])))
        = new NonEmptyList(1, new NonEmptyList(2, new NonEmptyList(3, new NonEmptyList(4, new Empty))))
   */
  def ++[B >: A](list: MyListWithFunctions[B]): MyListWithFunctions[B] = {
    new NonEmptyListWithFunction(element, t ++ list)
  }

  /*
    [1,2].flatMap(n => [n,n+1])
    = [1,2] ++ [2].flatMap(n => [n, n+1])
    = [1,2] ++ [2,3] ++ Empty.flatMap(n => [n,n+1])
    = [1,2,3,4]
   */
  def flatMap[B](transformer: A => MyListWithFunctions[B]): MyListWithFunctions[B] =
    transformer(element) ++ t.flatMap(transformer)

  override def printElements: String = {
    if (t.isEmpty) "" + element
    else element + " " + t.printElements
  }

}

object ObjectsWithFunction extends App {
  val list: MyListWithFunctions[Int] = new NonEmptyListWithFunction(1, new NonEmptyListWithFunction(2, new NonEmptyListWithFunction(3, new EmptyWithFunction)))
  println(list.toString)
  println("Transformer " + list.map(new Function[Int, Int] {
    override def apply(elem: Int): Int = elem * 2
  }))

  println("Transformer with Function " + list.map(new Function[Int, Int] {
    override def apply(elem: Int): Int = elem * 2
  }))

  println("Predicate " + list.filter(new Function[Int, Boolean] {
    override def apply(elem: Int): Boolean = elem % 2 == 0
  }).toString)

  val anotherList: MyListWithFunctions[Int] = new NonEmptyListWithFunction(4, new NonEmptyListWithFunction(5, new EmptyWithFunction))
  println((list.++(anotherList)).toString)

  println(list.flatMap(new Function[Int, MyListWithFunctions[Int]] {
    override def apply(element: Int): MyListWithFunctions[Int] = new NonEmptyListWithFunction(element, new NonEmptyListWithFunction(element+1, new EmptyWithFunction))
  }))
}

