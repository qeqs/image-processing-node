package ipn.operations.base.morph;

import ipn.model.transport.PrimitiveInfo;
import ipn.operations.OperationsUtil;
import ipn.operations.base.Operation;
import java.util.Map;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Vadim Lygin on 9/26/2017.
 */
@Component
@Scope("prototype")
public class ClosingOperation implements Operation<Mat> {

  public Mat execute(Mat img, Mat prim) {
    Mat dilated = new Mat(), eroded = new Mat();

    Imgproc.dilate(img, dilated, prim);
    Imgproc.erode(dilated, img, prim);

    return eroded;
  }

  @Override
  public Mat execute(Mat image, Map<String, Object> metadata) {
    PrimitiveInfo primitiveInfo = (PrimitiveInfo) metadata.get("primitive_info");
    return execute(image, OperationsUtil.getStructElement(primitiveInfo));
  }
}
