package lectures.part2OOP

//object ooBasic extends App {
//  val person = new Person("David", 13)
//  println(person.age)
//  person.greet("Saumya")
//  person.greet()
//
//  val person1 = new Person()
//  println(person1.greet())
//}
// Constructor
// val in params is used to make params behave as field
class Person(name: String, val age: Int){
  val x  = 2 // x is now field of Person class, can be accessed using obj_of_person_class.x

  println(1+2)

//  method
  def greet(name: String) = { println(s"${this.name} says: hi $name") }

//  method overloading (same method name with diff number of params and return type)

  def greet() = {println(s"Hello I am $name")}

//  multiple constructor
  def this(name: String) = this(name, 0)
  def this() = this("Saumya")
}


/*
 Novel and Writer
 Writer - firstname, lastname, dob
  method - fullname
 Novel - name, year of release , author
  method - authorAge, iswrittenBy(author), copy
*/

class Writer(val firstname: String, val lastname: String, dob: Int) {
  def fullname(): String = {
    this.firstname + ' ' + this.lastname
  }

  def age: Int = {
    return 2019 - this.dob
  }
}
class Novel(val name: String, val year_of_release: Int, writer: Writer){

  def authorAge = {
    writer.age
  }

  def isWrittenBy(author: String): Boolean = {
    this.writer.fullname() == author
  }

}

object ooBasic extends App {
  println("-----------------------ooBasics Assignment Author/Novel ------------------")
  val writer = new Writer("Karry", "Eion", 1989)
  println(writer.fullname())
  val novel = new Novel("PIE", 2012, writer)
  println("Author Age from Novel" + novel.authorAge)
  println("Written By Saumya "  +novel.isWrittenBy("Saumya"))
  println("Written By Karry "  +novel.isWrittenBy("Karry Eion"))

  println("-----------------------ooBasics Assignment Counter------------------")
//  val c = new Counter
//  println("Current Counter value " + c.current_count)
//  println("Inc Counter value " + c.inc(10))
//  println("Current Counter value " + c.current_count)
//  println("Dec Counter value " + c.dec(2))
//  println("Current Counter value " + c.current_count)
//
//  println("Overloaded Inc get value " + c.inc)
//  println("Overloaded Dec get value " + c.dec)

  val counter = new Counter()
  println(counter.count)
//  println(counter.inc.count)
//  println(counter.inc.count)
  println(counter.inc(3).count)
  println(counter.dec(3).count)
}

/*
 Counter class
   receive an Int value, method current count, method to inc and dec , overload inc/dec to recieve an amount
*/

class Counter(val count: Int = 0){
  def inc = {
    println("Incrementing...")
    new Counter(count + 1)
  }
  def dec = {
    println("Decrementing...")
    new Counter(count - 1)
  }

  def inc(n: Int): Counter = {
    if (n <= 0 ) return this
    else inc.inc(n-1)
  }

  def dec(n: Int): Counter = {
    if(n <= 0) return this
    else dec.dec(n-1)
  }
}
//Following is the wrong way, as its muatable
//class Counter {
//  var count: Int = 0
//
//  def current_count: Int = {
//    return count
//  }
//
//  def inc(amount: Int): Int = {
//    this.count += amount
//    return this.count
//  }
//
//  def dec(amount: Int): Int = {
//    this.count -= amount
//    return this.count
//  }
//
//  def inc: Int = this.inc(0)
//  def dec: Int = this.dec(0)
//}
