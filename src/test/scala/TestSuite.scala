import org.scalatest.FunSuite
class TestSuite extends FunSuite {
  test("Test TestSuite") {
    val sampletxt = scala.io.Source.fromFile("src/test/resources/sample.txt").mkString

    /* Scrimage is working...
    import com.sksamuel.scrimage._
    val ins = "src/test/resources/img/sample.jpg"
    val outs = "src/test/resources/img/grey.png"
    val in = new java.io.File(ins)
    val img = Image.fromFile(in)


    // pyramiding
    img.scale(0.1).output(new java.io.File(outs))
    */

    import CannyEdgeDetector._
    val detector = new CannyEdgeDetector()
    detector.setLowThreshold(.5f)
    detector.setHighThreshold(1f)
    val img = ImageIO.read(new File("src/test/resources/img/sample.jpg"))
    detector.setSourceImage(img)
    detector.process()
    val edges = detector.getEdgesImage();
    ImageIO.write(edges, "jpg", new File("src/test/resources/img/edge.jpg"))

    
    import java.io.File
    import javax.imageio.ImageIO // to read img 
    import java.awt.image.BufferedImage // to store img
    import java.awt.Color

    val img = ImageIO.read(new File("src/test/resources/img/sample.jpg"))
    for (i <- 0 until img.getHeight; j <- 0 until img.getWidth) {
      val c = new Color(img.getRGB(j,i))
      //val x = { if ( c.getRed+ c.getGreen + c.getBlue > 70*3.0) 255 else 0 }
      //val x = (c.getRed*.299 + c.getGreen*.587 + c.getBlue*.114).toInt
      val x = if (c.getRed < 70 && c.getGreen < 70) 0 else 255
      val newc = new Color(x,x,x)
      img.setRGB(j,i,newc.getRGB)
    }
    ImageIO.write(img, "jpg", new File("src/test/resources/img/grayscale.jpg"))
  }
}
