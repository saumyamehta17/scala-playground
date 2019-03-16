package lectures.part2OOP

object ooBasic extends App {
  val person = new Person("David", 13)
  println(person.age)
  person.greet("Saumya")
  person.greet()

  val person1 = new Person()
  println(person1.greet())
}

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

