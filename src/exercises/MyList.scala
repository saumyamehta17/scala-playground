package src.exercises

import java.util.NoSuchElementException

abstract class MyList {

/*
  head = first element of list
  tail = return remainder
  isEmpty = is this list empty?
  add(int) = new list with this element added
  override toString = a string representation of list
*/

 def head: Int
 def add(n: Int): MyList
 def isEmpty: Boolean
 def tail: MyList
 def printElements: String = ""
 override def toString: String = "[" + printElements + "]"
}

object Empty extends MyList{
  def head: Int = throw new NoSuchElementException
  def tail: MyList = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add(n: Int): MyList = Empty
}

class NonEmptyList(h: Int, t: MyList) extends MyList{
  def head: Int = h
  def tail: MyList = t
  def isEmpty: Boolean = false
  def add(n: Int) = new NonEmptyList(n, this)
  override def printElements: String = {
    if (t.isEmpty) "" + h
    else h + " " + t.printElements
  }
}

object Objects extends App {
  val list = new NonEmptyList(1, new NonEmptyList(2, Empty))
  println(list.head)
  println(list.tail.head)
  println(list.add(4).head)
  println(list.toString)


}