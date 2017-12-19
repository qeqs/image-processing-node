package ipn.executors;

import ipn.model.OperationType;
import ipn.operations.statistics.StatisticOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Vadim Lygin on 9/28/2017.
 */
@Component
@Scope("prototype")
public class StatisticOperationsExecutor extends OperationExecutor {

  @Autowired
  public StatisticOperationsExecutor(List<? extends StatisticOperation> operations) {
    super(operations);
  }

  @Override
  public boolean isApplicableFor(OperationType type) {
    return type.equals(OperationType.STATISTICAL);
  }
}
