package ipn.executors;

import ipn.model.transport.Picture;
import ipn.operations.base.Operation;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Vadim Lygin on 9/28/2017.
 */
public abstract class OperationExecutor implements Executor {


  protected final List<? extends Operation> operations;

  protected OperationExecutor(List<? extends Operation> operations) {
    this.operations = operations;
  }


  public void process(Picture picture) {
    HashMap<String, Object> featureList = new HashMap<>();
    for (Operation operation : operations) {
      featureList.put(operation.toString(), operation.execute(picture.getImageMat(), picture.getProcessInfo().getMetadata()));
    }
    picture.setData(featureList);
  }

}
