package ipn.operations.statistics;

import ipn.operations.base.Operation;
import java.util.HashMap;
import java.util.Map;
import org.opencv.core.Core;
import org.opencv.core.Mat;

/**
 * Created by Vadim Lygin on 9/26/2017.
 */
public abstract class MathExpectationOperation implements Operation<HashMap<Integer, Double>> {

  private final Operation morphOperation;

  protected MathExpectationOperation(Operation morphOperation) {
    this.morphOperation = morphOperation;
  }

  @Override
  public HashMap<Integer, Double> execute(Mat image, Map<String, Object> metadata) {

    int steps = (int) metadata.get("steps");

    HashMap<Integer, Double> chart = new HashMap<>();
    Mat res = new Mat();
    Mat tempPrev = new Mat();
    image.copyTo(tempPrev);
    Mat tempNext;
    for (int i = 1; i < 2 * steps; i += 2) {
      tempNext = (Mat) morphOperation.execute(tempPrev, metadata);

      Core.subtract(tempPrev, tempNext, res);
      chart.put(i, Core.norm(res) / (res.height() * res.width()));

      tempNext.copyTo(tempPrev);
    }
    return chart;
  }
}
