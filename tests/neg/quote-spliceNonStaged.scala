package quotes
import scala.quoted._

object Quotes_1 {
  def printHello with QuoteContext : Expr[Unit] = '{ println("Hello") }
  $printHello // error
}
