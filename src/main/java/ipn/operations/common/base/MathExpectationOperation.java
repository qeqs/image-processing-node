package ipn.operations.common.base;

import java.util.HashMap;
import lombok.Getter;
import lombok.Setter;
import org.opencv.core.Core;
import org.opencv.core.Mat;

/**
 * Created by Vadim Lygin on 9/26/2017.
 */
public abstract class MathExpectationOperation implements Operation<HashMap<Integer, Double>> {

  private final Operation morphOperation;
  @Setter
  @Getter
  private int steps;

  protected MathExpectationOperation(Operation morphOperation, int steps) {
    this.morphOperation = morphOperation;
    this.steps = steps;
  }

  @Override
  public HashMap<Integer, Double> execute(Mat image, Mat prim) {
    HashMap<Integer, Double> chart = new HashMap<>();
    Mat res = new Mat();
    Mat tempPrev = new Mat();
    image.copyTo(tempPrev);
    Mat tempNext;
    for (int i = 1; i < 2 * steps; i += 2) {
      tempNext = (Mat) morphOperation.execute(tempPrev, prim);

      Core.subtract(tempPrev, tempNext, res);
      chart.put(i, Core.norm(res) / (res.height() * res.width()));

      tempNext.copyTo(tempPrev);
    }
    return chart;
  }
}
