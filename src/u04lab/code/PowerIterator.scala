package u04lab.code

import Optionals._
import Lists._
import u04lab.code.Lists.List.reverse
import u04lab.code.Optionals.Option.ifPresent
import u04lab.code.Streams.Stream.{drop, head}
import u04lab.code.Streams._

import java.util.Random


trait PowerIterator[A] {
  def next(): Option[A]
  def allSoFar(): List[A]
  def reversed(): PowerIterator[A]
}

object PowerIterator {
  def incremental(start: Int, successive: Int => Int): PowerIterator[Int] = PowerIteratorImpl(Stream.iterate(start)(successive))
  def fromList[A](list: List[A]): PowerIterator[A] = PowerIteratorImpl(List.stream(list))
  def randomBooleans(size: Int): PowerIterator[Boolean] = PowerIteratorImpl(Stream.take(Stream.generate(new Random().nextBoolean()))(size))

  private case class PowerIteratorImpl[A](private var stream: Stream[A]) extends PowerIterator[A] {

    private var pastList: List[A] = List.Nil()

    override def next(): Option[A] = {
      val nextElem = head(stream)
      ifPresent(nextElem)(e => pastList = List.Cons(e, pastList))
      stream = drop(stream)(1)
      nextElem
    }

    override def allSoFar(): List[A] = reverse(this.pastList)

    override def reversed(): PowerIterator[A] = PowerIteratorImpl(List.stream(pastList))
  }
}

