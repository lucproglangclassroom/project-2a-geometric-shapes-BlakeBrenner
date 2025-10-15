package edu.luc.cs.laufer.cs371.shapes

import Shape.*

object boundingBox:

  def apply(s: Shape): Location = s match

    // Leaf: Rectangle already defines its own bounding box
    case r @ Rectangle(w, h) =>
      Location(0, 0, r)

    // Ellipse: bounding box centered at origin → Location(-rx, -ry)
    case e @ Ellipse(rx, ry) =>
      Location(-rx, -ry, Rectangle(2 * rx, 2 * ry))

    // Location: translate the inner bounding box
    case Location(x, y, inner) =>
      val Location(ix, iy, Rectangle(w, h)) = apply(inner)
      Location(ix + x, iy + y, Rectangle(w, h))

    // Group: union of child bounding boxes
    case Group(shapes*) =>
      val boxes = shapes.map(apply)
      val xs = boxes.map(_.x)
      val ys = boxes.map(_.y)
      val ws = boxes.map(b => b.x + b.shape.asInstanceOf[Rectangle].width)
      val hs = boxes.map(b => b.y + b.shape.asInstanceOf[Rectangle].height)
      val left   = xs.min
      val top    = ys.min
      val right  = ws.max
      val bottom = hs.max
      Location(left, top, Rectangle(right - left, bottom - top))

end boundingBox
