package lectures.part1Basics

object ValuesVariableTypes extends App {

//  VALs are immutable
  val x = 12
  println(x)

  val y: Int = 12
  val aString: String = "Hello"
  val aBoolean: Boolean = true
  val aShort: Short = 12345
  val aLong: Long = 1234567890
  val aFloat: Float = 12.3f
  val aDouble: Double = 12.0
  val aChar: Char = 'a'

//  Variables are mutable
  var aVariable: Int = 12
  aVariable = 13
  println(aVariable)
}
