package src.lectures.part2OOP

object AbstractsDataType extends App {

  // Abstract VS Trait - both can have abstract and non-abstract members
  // 1. Traits can't have constructor parameters
  // 2. extend only one class but multiple traits

  //  abstract - Abstract class can not be initiated, let sub class implements the details of method.
  //   abstract classes meant to be extended later
  abstract class Animal {
    val creatureType: String
    def eat
  }

  class Dog extends Animal {
    val creatureType = "ROtweiler"
    def eat = println("crunch crunch")
  }

  val dog = new Dog
  dog.eat

  // Traits - ultimate abstract dataType in Scala

  trait Carnivore {
    val creatureType: String = "Wild"
    def eat(animal: Animal)
  }

  //  traits can be inherited along with abstract class
  //  we can mixin as many traits as we want
  class Crocodile extends Animal with Carnivore {
    override val creatureType: String = "Wild"
    def eat: Unit = "nomnomnom"
    def eat(animal: Animal) = println(s"I am Crocodile and eating ${animal.creatureType}")
  }

  val croc = new Crocodile
  croc.eat(dog)
}
