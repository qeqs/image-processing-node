package ipn.operations;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.stereotype.Component;

@Component
public class FileOperations {

  private Mat picture;
  private final static String TEMPORAL_IMAGE = "tmp.jpg";

  public void readImage(String path) throws IOException {
    picture = Imgcodecs.imread(path);
    if (picture.channels() > 3) {
      Imgproc.cvtColor(picture, picture, Imgproc.COLOR_BGRA2BGR);
    }
  }

  public Mat getPicture() {
    return picture;
  }

  public Image convertImage(Mat pic) throws IOException {
    Imgcodecs.imwrite(TEMPORAL_IMAGE, pic);
    BufferedImage image = ImageIO.read(new File(TEMPORAL_IMAGE));
    if (image == null) {
      return null;
    }
    return SwingFXUtils.toFXImage(image, null);
  }
}
