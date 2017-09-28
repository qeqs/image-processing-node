package ipn.operations.common.statistic;

import ipn.operations.common.base.MathExpectationOperation;
import ipn.operations.common.base.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Vadim Lygin on 9/28/2017.
 */
@Component
@Scope("prototype")
public class OpeningMathExpectationOperation extends MathExpectationOperation {

  @Autowired
  public OpeningMathExpectationOperation(
      @Qualifier("openinigOperation") Operation morphOperation,
      @Value("application.steps") int steps) {
    super(morphOperation, steps);
  }
}
