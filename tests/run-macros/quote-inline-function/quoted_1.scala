import scala.quoted._
import scala.quoted.autolift.{given _}

object Macros {

  inline def foreach1(start: Int, end: Int, f: Int => Unit): String = ${impl('start, 'end, 'f)}
  inline def foreach2(start: Int, end: Int, f: => Int => Unit): String = ${impl('start, 'end, 'f)}
  inline def foreach3(start: Int, end: Int, inline f: Int => Unit): String = ${impl('start, 'end, 'f)}

  def impl(start: Expr[Int], end: Expr[Int], f: Expr[Int => Unit]) with (qctx: QuoteContext) : Expr[String] = {
    import qctx.tasty.{_, given _}
    val res = '{
      var i = $start
      val j = $end
      while (i < j) {
        ${Expr.betaReduce(f)('i)}
        i += 1
      }
      while {
        ${Expr.betaReduce(f)('i)}
        i += 1
        i < j
      } do ()
    }
    res.show
  }
}
