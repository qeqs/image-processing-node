package ipn.operations.granulation;

import ipn.operations.base.Operation;
import java.util.HashMap;
import java.util.List;

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
public class GradientDeviationOperation extends DeviationOperation{

  @Autowired
  protected GradientDeviationOperation(
      @Qualifier("gradientOperation") Operation<Mat> morphOperation,
      @Qualifier("gradientMathExpectationOperation") Operation<List<Double>> mathWaitOperation) {
    super(morphOperation, mathWaitOperation);
  }

  @Override
  public String toString() {
    return "GradientDeviation";
  }
}
