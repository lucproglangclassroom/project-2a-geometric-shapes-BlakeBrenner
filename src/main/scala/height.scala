package edu.luc.cs.laufer.cs371.shapes

import Shape.*

/** Computes the height of a shape tree.
  * - Leaf shapes (Rectangle, Ellipse): height = 1  
  * - Wrappers (Location): 1 + height(inner)  
  * - Groups: 1 + max(child heights)  
  */
object height:

  def apply(s: Shape): Int = s match
    case Rectangle(_, _)    => 1
    case Ellipse(_, _)      => 1
    case Location(_, _, in) => 1 + apply(in)
    case Group(shapes*) if shapes.isEmpty => 1
    case Group(shapes*) => 1 + shapes.map(apply).max

end height
