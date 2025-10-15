package edu.luc.cs.laufer.cs371.shapes

import org.scalatest.funsuite.AnyFunSuite
import TestFixtures.*
import Shape.*

class TestSize extends AnyFunSuite:

  test("size of single rectangle") {
    assert(size(simpleRectangle) == 1)
  }

  test("size of location (still one leaf)") {
    assert(size(simpleLocation) == 1)
  }

  test("size of ellipse and rectangle in group") {
    val g = Group(Ellipse(10, 5), Rectangle(4, 3))
    assert(size(g) == 2)
  }

  test("nested groups count all leaves") {
    val g = Group(
      Rectangle(10, 10),
      Group(Ellipse(3, 4), Rectangle(1, 1)),
      Location(5, 5, Ellipse(2, 2))
    )
    assert(size(g) == 4)
  }

end TestSize
