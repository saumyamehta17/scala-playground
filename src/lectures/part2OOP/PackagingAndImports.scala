package lectures.part2OOP

//import playground.Cindrella

object PackagingAndImports extends App {
  // Package = is a group of definitions under same name

  // How to use definitions
  // 1. Be in same package like follow. package members are accessible by their simple name
  val writer = new Coder("saumya")
  // 2. import package
  //val princess = new Cindrella
  //or
  val princess = new playground.Cindrella // a fully qualified name

  // package object
  // standalone methods/constants, used one per package

  // Name aliasing at imports
  // if we same name classes in different packages, we can use aliasing like below:
  // 1. import java.util.Date
  // 2. import java.sql.Date
  // val date = new Date // it will use first
  // import java.sql.Date convert to import java.sql.(Date => SqlDate)
  // val sqldate = new SqlDate

  // Default Packages
  // java.lang - String, Object, Exception
  // scala - Int, Nothing, Function and may more



}
