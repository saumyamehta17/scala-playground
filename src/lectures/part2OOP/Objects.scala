package src.lectures.part2OOP

object Objects{

//  Scala does not have static
   object Person {
    //Static / Class level functionality
      val N_EYES = 2
      def canFly = false

      // Factory Method
      def from(mother: Person, father: Person): Person = new Person("Harry")
   }

   class Person(name: String) {
    //instance level functionality

   }

  println(Person.N_EYES)
  println(Person.canFly)

//  Scala object = singleton instance
  val mary = Person
  val john = Person
  println("class level / singleton object should be same" + mary == john)

  val person1 = new Person("mary")
  val person2 = new Person("john")
  println("instance level object should not be same" + person1 == person2)

  println("Factory Method --> " + Person.from(person1, person2))

//  Scala Applications = Scala Object with
//  def main(args: Array[String]): Unit
//  scala application turns into java virtual machine application,
  //  whose entry point have to be 'public static void main wth array of string as parameter'
//  In scala, void return type is Unit, static in scala is a simple method of object

  def main(args: Array[String]): Unit = {
    println("Heyyhh")
  }
}


