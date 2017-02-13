package scriabin

object Scriabin {
  import java.io.File
  import com.sksamuel.scrimage._

  def readFromFile(path: String) = {
    val in = new java.io.File(path)
    val img = Image.fromFile(in)
    img
  }

  def outputImg(img: Image) = ???

  def slideWindow(img: Img, h: Int, w: Int) = ???

}

/* Scrimage Examples:

import com.sksamuel.scrimage._
val ins = "src/test/resources/img/sample.jpg"
val outs = "src/test/resources/img/grey.png"
val in = new java.io.File(ins)
val img = Image.fromFile(in)

// pyramiding
img.scale(0.1).output(new java.io.File(outs))
*/
