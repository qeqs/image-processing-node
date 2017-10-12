package ipn.services;

import ipn.model.Picture;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Vadim Lygin on 9/26/2017.
 */
@Service
@Slf4j
public class PreprocessingService {

  public Picture preprocess(MultipartFile picture) {

    File file = new File(picture.getName());
    try (FileOutputStream fs = new FileOutputStream(file)) {
      fs.write(picture.getBytes());
    } catch (IOException e) {
      log.error("Error with saving picture ", e);
    }
    Mat pic = null;
    try {
      pic = Imgcodecs.imread(file.getAbsolutePath());
      if (pic.channels() > 3) {
        Imgproc.cvtColor(pic, pic, Imgproc.COLOR_BGRA2BGR);
      }
    } catch (Exception e) {
      log.error("Error with converting picture to bgr format ", e);
    }
    Picture pictureTO = new Picture();
    pictureTO.setName(picture.getName());
    pictureTO.setImageMat(pic);
    return pictureTO;
  }
}
