package ipn.executors;

import ipn.model.OperationType;
import ipn.operations.granulation.GranulationOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Vadim Lygin on 9/28/2017.
 */
@Component
@Scope("prototype")
public class GranulationOperationsExecutor extends OperationExecutor<List<Double>> {

  @Autowired
  public GranulationOperationsExecutor(List<? extends GranulationOperation> operations) {
    super(operations);
  }

  @Override
  public boolean isApplicableFor(OperationType type) {
    return type.equals(OperationType.GRANULATION);
  }
}
