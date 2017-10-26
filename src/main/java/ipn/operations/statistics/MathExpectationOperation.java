package ipn.operations.statistics;

import ipn.model.transport.PrimitiveInfo;
import ipn.operations.OperationsUtil;
import ipn.operations.base.Operation;
import java.util.HashMap;
import java.util.Map;
import org.opencv.core.Core;
import org.opencv.core.Mat;

/**
 * Created by Vadim Lygin on 9/26/2017.
 */
public abstract class MathExpectationOperation implements StatisticOperation {

  private final Operation morphOperation;

  protected MathExpectationOperation(Operation morphOperation) {
    this.morphOperation = morphOperation;
  }

  @Override
  public HashMap<Integer, Double> execute(Mat image, Map<String, Object> metadata) {

    int steps = (int) metadata.get("steps");
    Integer height = (Integer) metadata.get("primitive_height");
    Integer width = (Integer) metadata.get("primitive_width");
    Integer type = (Integer) metadata.get("primitive_type");

    HashMap<Integer, Double> chart = new HashMap<>();
    Mat res = new Mat();
    Mat tempPrev = new Mat();
    image.copyTo(tempPrev);
    Mat tempNext;
    for (int i = 1; i < 2 * steps; i += 2) {
      PrimitiveInfo primitiveInfo = new PrimitiveInfo(height,width,type);
      primitiveInfo.increment(i/2);
      Map<String, Object> morphMetadata = new HashMap<>();
      morphMetadata.put("primitive_info", primitiveInfo);
      tempNext = (Mat) morphOperation.execute(tempPrev, morphMetadata);

      Core.subtract(tempPrev, tempNext, res);
      chart.put(i, Core.norm(res) / (res.height() * res.width()));

      tempNext.copyTo(tempPrev);
    }
    return chart;
  }
}
