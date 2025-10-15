package edu.luc.cs.laufer.cs371.shapes

import Shape.*

/** Computes the number of concrete leaf shapes (Rectangle, Ellipse) in a shape tree. */
object size:

  def apply(s: Shape): Int = s match
    case Rectangle(_, _)   => 1
    case Ellipse(_, _)     => 1
    case Location(_, _, in) => apply(in)
    case Group(shapes*)     => shapes.map(apply).sum

end size
