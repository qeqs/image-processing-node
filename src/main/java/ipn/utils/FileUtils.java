package ipn.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

@Slf4j
public class FileUtils {

  public static Mat readFile(String path) {
    Mat pic = null;
    try {
      pic = Imgcodecs.imread(path);
      if (pic.channels() > 3) {
        Imgproc.cvtColor(pic, pic, Imgproc.COLOR_BGRA2BGR);
      }
    } catch (Exception e) {
      log.error("Error with converting picture to bgr format ", e);
    }
    return pic;
  }

  public static byte[] readBytes(String path) {
    File file = new File(path);
    try (FileInputStream fs = new FileInputStream(file)) {
      return IOUtils.toByteArray(fs);
    } catch (IOException e) {
      log.error("Error with fetching picture from file system", e);
    }
    return null;
  }

}
