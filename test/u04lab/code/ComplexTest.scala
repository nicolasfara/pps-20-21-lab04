package u04lab.code

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ComplexTest {

  private val a = Array(Complex(10.0, 20.0), Complex(2.0, 4.0), Complex(1, 1), Complex(-2, -4))

  @Test
  def testEquals(): Unit = {
    assertEquals(Complex(10, 10), Complex(10, 10))
  }

  @Test
  def testAddComplex(): Unit = {
    assertEquals(Complex(10, 5) + Complex(5, 1), Complex(15, 6))
  }

  @Test
  def testNegativeAdd(): Unit = {
    assertEquals(Complex(10, 5) + Complex(-1, -1), Complex(9, 4))
  }

  @Test
  def testMultiply(): Unit = {
    assertEquals(Complex(1, 2) * Complex(1, 1), Complex(-1, 3))
  }

  @Test
  def testNegativeMultiply(): Unit = {
    assertEquals(Complex(1, 2) * Complex(-1, -1), Complex(1, -3))
  }

  @Test
  def testToString(): Unit = {
    assertEquals(Complex(10, 20).toString, Complex(10.0, 20.0).toString)
  }
}
