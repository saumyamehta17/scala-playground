package src.lectures.part2OOP

object InheritenceAndTraits extends App {

// Scala has Single Level/Class Inheritance
  class Animal {
    val creatureType = "Wild"
    // Public Methods available everywhere
    def eat = println("nomnom")

    // Private Methods not available outside
    private def legs = println(4)

    // Protected Methods can be used in subclass
    protected def speak = println("bhobho")
  }

  class Cat extends Animal {
    def miow = {
      speak
      println("Meow Meow")
    }
  }


  val cat = new Cat
  cat.eat
  cat.miow

  // Constructors
  class Person(name: String, age: Int){
    def this(name: String) = this(name, 0)
  }
  class Adult(name: String, age: Int) extends Person(name, age)

  // override
  //  class Dog extends Animal {
  //    override val creatureType: String = "Domestic"
  //    override def speak = println("Bho Bho")
  //  }

  class Dog(override val creatureType: String = "Domestic") extends Animal {
    override def speak = println("Bho Bho")
  }

  val dog = new Dog
  dog.speak
  println(dog.creatureType)

  // Overriding vs Overloading
  // redefining the method in subclass
  // overloading same method name with different signature

  // preventive Overrides
  // 1. use final on member, method will not be able override
  // 2. use final on class, class will not be extendable
  // 3. use seal on class, which not be extended out of current file
}
