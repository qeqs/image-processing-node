package ipn.operations.base.morph;

import ipn.operations.OperationsUtil;
import ipn.operations.base.Operation;
import java.util.Map;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Vadim Lygin on 9/26/2017.
 */
@Component
@Scope("prototype")
public class GradientOperation implements Operation<Mat> {

  @Override
  public Mat execute(Mat image, Map<String, Object> metadata) {
    Integer type = (Integer) metadata.get("primitive_type");
    Integer height = (Integer) metadata.get("primitive_height");
    Integer width = (Integer) metadata.get("primitive_width");
    return execute(image, OperationsUtil.getStructElement(width, height, type));
  }

  public Mat execute(Mat img, Mat prim) {
    Mat dilate = new Mat(), erode = new Mat();

    Imgproc.erode(img, erode, prim);
    Imgproc.dilate(img, dilate, prim);
    Core.subtract(dilate, erode, dilate);

    return dilate;
  }
}
