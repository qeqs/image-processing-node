package ipn.operations.common.morph;

import ipn.operations.common.base.Operation;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.springframework.stereotype.Component;

/**
 * Created by Vadim Lygin on 9/26/2017.
 */
@Component("prototype")
public class GradientOperation implements Operation<Mat> {

  @Override
  public Mat execute(Mat img, Mat prim) {
    Mat dilate = new Mat(), erode = new Mat();
    Imgproc.erode(img, erode, prim);
    Imgproc.dilate(img, dilate, prim);
    Core.subtract(dilate, erode, dilate);
    return dilate;
  }
}
