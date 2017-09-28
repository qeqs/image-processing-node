package ipn.operations.common.base;

import ipn.operations.OperationsUtil;
import java.util.HashMap;
import lombok.Getter;
import lombok.Setter;
import org.opencv.core.Core;
import org.opencv.core.Mat;

/**
 * Created by Vadim Lygin on 9/26/2017.
 */
public abstract class DeviationOperation implements Operation<HashMap<Integer, Double>> {

  private final Operation<Mat> morphOperation;
  private final Operation<HashMap<Integer, Double>> mathWaitOperation;
  @Setter
  @Getter
  private int steps;

  protected DeviationOperation(Operation<Mat> morphOperation,
      Operation<HashMap<Integer, Double>> mathWaitOperation, int steps) {
    this.morphOperation = morphOperation;
    this.mathWaitOperation = mathWaitOperation;
    this.steps = steps;
  }

  @Override
  public HashMap<Integer, Double> execute(Mat image, Mat prim) {

    HashMap<Integer, Double> chart = mathWaitOperation.execute(image, prim);
    double mat = OperationsUtil.scalar(chart);
    chart = new HashMap<>();

    Mat res = new Mat();
    Mat tempPrev = new Mat();
    image.copyTo(tempPrev);
    Mat tempNext;
    for (int i = 1; i < 2 * steps; i += 2) {
      tempNext = (Mat) morphOperation.execute(tempPrev, prim);

      Core.subtract(tempPrev, tempNext, res);

      chart.put(i, Math.sqrt(
          Math.pow(Core.norm(res) - mat, 2.0) / (res.height() * res.width())
      ));

      tempNext.copyTo(tempPrev);
    }
    return chart;
  }
}
