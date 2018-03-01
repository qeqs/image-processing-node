package ipn.operations.base.morph;

import ipn.operations.base.Operation;
import java.util.Map;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class MedianFilterOperation implements Operation<Mat>{

  @Override
  public Mat execute(Mat image, Map<String, Object> metadata) {
    Mat res = new Mat();
    Imgproc.medianBlur(image, res,3);
    return res;
  }
}
