package lectures.part2OOP

object Generics extends App{
  //  Type System
  // type paramterized - previous linkedlist only accept int, now we want to store strings in linkedlist then we have duplicate code
  // collection that could store elements of certain type

  // MyList is generic class
  class MyList[A] {
  //    use type A
  }
  //  multiple type parameter(key is one generic type, and value is another)
  class MyMap[key, value]

  val intList = new MyList[Int]
  val stringList = new MyList[String]

  // Generic Methods
  object MyList{
    def emptyList[A]: MyList[A] = ???
  }

  val emptyListOfInt = MyList.emptyList[Int]
  //  println(emptyListOfInt)

  //  variance Problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal
  // Question - if cat extends animal, does list-of_cats also extends list_of_animals
  // Answers
  // 1. yes, list_of_cats extends list_of_animals called COVARIANCE
   class ConvarianceList[+A]
  // 2. List of cat and list of dog are different = INVARIANCE
  class InvariantList[A]
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]

  // 3. CONTRAVARIANCE
  class ContravarianceList[-A]

  // Bounded Types - allow you to use your generic classes only certain class
  class Cage[A <: Animal](animal: Animal) // class cage only have type paramters of Animal
  // <: or lower bounded types,  mean it allow types which are subclass of animal
  val cage = new Cage(new Dog)
  class Car
  //  val cage = new Cage(new Car) // this will not work, as only allowed generic are Animal
  // >: or upper bounded types


  // Exercise - Expand the MyList "Go to MyList file"
}
