package ipn.executors;

import ipn.operations.base.Operation;
import java.util.HashMap;
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
  public StatisticOperationsExecutor(List<? extends Operation<HashMap<Integer, Double>>> operations) {
    super((List<Operation>) operations);
  }
}
