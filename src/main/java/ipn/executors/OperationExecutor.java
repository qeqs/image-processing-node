package ipn.executors;

import ipn.operations.base.Operation;
import org.opencv.core.Mat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Vadim Lygin on 9/28/2017.
 */
public abstract class OperationExecutor<T> implements Executor<T> {


  protected final List<? extends Operation> operations;

  protected OperationExecutor(List<? extends Operation<T>> operations) {
    this.operations = operations;
  }


  public List<T> process(Mat picture, Map<String, Object> metadata) {
    List<T> featureList = new ArrayList<>();
    for (Operation<T> operation : operations) {
      featureList.add(operation.execute(picture, metadata));
    }
    return featureList;
  }

}
