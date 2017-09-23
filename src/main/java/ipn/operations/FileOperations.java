package ipn.operations;

import java.io.IOException;
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
}
