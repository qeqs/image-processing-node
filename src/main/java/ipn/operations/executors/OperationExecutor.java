package ipn.operations.executors;

import ipn.model.Picture;
import ipn.operations.base.Operation;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vadim Lygin on 9/28/2017.
 */
public abstract class OperationExecutor implements Executor {


  protected final List<Operation> operations;

  protected OperationExecutor(List<Operation> operations) {
    this.operations = operations;
  }


  public void process(Picture picture) {
    ArrayList<Object> featureList = new ArrayList<>();
    for (Operation operation : operations) {
      featureList.add(operation.execute(picture.getImageMat(), picture.getMetadata()));
    }

  }

}
