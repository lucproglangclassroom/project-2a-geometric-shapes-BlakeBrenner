package edu.luc.cs.laufer.cs371.shapes

import org.scalatest.funsuite.AnyFunSuite
import TestFixtures.*
import Shape.*

class TestHeight extends AnyFunSuite:

  test("height of single rectangle or ellipse") {
    assert(height(Rectangle(10, 20)) == 1)
    assert(height(Ellipse(5, 7)) == 1)
  }

  test("height of location") {
    val s = Location(10, 10, Rectangle(3, 3))
    assert(height(s) == 2)
  }

  test("height of nested locations") {
    val s = Location(2, 2, Location(3, 3, Ellipse(1, 1)))
    assert(height(s) == 3)
  }

  test("height of group adds one to deepest child") {
    val s = Group(
      Rectangle(2, 2),                          // height 1
      Location(1, 1, Group(Ellipse(3, 3)))      // inner Group(1+1+1) → 3
    )
    // Group itself adds 1 → total height 4
    assert(height(s) == 4)
  }

  test("empty group height = 1") {
    assert(height(Group()) == 1)
  }

end TestHeight
