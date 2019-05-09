package lectures.part2OOP

object Anonymous extends App{


  abstract class Animal{
    def eat
  }

  val funnyAnimal = new Animal{
    def eat = "hahahahahahahahha"
  }

  println(funnyAnimal.getClass)
  println(funnyAnimal.eat)
  /* Behind the scene above code will do follow:
     class Anonymous$$anon$1 extends Animal {
      override def eat: Unit = "hahahahahahahahha"
     }
     val funnyAnimal = new Anonymous$$anon$1
  */

  /* Takeaways
  1. We can instantiate types and override fields,methods on spot
  2. Annoymous works for traits and classes(abstract or not)
  */

}
