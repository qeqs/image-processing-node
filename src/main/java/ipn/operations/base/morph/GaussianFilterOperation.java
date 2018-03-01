package ipn.operations.base.morph;


import ipn.operations.base.Operation;
import java.util.Map;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class GaussianFilterOperation implements Operation<Mat> {

  @Override
  public Mat execute(Mat image, Map<String, Object> metadata) {
    Mat res = new Mat();
    Imgproc.filter2D(image, res, 1 , Imgproc.getGaussianKernel(3, 1));
    return res;
  }
}
