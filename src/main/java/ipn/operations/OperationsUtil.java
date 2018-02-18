package ipn.operations;

import ipn.model.transport.PrimitiveInfo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

/**
 * Created by Vadim Lygin on 9/26/2017.
 */
public class OperationsUtil {
  private static final int WIDTH = 5;
  private static final int HEIGHT = 5;
  private static final int TYPE = Imgproc.MORPH_RECT;


  public static Mat getBinaryMat(Mat img) {
    Mat dst = new Mat();
    Imgproc.threshold(img, dst, 50, 250, 0);
    return dst;
  }

  public static Mat getStructElement(PrimitiveInfo primitiveInfo) {
    return Imgproc.getStructuringElement(primitiveInfo.getType(), new Size(primitiveInfo.getWidth(), primitiveInfo.getHeight()));
  }

  public static double scalar(List<Double> chart) {
    double mat = 0.0;
    for (Double value : chart) {
      mat += value;
    }
    return mat;
  }
}
