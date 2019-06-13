package exercises

abstract class Cons[+A] {

  // Higher Order Function -> which take function as an argument and return function

  def head: A
  def tail: Cons[A]
  def isEmpty: Boolean
  def printElements: String = ""
  override def toString: String = "[" + printElements + "]"

  //  20. OO Exercises, Expanding our collection
  def map[B](transformer: A => B): Cons[B]

  def filter(predicate: A => Boolean): Cons[A]

  // concatenation
  def ++[B >: A](list: Cons[B]): Cons[B]

  def flatMap[B](transformer: A => Cons[B]): Cons[B]

  def forEach(f: (A => Unit)): Unit

  def sort(compare: (A, A) => Int): Cons[A]

  def zipWith[B,C](list: Cons[B], f: (A,B) => C): Cons[C]

  def fold[B](start: B, f: (A,B) => B): B
}


class EmptyCons extends Cons[Nothing]{

  def head: Nothing = throw new NoSuchElementException
  def tail: Cons[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true

  def map[B](transformer: Nothing => B): Cons[B] = new EmptyCons

  def filter(predicate: Nothing => Boolean): Cons[Nothing] = new EmptyCons

  def ++[B >: Nothing](list: Cons[B]): Cons[B] = list
  def flatMap[B](transformer: Nothing => Cons[B]): Cons[B] = new EmptyCons
  def forEach(f: (Nothing => Unit)): Unit = ()
  def sort(compare: (Nothing, Nothing) => Int) =  new EmptyCons

  def zipWith[B,C](list: Cons[B], f: (Nothing, B) => C) = {
    if(!list.isEmpty) throw new RuntimeException("List length mismatches")
    else new EmptyCons
  }

  def fold[B](start: B, f: (Nothing,B) => B): B = start
}

class NonEmptyCons[+A](element: A, t: Cons[A]) extends Cons[A]{

  def head: A = element
  def tail: Cons[A] = t
  def isEmpty = false
  def add[B >: A](element: B): Cons[B] = new NonEmptyCons(element, this)


  /*
    [1,2,3].filter(n%2 == 0)
    = [2,3].filter(n%2 == 0)
    = new NonEmptyList(2, [3].filter(n%2 == 0)
    = new NonEmptyList(2, Empty.filter(n%2 == 0))
    = new NonEmptyList(2, new Empty)
  */
  def filter(predicate: A => Boolean): Cons[A] = {
    if (predicate(element)) { new NonEmptyCons(element, t.filter(predicate)) }
    else t.filter(predicate)
  }

  /*
    [1,2,3].map(n*2)
      = new NonEmptyList(2, [2,3].map(n*2))
      = new NonEmptyList(2, new NonEmptyList(4, [3].map(n*2))
      = new NonEmptyList(2, new NonEmptyList(4, new NonEmptyList(6, new Empty.map(n*2))
      = new NonEmptyList(2, new NonEmptyList(4, new NonEmptyList(6, new Empty)
   */
  def map[B](transformer: A => B): Cons[B] = {
    new NonEmptyCons(transformer(element), t.map(transformer))
  }

  /*
    [1,2] ++ [3,4]
    = new NonEmptyList(1, [2] ++ [3,4])
    = new NonEmptyList(1, new NonEmptyList(2, new Empty ++ [3,4]))
       = new NonEmptyList(1, new NonEmptyList(2, [3,4]))  = new NonEmptyList(1, new NonEmptyList(2, new NonEmptyList(3, [4])))
        = new NonEmptyList(1, new NonEmptyList(2, new NonEmptyList(3, new NonEmptyList(4, new Empty))))
   */
  def ++[B >: A](list: Cons[B]): Cons[B] = {
    new NonEmptyCons(element, t ++ list)
  }

  /*
    [1,2].flatMap(n => [n,n+1])
    = [1,2] ++ [2].flatMap(n => [n, n+1])
    = [1,2] ++ [2,3] ++ Empty.flatMap(n => [n,n+1])
    = [1,2,3,4]
   */
  def flatMap[B](transformer: A => Cons[B]): Cons[B] =
    transformer(element) ++ t.flatMap(transformer)

  override def printElements: String = {
    if (t.isEmpty) "" + element
    else element + " " + t.printElements
  }

  def forEach(f: (A => Unit)): Unit = {
    f(element)
    t.forEach(f)
  }

  def sort(compare: (A, A) => Int): Cons[A] = {
    def insert(a: A, list: Cons[A]): Cons[A] = {
      if(list.isEmpty) new NonEmptyCons(a, new EmptyCons)
      else {
        if (compare(a, list.head) < 0) new NonEmptyCons(a, insert(list.head, list.tail))
        else new NonEmptyCons(list.head, insert(a, list.tail))
      }
    }
    val sortedList = t.sort(compare)
    insert(element, sortedList)
  }

  def zipWith[B,C](list: Cons[B], f: (A,B) => C): Cons[C] = {
    if(list.isEmpty) throw new RuntimeException("List length mismatches")
    else new NonEmptyCons( f(element, list.head), t.zipWith(list.tail, f))
  }

  def fold[B](start: B = 0, f: (A,B) => B): B = {
    val newStart = f(element, start)
    t.fold(newStart, f)
  }
}

object ObjectsWithAnonymousFunction extends App {
  val list: Cons[Int] = new NonEmptyCons(1, new NonEmptyCons(2, new NonEmptyCons(3, new EmptyCons)))
  println(list.toString)
  println("Transformer " + list.map( x => x * 2  ))


  println("Predicate " + list.filter( x => x % 2 == 0 ).toString)

  val anotherList: Cons[Int] = new NonEmptyCons(4, new NonEmptyCons(5, new EmptyCons))
  println((list.++(anotherList)).toString)

  println(list.flatMap(new Function[Int, Cons[Int]] {
    override def apply(element: Int): Cons[Int] = new NonEmptyCons(element, new NonEmptyCons(element+1, new EmptyCons))
  }))

  list.forEach(x => println(x))
  println(list.sort((x: Int, y: Int) => y-x))

  val list2: Cons[Int] = new NonEmptyCons(4, new NonEmptyCons(5, new NonEmptyCons(6, new EmptyCons)))
  println(list.zipWith(list2, (x: Int, y: Int) => x * y))

  val strList: Cons[String] = new NonEmptyCons("Hello", new NonEmptyCons("Scala", new EmptyCons))
  println(anotherList.zipWith(strList, (x: Int, y: String) => x + " - " + y))
  println("Fold of [1,2,3] " + list.fold(0, (a: Int, b: Int) => a + b))
}


