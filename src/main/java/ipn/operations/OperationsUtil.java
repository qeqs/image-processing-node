package ipn.operations;

import java.util.HashMap;
import java.util.Map;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

/**
 * Created by Vadim Lygin on 9/26/2017.
 */
public class OperationsUtil {


  public static Mat getBinaryMat(Mat img) {
    Mat dst = new Mat();
    Imgproc.threshold(img, dst, 50, 250, 0);
    return dst;
  }

  public static Mat getStructElement(int w, int h, int type) {
    return Imgproc.getStructuringElement(type, new Size(w, h));
  }

  public static double scalar(HashMap<Integer, Double> chart) {
    double mat = 0.0;
    for (Map.Entry<Integer, Double> entry : chart.entrySet()) {
      Double value = entry.getValue();
      mat += value;
    }
    return mat;
  }
}
