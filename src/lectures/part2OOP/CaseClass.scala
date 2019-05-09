package lectures.part2OOP

object CaseClass extends App{

  // 1. case classes promotes all parameters to fields
  case class Person(name: String, age: Int)

  val jim = new Person("jim", 34)
  println(jim.name)

  // 2. case class has string representation
  println(jim.toString)

  // 3. equals and hashcode
  // non-case classes treat two objects different with same values
  val jim1 = new Person("jim", 34)
  println(jim1 == jim)

  // 4. handy copy method
  val jim2 = jim.copy(age = 41)
  println(jim2)
  println(jim2 == jim)

  // 5. has companion objects
//  val thePerson = Person
//  val mary = Person.apply("mary", 12)
  val mary = Person("mary", 12)
  println(mary.toString)

  // 6. classes are serializable
  // Akka -> deals with sending serializable data over the network

  // 7. have extractor patterns, it can be used for Pattern Matching

  case object UK {
    def name = ""
  }
  /* Assignment: Expand MyList - use case classes and case objects */


}
