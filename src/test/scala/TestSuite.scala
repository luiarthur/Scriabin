import org.scalatest.FunSuite
class TestSuite extends FunSuite {
  test("Test TestSuite") {
    val sampletxt = scala.io.Source.fromFile("src/test/resources/sample.txt").mkString

    //import com.sksamuel.scrimage.Image
    //val image1 = Image(getClass.getResourceAsStream("src/test/resources/img/sample.jpg"))

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
