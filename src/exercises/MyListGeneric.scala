package exercises

abstract class MyListGeneric[+A] {

  def head: A
  def tail: MyListGeneric[A]
  def isEmpty: Boolean
  def printElements: String = ""
  override def toString: String = "[" + printElements + "]"

//  20. OO Exercises, Expanding our collection
   def map[B](transformer: MyTransformer[A,B]): MyListGeneric[B]

   def filter(predicate: MyPredicate[A]): MyListGeneric[A]

   // concatenation
   def ++[B >: A](list: MyListGeneric[B]): MyListGeneric[B]

  def flatMap[B](transformer: MyTransformer[A, MyListGeneric[B]]): MyListGeneric[B]
}


class Empty extends MyListGeneric[Nothing]{

  def head: Nothing = throw new NoSuchElementException
  def tail: MyListGeneric[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true

  def map[B](transformer: MyTransformer[Nothing,B]): MyListGeneric[B] = new Empty

  def filter(predicate: MyPredicate[Nothing]): MyListGeneric[Nothing] = new Empty

  def ++[B >: Nothing](list: MyListGeneric[B]): MyListGeneric[B] = list
  def flatMap[B](transformer: MyTransformer[Nothing, MyListGeneric[B]]): MyListGeneric[B] = new Empty
}

class NonEmptyList[+A](element: A, t: MyListGeneric[A]) extends MyListGeneric[A]{

  def head: A = element
  def tail: MyListGeneric[A] = t
  def isEmpty = false
  def add[B >: A](element: B): MyListGeneric[B] = new NonEmptyList(element, this)


  /*
    [1,2,3].filter(n%2 == 0)
    = [2,3].filter(n%2 == 0)
    = new NonEmptyList(2, [3].filter(n%2 == 0)
    = new NonEmptyList(2, Empty.filter(n%2 == 0))
    = new NonEmptyList(2, new Empty)
  */
  def filter(predicate: MyPredicate[A]): MyListGeneric[A] = {
    if (predicate.test(element)) { new NonEmptyList(element, t.filter(predicate)) }
    else t.filter(predicate)
  }

  /*
    [1,2,3].map(n*2)
      = new NonEmptyList(2, [2,3].map(n*2))
      = new NonEmptyList(2, new NonEmptyList(4, [3].map(n*2))
      = new NonEmptyList(2, new NonEmptyList(4, new NonEmptyList(6, new Empty.map(n*2))
      = new NonEmptyList(2, new NonEmptyList(4, new NonEmptyList(6, new Empty)
   */
  def map[B](transformer: MyTransformer[A,B]): MyListGeneric[B] = {
    new NonEmptyList(transformer.transform(element), t.map(transformer))
  }

  /*
    [1,2] ++ [3,4]
    = new NonEmptyList(1, [2] ++ [3,4])
    = new NonEmptyList(1, new NonEmptyList(2, new Empty ++ [3,4]))
       = new NonEmptyList(1, new NonEmptyList(2, [3,4]))  = new NonEmptyList(1, new NonEmptyList(2, new NonEmptyList(3, [4])))
        = new NonEmptyList(1, new NonEmptyList(2, new NonEmptyList(3, new NonEmptyList(4, new Empty))))
   */
  def ++[B >: A](list: MyListGeneric[B]): MyListGeneric[B] = {
    new NonEmptyList(element, t ++ list)
  }

  /*
    [1,2].flatMap(n => [n,n+1])
    = [1,2] ++ [2].flatMap(n => [n, n+1])
    = [1,2] ++ [2,3] ++ Empty.flatMap(n => [n,n+1])
    = [1,2,3,4]
   */
  def flatMap[B](transformer: MyTransformer[A, MyListGeneric[B]]): MyListGeneric[B] =
    transformer.transform(element) ++ t.flatMap(transformer)

  override def printElements: String = {
    if (t.isEmpty) "" + element
    else element + " " + t.printElements
  }

}

/*
1. Generic trait MyPredicate[-T] with little method test(T) => Boolean
2. Generic trait MyTransformer[-A, B] with method   transform(A) => B
3. MyList:
    - map(transformer) => MyList
    - filter(predicate) => MyList
    - flatMap(transformer from A to MyList[B]) => MyList[B]

    Below is PseudoCode:
    [1,2,3].map(n*2) => [2,4,6]
    [1,2,3,4].filter(n*2) => [2,4] // Filters that numbers are even
    [1,2,3].flatMap(n => [n,n+1]) => [1,2,2,3,3,4] //sublist for each element


*/

trait MyPredicate[-A] {
  def test(elem: A): Boolean
}

trait MyTransformer[-A, B] {
  def transform(elem: A): B
}

object Objects extends App {
  val list: MyListGeneric[Int] = new NonEmptyList(1, new NonEmptyList(2, new NonEmptyList(3, new Empty)))
  println(list.toString)
  println("Transformer " + list.map(new MyTransformer[Int, Int] {
    override def transform(elem: Int): Int = elem * 2
  }))

  println("Transformer with Function " + list.map(new MyTransformer[Int, Int] {
    override def transform(elem: Int): Int = elem * 2
  }))

  println("Predicate " + list.filter(new MyPredicate[Int] {
    override def test(elem: Int): Boolean = elem % 2 == 0
  }).toString)

  val anotherList: MyListGeneric[Int] = new NonEmptyList(4, new NonEmptyList(5, new Empty))
  println((list.++(anotherList)).toString)

  println(list.flatMap(new MyTransformer[Int, MyListGeneric[Int]] {
    override def transform(element: Int): MyListGeneric[Int] = new NonEmptyList(element, new NonEmptyList(element+1, new Empty))
  }))
}
