package u04lab.code

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ComplexTest {

  private val a = Array(Complex(10.0, 20.0), Complex(2.0, 4.0), Complex(1, 1), Complex(-2, -4))

  @Test
  def testEquals(): Unit = {
    assertEquals(a(0), a(0))
  }

  @Test
  def testAddComplex(): Unit = {
    assertEquals(a(0) + a(1), Complex(12, 24))
  }

  @Test
  def testNegativeAdd(): Unit = {
    assertEquals(a(2) + a(3), Complex(-1, -3))
  }

  @Test
  def testMultiply(): Unit = {
    assertEquals(a(0) * a(1), Complex(20, 80))
  }

  @Test
  def testNegativeMultiply(): Unit = {
    assertEquals(a(0) * a(3), Complex(-20, -80))
  }

  @Test
  def testToString(): Unit = {
    assertEquals(a(0).toString, Complex(10.0, 20.0).toString)
  }
}
