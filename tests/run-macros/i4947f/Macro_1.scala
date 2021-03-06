import scala.quoted._

object Macros {
  def printStack(tag: String): Unit = {
    println(tag + ": "+ new Exception().getStackTrace().apply(1))
  }
  def assertImpl(expr: Expr[Boolean]) with QuoteContext = '{
    printStack("assertImpl")
    println($expr)
  }

  inline def assert2(inline expr: Boolean): Unit = ${ Macros.assertImpl('expr) }

}
