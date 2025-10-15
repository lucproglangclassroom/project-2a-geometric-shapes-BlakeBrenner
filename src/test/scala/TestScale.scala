package edu.luc.cs.laufer.cs371.shapes

import org.scalatest.funsuite.AnyFunSuite
import TestFixtures.*
import Shape.*

class TestScale extends AnyFunSuite:

  test("scale rectangle and ellipse") {
    assert(scale(Rectangle(10, 5), 0.5) == Rectangle(5, 3))
    assert(scale(Ellipse(3, 4), 2.0) == Ellipse(6, 8))
  }

  test("scale location") {
    val s = Location(10, 20, Rectangle(5, 10))
    val t = scale(s, 0.5)
    assert(t == Location(5, 10, Rectangle(3, 5)))
  }

  test("scale nested group") {
    val s = Group(
      Rectangle(10, 10),
      Location(4, 2, Ellipse(2, 1))
    )
    val t = scale(s, 2.0).asInstanceOf[Group]
    t.shapes match
      case Seq(Rectangle(w1, h1), Location(x, y, Ellipse(rx, ry))) =>
        assert(w1 == 20 && h1 == 20)
        assert(x == 8 && y == 4)
        assert(rx == 4 && ry == 2)
      case _ => fail("unexpected structure after scaling")
  }

  test("scale empty group returns empty group") {
    assert(scale(Group(), 2.0) == Group())
  }

  test("scale by 1 keeps shape identical") {
    val r = Rectangle(5, 5)
    assert(scale(r, 1.0) == r)
  }

end TestScale
