package ipn.operations.statistics;

import ipn.operations.base.Operation;
import java.util.HashMap;
import org.opencv.core.Mat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Vadim Lygin on 9/28/2017.
 */
@Component
@Scope("prototype")
public class OpeningDeviationOperation extends DeviationOperation {

  @Autowired
  public OpeningDeviationOperation(
      @Qualifier("openinigOperation") Operation<Mat> morphOperation,
      @Qualifier("openingMathExpectationOperation") Operation<HashMap<Integer, Double>> mathWaitOperation) {
    super(morphOperation, mathWaitOperation);
  }

  @Override
  public String toString() {
    return "OpeningDeviation";
  }
}
