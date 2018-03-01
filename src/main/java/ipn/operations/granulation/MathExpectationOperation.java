package ipn.operations.granulation;

import ipn.model.transport.PrimitiveInfo;
import ipn.operations.base.Operation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.opencv.core.Core;
import org.opencv.core.Mat;

/**
 * Created by Vadim Lygin on 9/26/2017.
 */
public abstract class MathExpectationOperation implements GranulationOperation {

  private final Operation morphOperation;

  protected MathExpectationOperation(Operation morphOperation) {
    this.morphOperation = morphOperation;
  }

  @Override
  public List<Double> execute(Mat image, Map<String, Object> metadata) {

    int steps = (int) metadata.get("steps");
//    Integer height = (Integer) metadata.get("primitive_height");
//    Integer width = (Integer) metadata.get("primitive_width");
//    Integer type = (Integer) metadata.get("primitive_type");

    List<Double> chart = new ArrayList<>();
    Mat res = new Mat();
    Mat tempPrev = new Mat();
    image.copyTo(tempPrev);
    Mat tempNext;
    for (int i = 3; i < 2 * steps + 2; i += 2) {
      PrimitiveInfo primitiveInfo = new PrimitiveInfo();
      primitiveInfo.increment(i);

      Map<String, Object> morphMetadata = new HashMap<>();
      morphMetadata.put("primitive_info", primitiveInfo);

      tempNext = (Mat) morphOperation.execute(tempPrev, morphMetadata);

      Core.subtract(tempPrev, tempNext, res);
      chart.add(Core.norm(res) / (res.height() * res.width()));

      tempNext.copyTo(tempPrev);
    }
    return chart;
  }
}
