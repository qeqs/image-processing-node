package ipn.operations.statistics;

import ipn.operations.base.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Vadim Lygin on 9/28/2017.
 */
@Component
@Scope("prototype")
public class GradientMathExpectationOperation extends MathExpectationOperation {

  @Autowired
  protected GradientMathExpectationOperation(
      @Qualifier("gradientOperation") Operation morphOperation) {
    super(morphOperation);
  }
}
