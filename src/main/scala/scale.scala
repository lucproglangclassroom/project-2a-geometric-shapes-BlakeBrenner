package edu.luc.cs.laufer.cs371.shapes

import Shape.*

/** Scales a shape tree recursively by factor k.
  * Dimensions and locations are scaled; structure is preserved.
  */
object scale:

  def apply(s: Shape, k: Double): Shape =
    require(k >= 0.0, s"scale factor must be non-negative: $k")

    def sInt(x: Int): Int = math.round(x * k).toInt

    s match
      case Rectangle(w, h)    => Rectangle(sInt(w), sInt(h))
      case Ellipse(rx, ry)    => Ellipse(sInt(rx), sInt(ry))
      case Location(x, y, in) => Location(sInt(x), sInt(y), apply(in, k))
      case Group(shapes*)     => Group(shapes.map(ch => apply(ch, k))*)

end scale
