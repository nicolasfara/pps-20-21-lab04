package u04lab.code

import Optionals._
import Lists._
import u04lab.code.Lists.List.Cons
import u04lab.code.Streams._

import java.util.Random
import scala.annotation.tailrec


trait PowerIterator[A] {
  def next(): Option[A]
  def allSoFar(): List[A]
  def reversed(): PowerIterator[A]
}

object PowerIterator {
  def incremental(start: Int, successive: Int => Int): PowerIterator[Int] = PowerIteratorImpl(Stream.iterate(start)(successive))
  def fromList[A](list: List[A]): PowerIterator[A] = PowerIteratorImpl(List.stream(list))
  def randomBooleans(size: Int): PowerIterator[Boolean] = PowerIteratorImpl(Stream.take(Stream.generate(new Random().nextBoolean()))(size))

  case class PowerIteratorImpl[A](private val stream: Stream[A]) extends PowerIterator[A] {

    private var pastList: List[A] = List.Nil()
    private var nextIndex = -1

    override def next(): Option[A] = {
      @tailrec
      def _next(stream: Stream[A], currNext: Int): Option[A] = stream match {
        case Stream.Cons(_, tail) if currNext > 0 => _next(tail(), currNext - 1)
        case Stream.Cons(head, _) if currNext == 0 =>
          pastList = List.append(pastList, Cons(head(), List.Nil()))
          Option.of(head())
        case _ => Option.empty
      }
      nextIndex += 1
      _next(stream, nextIndex)
    }

    override def allSoFar(): List[A] = this.pastList

    override def reversed(): PowerIterator[A] = PowerIteratorImpl(List.stream(List.reverse(pastList)))
  }
}

