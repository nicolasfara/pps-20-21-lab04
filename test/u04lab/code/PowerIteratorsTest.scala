package u04lab.code

import Optionals._
import Lists._
import Lists.List._
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions._

class PowerIteratorsTest {


  @Test
  def testIncremental() {
    val pi = PowerIterator.incremental(5, _+2); // pi produce 5,7,9,11,13,...
    assertEquals(Option.of(5), pi.next());
    assertEquals(Option.of(7), pi.next());
    assertEquals(Option.of(9), pi.next());
    assertEquals(Option.of(11), pi.next());
    assertEquals(List.Cons(5, List.Cons(7, List.Cons(9, List.Cons(11,List.Nil())))), pi.allSoFar()); // elementi già prodotti
    for (i <- 0 until 10) {
      pi.next(); // procedo in avanti per un po'..
    }
    assertEquals(Option.of(33), pi.next()); // sono arrivato a 33
  }

  @Test
  def testFromList(): Unit = {
    val list = Cons(1, Cons(2, Cons(3, Nil())))
    val li = PowerIterator.fromList(list)

    assertEquals(Option.of(1), li.next())
    assertEquals(Option.of(2), li.next())

    assertEquals(Cons(1, Cons(2, Nil())), li.allSoFar())
  }

  @Test
  def testRandomBoolean(): Unit = {
    val rb = PowerIterator.randomBooleans(5)
    var lst: List[Boolean] = Nil()
    for (_ <- 0 until 5) {
      lst = List.append(lst, Cons(Option.getOrElse(rb.next(), false), Nil()))
    }
    assertEquals(Option.empty, rb.next())
    assertEquals(lst, rb.allSoFar())
  }
}
