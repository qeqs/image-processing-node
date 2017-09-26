package ipn.operations.common.base;

import java.util.HashMap;
import lombok.Getter;
import lombok.Setter;
import org.opencv.core.Core;
import org.opencv.core.Mat;

/**
 * Created by Vadim Lygin on 9/26/2017.
 */
public abstract class DeviationOperation implements Operation<HashMap<Integer, Double>> {

  private final Operation morphOperation;
  @Setter
  @Getter
  private int steps;


  protected DeviationOperation(Operation morphOperation, Operation mathWaitOperation, int steps) {
    this.morphOperation = morphOperation;
    this.steps = steps;
  }

  @Override
  public HashMap<Integer, Double> execute(Mat image, Mat prim) {


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
    return chart
  }
}
