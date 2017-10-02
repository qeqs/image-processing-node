package ipn.executors;

import ipn.model.OperationType;
import ipn.model.Picture;

/**
 * Created by Vadim Lygin on 9/28/2017.
 */
public interface Executor {
  void process(Picture picture);
  boolean isApplicableFor(OperationType type);
}
