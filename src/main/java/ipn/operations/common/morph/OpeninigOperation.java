package ipn.operations.common.morph;

import ipn.operations.common.base.Operation;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.springframework.stereotype.Component;

/**
 * Created by Vadim Lygin on 9/26/2017.
 */
@Component("prototype")
public class OpeninigOperation implements Operation<Mat> {

  @Override
  public Mat execute(Mat img, Mat prim) {
    Mat dilated = new Mat(), eroded = new Mat();

    Imgproc.erode(img, eroded, prim);
    Imgproc.dilate(eroded, dilated, prim);

    return dilated;
  }
}
