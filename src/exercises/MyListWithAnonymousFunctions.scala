package exercises

abstract class MyListWithAnonymousFunctions[+A] {

  // Higher Order Function -> which take function as an argument and return function

  def head: A
  def tail: MyListWithAnonymousFunctions[A]
  def isEmpty: Boolean
  def printElements: String = ""
  override def toString: String = "[" + printElements + "]"

  //  20. OO Exercises, Expanding our collection
  def map[B](transformer: A => B): MyListWithAnonymousFunctions[B]

  def filter(predicate: A => Boolean): MyListWithAnonymousFunctions[A]

  // concatenation
  def ++[B >: A](list: MyListWithAnonymousFunctions[B]): MyListWithAnonymousFunctions[B]

  def flatMap[B](transformer: A => MyListWithAnonymousFunctions[B]): MyListWithAnonymousFunctions[B]
}


class EmptyWithAnonymousFunctions extends MyListWithAnonymousFunctions[Nothing]{

  def head: Nothing = throw new NoSuchElementException
  def tail: MyListWithAnonymousFunctions[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true

  def map[B](transformer: Nothing => B): MyListWithAnonymousFunctions[B] = new EmptyWithAnonymousFunctions

  def filter(predicate: Nothing => Boolean): MyListWithAnonymousFunctions[Nothing] = new EmptyWithAnonymousFunctions

  def ++[B >: Nothing](list: MyListWithAnonymousFunctions[B]): MyListWithAnonymousFunctions[B] = list
  def flatMap[B](transformer: Nothing => MyListWithAnonymousFunctions[B]): MyListWithAnonymousFunctions[B] = new EmptyWithAnonymousFunctions
}

class NonEmptyListWithAnonymousFunctions[+A](element: A, t: MyListWithAnonymousFunctions[A]) extends MyListWithAnonymousFunctions[A]{

  def head: A = element
  def tail: MyListWithAnonymousFunctions[A] = t
  def isEmpty = false
  def add[B >: A](element: B): MyListWithAnonymousFunctions[B] = new NonEmptyListWithAnonymousFunctions(element, this)


  /*
    [1,2,3].filter(n%2 == 0)
    = [2,3].filter(n%2 == 0)
    = new NonEmptyList(2, [3].filter(n%2 == 0)
    = new NonEmptyList(2, Empty.filter(n%2 == 0))
    = new NonEmptyList(2, new Empty)
  */
  def filter(predicate: A => Boolean): MyListWithAnonymousFunctions[A] = {
    if (predicate(element)) { new NonEmptyListWithAnonymousFunctions(element, t.filter(predicate)) }
    else t.filter(predicate)
  }

  /*
    [1,2,3].map(n*2)
      = new NonEmptyList(2, [2,3].map(n*2))
      = new NonEmptyList(2, new NonEmptyList(4, [3].map(n*2))
      = new NonEmptyList(2, new NonEmptyList(4, new NonEmptyList(6, new Empty.map(n*2))
      = new NonEmptyList(2, new NonEmptyList(4, new NonEmptyList(6, new Empty)
   */
  def map[B](transformer: A => B): MyListWithAnonymousFunctions[B] = {
    new NonEmptyListWithAnonymousFunctions(transformer(element), t.map(transformer))
  }

  /*
    [1,2] ++ [3,4]
    = new NonEmptyList(1, [2] ++ [3,4])
    = new NonEmptyList(1, new NonEmptyList(2, new Empty ++ [3,4]))
       = new NonEmptyList(1, new NonEmptyList(2, [3,4]))  = new NonEmptyList(1, new NonEmptyList(2, new NonEmptyList(3, [4])))
        = new NonEmptyList(1, new NonEmptyList(2, new NonEmptyList(3, new NonEmptyList(4, new Empty))))
   */
  def ++[B >: A](list: MyListWithAnonymousFunctions[B]): MyListWithAnonymousFunctions[B] = {
    new NonEmptyListWithAnonymousFunctions(element, t ++ list)
  }

  /*
    [1,2].flatMap(n => [n,n+1])
    = [1,2] ++ [2].flatMap(n => [n, n+1])
    = [1,2] ++ [2,3] ++ Empty.flatMap(n => [n,n+1])
    = [1,2,3,4]
   */
  def flatMap[B](transformer: A => MyListWithAnonymousFunctions[B]): MyListWithAnonymousFunctions[B] =
    transformer(element) ++ t.flatMap(transformer)

  override def printElements: String = {
    if (t.isEmpty) "" + element
    else element + " " + t.printElements
  }

}

object ObjectsWithAnonymousFunction extends App {
  val list: MyListWithAnonymousFunctions[Int] = new NonEmptyListWithAnonymousFunctions(1, new NonEmptyListWithAnonymousFunctions(2, new NonEmptyListWithAnonymousFunctions(3, new EmptyWithAnonymousFunctions)))
  println(list.toString)
  println("Transformer " + list.map( x => x * 2  ))


  println("Predicate " + list.filter( x => x % 2 == 0 ).toString)

  val anotherList: MyListWithAnonymousFunctions[Int] = new NonEmptyListWithAnonymousFunctions(4, new NonEmptyListWithAnonymousFunctions(5, new EmptyWithAnonymousFunctions))
  println((list.++(anotherList)).toString)

  println(list.flatMap(new Function[Int, MyListWithAnonymousFunctions[Int]] {
    override def apply(element: Int): MyListWithAnonymousFunctions[Int] = new NonEmptyListWithAnonymousFunctions(element, new NonEmptyListWithAnonymousFunctions(element+1, new EmptyWithAnonymousFunctions))
  }))
}


